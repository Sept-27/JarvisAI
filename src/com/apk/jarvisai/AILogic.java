package com.apk.jarvisai;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.apk.gmail.MailCheck;

public class AILogic {

	TTSMbrola tts = new TTSMbrola();
	JarvisCalender cal = new JarvisCalender();
	Actions actions = new Actions();
	JarvisInteractionEngine engine = new JarvisInteractionEngine();
	Date date;
	String day;
	SimpleDateFormat df;
	boolean queryAnswred;
	boolean isTypingModeOn = false;
	boolean isMapRequested = false;
	boolean isMailCheck = true;

	public void checkCommand(String cmd) throws Exception {
		cmd = cmd.toLowerCase();
		queryAnswred = false;
		if (!isTypingModeOn) {

			if (cmd.equalsIgnoreCase("jarvis")) {
				tts.getString("yes sir");
				queryAnswred = true;
			}

			if (cmd.contains("good") || cmd.contains("hello")) {
				date = new Date();
				df = new SimpleDateFormat("H");
				int hr1 = Integer.parseInt(df.format(date));
				df = new SimpleDateFormat("m");
				if (-1 != cmd.indexOf("night") || -1 != cmd.indexOf("night jarvis") || -1 != cmd.indexOf("morning")
						|| -1 != cmd.indexOf("morning jarvis") || -1 != cmd.indexOf("evening")
						|| -1 != cmd.indexOf("evening jarvis") || -1 != cmd.indexOf("afternoon")
						|| -1 != cmd.indexOf("afternoon jarvis") || -1 != cmd.indexOf("jarvis")
						|| -1 != cmd.indexOf("hello")) {
					if (hr1 >= 22 && hr1 <= 3) {
						tts.getString("Good night sir");
					}
					if (hr1 >= 4 && hr1 <= 11) {
						tts.getString("Good Morning Sir");
					}
					if (hr1 >= 12 && hr1 <= 16) {
						tts.getString("Good afternoon sir");
					}
					if (hr1 >= 17 && hr1 <= 21) {
						tts.getString("Good evening sir");
					}
					queryAnswred = true;
				}
			}

			if (cmd.contains("are you")) {
				if (-1 != cmd.indexOf("there") || -1 != cmd.indexOf("up") || -1 != cmd.indexOf("there jarvis")
						|| -1 != cmd.indexOf("there buddy")) {
					tts.getString("At your service sir");
				}
				if (-1 != cmd.indexOf("alive")) {
					tts.getString("I am very much alive sir");
				}
				queryAnswred = true;
			}

			if (cmd.contains("what") || cmd.contains("what is") || cmd.contains("what's") || cmd.contains("whats")
					|| cmd.contains("tell me") || cmd.contains("could you") || cmd.contains("do you")) {

				if (-1 != cmd.indexOf("the time buddy") || -1 != cmd.indexOf("the time jarvis")
						|| -1 != cmd.indexOf("tell me the time please") || -1 != cmd.indexOf("time")
						|| -1 != cmd.indexOf("time please")) {
					date = new Date();
					df = new SimpleDateFormat("h");
					String h = df.format(date);
					df = new SimpleDateFormat("m");
					String m = df.format(date);
					df = new SimpleDateFormat("a");
					String s = df.format(date);
					tts.getString("Time is");

					if (s.equalsIgnoreCase("am")) {
						tts.getString(h);
						tts.getString(m);
						tts.getString(" a m");
					} else if (s.equalsIgnoreCase("pm")) {
						tts.getString(h);
						tts.getString(m);
						tts.getString(" p m");
					}
					queryAnswred = true;
				}

				if (-1 != cmd.indexOf("your name") || -1 != cmd.indexOf("have any name")) {
					if (-1 != cmd.indexOf("do you have any name")) {
						tts.getString("yes, I do");
					}
					tts.getString("my name is jarvis");
					queryAnswred = true;
				}

				if (-1 != cmd.indexOf("today")) {
					date = new Date();
					df = new SimpleDateFormat("EEEE");
					day = df.format(date);
					tts.getString("today is");
					tts.getString(day);
					queryAnswred = true;
				}

				if (cmd.contains("day is on") || cmd.contains("day was on") || cmd.contains("is on")
						|| cmd.contains("on") || cmd.contains("day on")) {
					if (cmd.contains("was")) {
						tts.getString("it was");

					} else if (cmd.contains("is")) {
						tts.getString("its");
					}
					cal.getCalender(cmd);
					df = new SimpleDateFormat("MMMM/dd/yyyy");
					try {
						cal.getCalender(cmd);
						date = df.parse(cal.compileDate());
						df = new SimpleDateFormat("EEEE");
						day = df.format(date);
						tts.getString(day);
					} catch (Exception e) {
						tts.getString("no such date");
						e.printStackTrace();
					}
					queryAnswred = true;
				}
			}

			if (isMapRequested) {
				new MapNavigation().searchOnMap(cmd);
				this.isMapRequested = false;
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("show me a map")) {
				this.isMapRequested = true;
				tts.getString("for which location, sir");
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("open my computer")) {
				actions.getString("mycomp");
				queryAnswred = true;
			}
			if (-1 != cmd.indexOf("open")) {
				String action = "";
				String[] s = cmd.split(" ");
				if (s.length > 2) {
					for (int i = 1; i < s.length; i++) {
						action += s[i];
					}
					System.out.println(action);
					actions.openApp(action);
					;
				}
				actions.openApp(s[1]);
				queryAnswred = true;
			}
			if (-1 != cmd.indexOf("close")) {
				String action = "";
				String[] s = cmd.split(" ");
				if (s.length > 2) {
					for (int i = 1; i < s.length; i++) {
						action += s[i];
					}
					System.out.println(action);
					actions.closeApp(action);
					;
				} else {
					actions.closeApp(s[1]);
				}
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("google search") || -1 != cmd.indexOf("look for")) {
				String query = "";
				String[] s = cmd.split(" ");
				for (int i = 2; i < s.length; i++) {
					if (i == s.length - 1) {
						query += s[i];
					} else {
						query += s[i] + "+";
					}

				}
				System.out.println(query);
				actions.googleSearch(query);
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("go to tab")) {
				String[] s = cmd.split(" ");
				new BrowserNavigation().navigateToTabNumber(Integer.valueOf(s[3]));
				queryAnswred = true;
			}

			if (cmd.equalsIgnoreCase("close this tab")) {
				new BrowserNavigation().closeTab();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("close tab")) {
				if (cmd.length() > 2) {
					String[] s = cmd.split(" ");
					new BrowserNavigation().closeTab(Integer.valueOf(s[2]));
				} else {
					new BrowserNavigation().closeTab();
				}
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("go to previous tab") || -1 != cmd.indexOf("switch to previous tab")
					|| -1 != cmd.indexOf("previous tab")) {
				new BrowserNavigation().navigateTabBack();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("go to next tab") || -1 != cmd.indexOf("switch to next tab")
					|| -1 != cmd.indexOf("next tab")) {
				new BrowserNavigation().navigateTabForward();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("go to previous page") || -1 != cmd.indexOf("go back")
					|| -1 != cmd.indexOf("previous page")) {
				new BrowserNavigation().NavigatePageBack();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("go to next page") || -1 != cmd.indexOf("next") || -1 != cmd.indexOf("next page")) {
				new BrowserNavigation().NavigatePageForward();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("bookmark this") || -1 != cmd.indexOf("mark this page")) {
				new BrowserNavigation().saveAsBookmark();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("show downloads") || -1 != cmd.indexOf("go to downloads")
					|| -1 != cmd.indexOf("show download")) {
				new BrowserNavigation().goToDownloads();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("reload")) {
				new BrowserNavigation().reloadPage();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("open new tab") || -1 != cmd.indexOf("new tab")) {
				new BrowserNavigation().openNewTab();
				queryAnswred = true;
			}

			if (-1 != cmd.indexOf("typing mode on")) {
				this.isTypingModeOn = true;
				queryAnswred = true;
				System.out.println("Typing mode on.");
			}

			if (isMailCheck) {
				if (-1 != cmd.indexOf("from who")) {

				}
			}

			if (-1 != cmd.indexOf("check my mail")) {
				MailCheck.getMailCount();
				isMailCheck = true;
				queryAnswred = true;
			}

			else if (!queryAnswred) {
				String query = "";
				String[] s = cmd.split(" ");
				if (s.length > 1) {
					for (int i = 0; i < s.length; i++) {
						if (i == s.length - 1) {
							query += s[i];
						} else {
							query += s[i] + "+";
						}
					}
					System.out.println(query);
				} else if (s.length == 1) {
					query = cmd;
				}
				new QuickAns().getAns(query);
			}
		} else if (isTypingModeOn) {
			if (-1 != cmd.indexOf("typing mode off")) {
				this.isTypingModeOn = false;
				System.out.println("Typing mode off.");
			} else {
				new TypeWriter().type(cmd);
			}
		}
	}

}
