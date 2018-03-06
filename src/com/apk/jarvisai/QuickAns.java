package com.apk.jarvisai;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class QuickAns {

	String ans = "";
	String[] divClassesWH = { "span._Tgc", "div._yXc", "div._oDd", "div._OMb" };
	String[] divClassesDIR = { "div.vk_ans", "div.vk_bk", "div._xwk", "span._tgc" };
	String[] divClassesDIST = { "div._xwk", "div._fk" };
	String[] divClassesMEAN = { "div._jig" };
	String[] divClassesIP = { "div.vk_h" };

	public void getAns(String query) {
		try {
			Document doc = Jsoup.connect("https://www.google.com/search?q=" + query).get();
			if (-1 != query.indexOf("how") || -1 != query.indexOf("why")) {
				for (int i = 0; i < divClassesWH.length; i++) {
					Elements link = doc.select(divClassesWH[i]);
					ans = link.text();
					if (ans.length() > 1) {
						break;
					}
				}
			}

			else if (-1 != query.indexOf("distance")) {
				for (int i = 0; i < divClassesDIST.length; i++) {
					Elements link = doc.select(divClassesDIST[i]);
					ans = link.text();
					if (ans.length() > 1) {
						break;
					}
				}
				String[] s = ans.split(" ");
				if (s[1].contains("km")) {
					s[1] = "kilometers";
				} else if (s[1].contains("m")) {
					s[1] = "meters";
				}
				ans = s[0] + " " + s[1];
			}

			else if (-1 != query.indexOf("meaning of")) {
				for (int i = 0; i < divClassesMEAN.length; i++) {
					Elements link = doc.select(divClassesMEAN[i]);
					ans = link.text();
					if (ans.length() > 1) {
						break;
					}
				}
			}

			else if (-1 != query.indexOf("ip")) {
				for (int i = 0; i < divClassesIP.length; i++) {
					Elements link = doc.select(divClassesIP[i]);
					ans = link.text();
					if (ans.length() > 1) {
						break;
					}
				}
			}

			else {
				for (int i = 0; i < divClassesDIR.length; i++) {
					Elements link = doc.select(divClassesDIR[i]);
					ans = link.text();
					if (ans.length() > 1) {
						break;
					}
				}
				if (ans.contains("m / s")) {
					ans = ans.replaceAll("m / s", " meters per second");
				} else if (ans.contains("km/h")) {
					ans = ans.replaceAll("km/h", " kilometers per hour");
				}

			}

			if (ans.length() < 1) {
				String result = new GetLink().findUrl(query);
				if (result.equalsIgnoreCase("failure")) {
					new Actions().googleSearch(query);
				}
			}
			System.out.println(ans);
			new TTSMbrola().getString(ans);
		} catch (Exception e) {

		}

	}

}
