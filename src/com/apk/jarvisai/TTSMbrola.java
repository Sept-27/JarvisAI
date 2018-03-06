package com.apk.jarvisai;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSMbrola {
	VoiceManager freettsVM;
	Voice freettsVoice;

	public void getString(String words) {
		System.setProperty("mbrola.base", "C:/Java libs/mbrola");
		freettsVM = VoiceManager.getInstance();
		freettsVoice = freettsVM.getVoice("mbrola_us1");
		freettsVoice.allocate();
		sayWords(words);
	}

	public void sayWords(String words) {
		freettsVoice.speak(words);
	}
}
