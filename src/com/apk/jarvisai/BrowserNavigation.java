package com.apk.jarvisai;

import java.awt.Robot;

import com.sun.glass.events.KeyEvent;

public class BrowserNavigation {

	Robot robot;

	public void navigateTabBack() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {

		}
	}

	public void navigateTabForward() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {

		}
	}

	public void navigateToTabNumber(int number) {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			switch (number) {
			case 1:
				robot.keyPress(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_1);
				break;
			case 2:
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);
				break;
			case 3:
				robot.keyPress(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_3);
				break;
			case 4:
				robot.keyPress(KeyEvent.VK_4);
				robot.keyRelease(KeyEvent.VK_4);
				break;
			case 5:
				robot.keyPress(KeyEvent.VK_5);
				robot.keyRelease(KeyEvent.VK_5);
				break;
			case 6:
				robot.keyPress(KeyEvent.VK_6);
				robot.keyRelease(KeyEvent.VK_6);
				break;
			case 7:
				robot.keyPress(KeyEvent.VK_7);
				robot.keyRelease(KeyEvent.VK_7);
				break;
			case 8:
				robot.keyPress(KeyEvent.VK_8);
				robot.keyRelease(KeyEvent.VK_8);
				break;
			}
			robot.keyRelease(KeyEvent.VK_CONTROL);

		} catch (Exception e) {

		}

	}

	public void closeTab() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {

		}
	}

	public void closeTab(int number) {
		navigateToTabNumber(number);
		closeTab();
	}

	public void NavigatePageBack() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyRelease(KeyEvent.VK_LEFT);
			robot.keyRelease(KeyEvent.VK_ALT);
		} catch (Exception e) {

		}
	}

	public void NavigatePageForward() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_ALT);
		} catch (Exception e) {

		}
	}

	public void reloadPage() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_R);
			robot.keyRelease(KeyEvent.VK_R);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {

		}
	}

	public void findOnPage() {

	}

	public void saveAsBookmark() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {

		}

	}

	public void goToDownloads() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {

		}
	}

	public void openNewTab() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {

		}
	}

	public void closeActiveChromeWindw() {
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {

		}
	}
}
