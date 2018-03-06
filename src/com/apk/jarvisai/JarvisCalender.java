package com.apk.jarvisai;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JarvisCalender {

	String month, date, year;
	String result;
	StringBuilder sb;
	Date dt;
	SimpleDateFormat df;
	boolean isDateSet, isMonthSet, isYearSet;

	TTSMbrola tts = new TTSMbrola();

	public void getCalender(String cmd) {
		String[] days = { "01", "1", "02", "2", "03", "3", "04", "4", "05", "5", "06", "6", "07", "7", "08", "8", "09",
				"9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31" };
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String[] array = cmd.split(" ");

		try {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < days.length; j++) {
					if (array[i].equals(days[j])) {
						date = array[i];
						isDateSet = true;
					}
				}
			}
			for (int i = 0; i < array.length; i++) {
				for (int k = 0; k < months.length; k++) {
					if (array[i].equals(months[k])) {
						month = array[i];
					}
				}
			}
		} catch (Exception e) {
			tts.getString("No such date");
			e.printStackTrace();
		}

		try {
			year = array[array.length - 1];
			if (year == null) {
				df = new SimpleDateFormat("yyyy");
				dt = new Date();
				year = df.format(dt);
			}
		} catch (Exception e) {
			System.out.println("Cannot parse year!!!");
			tts.getString("No year specified, hence I am considering current year");
		}
	}

	public String getMonth() {
		return month;
	}

	public String getDate() {
		return date;
	}

	public String getYear() {
		return year;
	}

	public String compileDate() {
		String mnth = getMonth();
		String date1 = getDate();
		String year1 = getYear();

		result = mnth + "/" + date1 + "/" + year1;
		return result;
	}
}
