package com.rokid.simple.dto.res.response.action.voice.item;

import java.io.Serializable;

import com.rokid.simple.dto.res.response.action.voice.item.confirm.ResVoiceConfirm;

public class ResVoiceItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tts;
	private ResVoiceConfirm confirm;

	public String getTts() {
		return tts;
	}

	public void setTts(String tts) {
		this.tts = tts;
	}

	public ResVoiceConfirm getConfirm() {
		return confirm;
	}

	public void setConfirm(ResVoiceConfirm confirm) {
		this.confirm = confirm;
	}

}
