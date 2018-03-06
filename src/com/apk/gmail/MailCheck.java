package com.apk.gmail;

import com.apk.jarvisai.TTSMbrola;

public class MailCheck {

	static TTSMbrola tts = new TTSMbrola();

	static public void getMailCount() {
		tts.getString("Please wait while I check your g mail account.");

		try {
			Quickstart.listMessagesMatchingQuery("label:important is:unread");
			long mailCount = Quickstart.getMailCount();
			tts.getString("You have " + mailCount + " un read" + ((mailCount > 1) ? "mails" : "mail"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public void getSenderName() {

	}
}
