package com.rokid.simple.dto.res.response.action.voice.item.confirm;

import java.io.Serializable;
import java.util.HashMap;

public class ResVoiceConfirm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tts;
	private String confirmIntent;
	private String confirmSlot;
	private HashMap<String, String> confirmAttributes;

	public String getTts() {
		return tts;
	}

	public void setTts(String tts) {
		this.tts = tts;
	}

	public String getConfirmIntent() {
		return confirmIntent;
	}

	public void setConfirmIntent(String confirmIntent) {
		this.confirmIntent = confirmIntent;
	}

	public String getConfirmSlot() {
		return confirmSlot;
	}

	public void setConfirmSlot(String confirmSlot) {
		this.confirmSlot = confirmSlot;
	}

	public HashMap<String, String> getConfirmAttributes() {
		return confirmAttributes;
	}

	public void setConfirmAttributes(HashMap<String, String> confirmAttributes) {
		this.confirmAttributes = confirmAttributes;
	}

}
