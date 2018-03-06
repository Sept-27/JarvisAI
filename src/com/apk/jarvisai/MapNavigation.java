package com.apk.jarvisai;

public class MapNavigation {

	public void searchOnMap(String location) {
		String query = "";
		String[] s = location.split(" ");
		if (s.length > 1) {
			for (int i = 0; i < s.length; i++) {
				if (i == s.length - 1) {
					query += s[i];
				} else {
					query += s[i] + "+";
				}
			}
			System.out.println(query);
		} else {
			query = s[0];
		}
		try {
			Runtime.getRuntime()
					.exec(new String[] { "cmd", "/c", "start chrome https://www.google.co.in/maps/place/" + query });
		} catch (Exception e) {

		}
	}

	public void getDirections(String location) {
		String toLocation = "";
		String fromLocation = "";
		try {
			Runtime.getRuntime().exec(new String[] { "cmd", "/c",
					"start chrome https://www.google.co.in/maps/dir/" + fromLocation + "/" + toLocation });
		} catch (Exception e) {

		}
	}
}
