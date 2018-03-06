package com.apk.jarvisai;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

public class Actions {
	String cmd;

	public void getString(String cmd) {
		this.cmd = cmd;
		if (cmd != null) {
			try {
				performAction();
			} catch (Exception e) {
				System.out.println("Error in action class");
				e.printStackTrace();
			}
		}
	}

	public void performAction() throws Exception {
		if (cmd.equals("mycomp")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
		}
		if (cmd.equalsIgnoreCase("chrome")) {
			RegistryQuery rq = new RegistryQuery();
			String path = rq.getRegistryPath("chrome.exe");
			path = path.replaceAll("\\\\", "/");
			System.out.println(path);
			Runtime.getRuntime().exec(path + "/chrome.exe", null, new File(path + "/"));
		}
	}

	public void openApp(String appName) {
		try {
			RegistryQuery rq = new RegistryQuery();
			String app = appName.concat(".exe");
			String path = rq.getRegistryPath(app);
			path = path.replaceAll("\\\\", "/");
			System.out.println(path);
			Runtime.getRuntime().exec(path);
		} catch (Exception e) {

		}
	}

	public void closeApp(String appName) {
		try {
			System.out.println(appName);
			String app = appName.concat(".exe");
			System.out.println("Closing " + app);
			Runtime.getRuntime().exec("taskkill /F /IM " + app);
		} catch (Exception e) {
			new TTSMbrola().getString(appName + " is not currently running");
		}
	}

	public void googleSearch(String query) {
		try {
			Runtime.getRuntime()
					.exec(new String[] { "cmd", "/c", "start chrome https://google.co.in/search?q=" + query });
		} catch (Exception e) {

		}
	}

}
