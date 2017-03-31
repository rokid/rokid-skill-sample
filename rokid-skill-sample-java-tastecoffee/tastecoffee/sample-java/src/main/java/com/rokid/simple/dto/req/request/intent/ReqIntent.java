package com.rokid.simple.dto.req.request.intent;

import java.io.Serializable;

public class ReqIntent<T> implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String voice;
	private String domain;
	private String intent;
	private T slots;

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public T getSlots() {
		return slots;
	}

	public void setSlots(T slots) {
		this.slots = slots;
	}

}
