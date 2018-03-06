package com.apk.jarvisai;

//WIP
public class JarvisInteractionEngine {

	TypeWriter typeWriter = new TypeWriter();

	public void interact(String actionType) {
		if (actionType.equalsIgnoreCase("save file")) {
			new TTSMbrola().getString("Where do you want me to save this file");
		} else if (-1 != actionType.indexOf("save it in a") || -1 != actionType.indexOf("put it in a")) {
			typeWriter.backSpace();
			String[] a = actionType.split(" ");
			if (a[4].equalsIgnoreCase("d")) {
				typeWriter.type("D:");
				typeWriter.enter();
			} else if (a[4].equalsIgnoreCase("e")) {
				typeWriter.type("D:");
				typeWriter.enter();
			}
			new TTSMbrola().getString("What should I call it");
		} else if (-1 != actionType.indexOf("save as") || -1 != actionType.indexOf("call it")) {
			String[] a = actionType.split(" ");
			String fileName = a[2];
			typeWriter.backSpace();
			typeWriter.type(fileName);
			typeWriter.enter();
			new TTSMbrola().getString("Your file has been saved sir.");
		}
	}
}
