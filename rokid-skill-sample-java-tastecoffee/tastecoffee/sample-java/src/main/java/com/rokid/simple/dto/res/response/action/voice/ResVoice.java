package com.rokid.simple.dto.res.response.action.voice;

import java.io.Serializable;

import com.rokid.simple.dto.res.response.action.voice.item.ResVoiceItem;

public class ResVoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean needEventCallback;
	private String behaviour;// APPEND/REPLACE_ALL/REPLACE_APPEND/CLEAR
	private ResVoiceItem item;

	public boolean isNeedEventCallback() {
		return needEventCallback;
	}

	public void setNeedEventCallback(boolean needEventCallback) {
		this.needEventCallback = needEventCallback;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public ResVoiceItem getItem() {
		return item;
	}

	public void setItem(ResVoiceItem item) {
		this.item = item;
	}

}
