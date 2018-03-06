package com.apk.jarvisai;

import java.io.InputStream;
import java.io.StringWriter;

public class RegistryQuery {

	private static final String REGQUERY_UTIL = "reg query ";
	private static final String REGSTR_TOKEN = "REG_SZ";

	public String getRegistryPath(String appName) {
		String PERSONAL_FOLDER_CMD = REGQUERY_UTIL + "\"HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\"
				+ "App Paths\\" + appName + "\" /ve";
		try {
			Process process = Runtime.getRuntime().exec(PERSONAL_FOLDER_CMD);
			StreamReader reader = new StreamReader(process.getInputStream());

			reader.start();
			process.waitFor();
			reader.join();

			String result = reader.getResult();
			int p = result.indexOf(REGSTR_TOKEN);
			if (p == -1)
				return null;
			return result.substring(p + REGSTR_TOKEN.length()).trim();
		} catch (Exception e) {
			return null;
		}
	}

	static class StreamReader extends Thread {
		private InputStream is;
		private StringWriter sw;

		StreamReader(InputStream is) {
			this.is = is;
			sw = new StringWriter();
		}

		public void run() {
			try {
				int c;
				while ((c = is.read()) != -1)
					sw.write(c);
			} catch (Exception e) {
			}
		}

		String getResult() {
			return sw.toString();
		}
	}
}
