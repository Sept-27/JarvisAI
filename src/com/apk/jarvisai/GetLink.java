package com.apk.jarvisai;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetLink {

	String[] divClassesTopLinkData = { "div._jsh", "div.f", "div._pwc" };
	String urlText = "";
	String url1 = "";

	public String findUrl(String query) {

		try {
			int index = 0;
			Document doc = Jsoup.connect("https://www.google.com/search?q=" + query).get();
			for (int i = 0; i < divClassesTopLinkData.length; i++) {
				Elements link = doc.select(divClassesTopLinkData[i]);
				urlText = link.text();
				if (urlText.length() > 1) {
					index = i;
					break;
				}
			}
			if (divClassesTopLinkData[index].equals("div.f")) {
				String[] s = urlText.split(" ");
				url1 = s[0];
				System.out.println(url1);
				if (url1.length() > 1) {
					System.out.println(url1);
					Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome " + url1 });
					return "success";
				}

			} else if (divClassesTopLinkData[index].equals("div._pwc")) {
				url1 = urlText.substring(urlText.indexOf("https://"));
				if (url1.contains("Similar")) {
					url1 = url1.replaceAll("Similar", "");
				} else if (url1.contains("similar")) {
					url1 = url1.replaceAll("similar", "");
				}
				if (url1.length() > 1) {
					System.out.println(url1);
					Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome " + url1 });
					return "success";
				}
			} else if (divClassesTopLinkData[index].equals("div._jsh")) {
				url1 = urlText.substring(urlText.indexOf("https://"));
				if (url1.contains("Similar")) {
					url1 = url1.replaceAll("Similar", "");
				} else if (url1.contains("similar")) {
					url1 = url1.replaceAll("similar", "");
				}
				if (url1.length() > 1) {
					System.out.println(url1);
					Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome " + url1 });
					return "success";
				}
			}
		} catch (Exception e) {

		}
		return "failure";
	}

}
