package com.apk.gmail;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Quickstart {
	/** Application name. */
	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File("D:/.credentials/gmail-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Scope for permission management. */
	private static final List<String> SCOPES = Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);

	private static final String USER = "me";

	private static long mailCount = 0;

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	public static Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = Quickstart.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();

		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	public static Gmail getGmailService() throws IOException {
		Credential credential = authorize();
		return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	// Get mail list based on search query
	public static List<Message> listMessagesMatchingQuery(String query) throws IOException {
		Gmail service = getGmailService();
		ListMessagesResponse response = service.users().messages().list(USER).setQ(query).execute();
		List<Message> messages = new ArrayList<Message>();

		while (response.getMessages() != null) {
			messages.addAll(response.getMessages());
			if (response.getNextPageToken() != null) {
				String pageToken = response.getNextPageToken();
				response = service.users().messages().list(USER).setQ(query).setPageToken(pageToken).execute();
			} else {
				break;
			}
		}
		setMailCount(messages.size());
		for (Message message : messages) {
			System.out.println(message.toPrettyString());
			getMessage(message.getId());
		}
		return messages;
	}

	// Read mail content based on mail ID retrieved by listMessageMatchingQuery()
	public static Message getMessage(String messageId) throws IOException {
		Gmail service = getGmailService();
		Message message = service.users().messages().get(USER, messageId).execute();
		System.out.println("Message snippet: " + message.getSnippet());
		List<MessagePartHeader> header = message.getPayload().getHeaders();
		for (MessagePartHeader h : header) {
			String name = h.getName();
			if (name.equalsIgnoreCase("from")) {
				System.out.println("Header value: " + h.getValue());
				break;
			}
		}
		return message;
	}

	// send mail
	public static void sendMessage(MimeMessage email) throws MessagingException, IOException {
		Gmail service = getGmailService();
		Message message = createMessageWithEmail(email);
		message = service.users().messages().send(USER, message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString());
	}

	// create mime email
	public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
			throws MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setText(bodyText);
		return email;
	}

	// encode mail to base64
	public static Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		emailContent.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

	public static List<Label> getLabelNames() throws IOException {
		Gmail service = getGmailService();
		ListLabelsResponse listResponse = service.users().labels().list(USER).execute();
		List<Label> labels = listResponse.getLabels();
		if (labels.size() == 0) {
			System.out.println("No labels found.");
		} else {
			System.out.println("Labels:");
			for (Label label : labels) {
				System.out.printf("- %s\n", label.getName());
			}
		}
		return labels;
	}

	public static long getMailCount() {
		return mailCount;
	}

	public static void setMailCount(long mailCount) {
		Quickstart.mailCount = mailCount;
	}

}
