package com.rokid.simple.dto.res.response.action;

import java.io.Serializable;

import com.rokid.simple.dto.res.response.action.display.ResDisplay;
import com.rokid.simple.dto.res.response.action.media.ResMedia;
import com.rokid.simple.dto.res.response.action.voice.ResVoice;

public class ResAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String version;
	private String type;// "NORMAL / EXIT"
	private boolean shoudEndSession;
	private ResVoice voice;
	private ResDisplay display;
	private ResMedia media;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isShoudEndSession() {
		return shoudEndSession;
	}

	public void setShoudEndSession(boolean shoudEndSession) {
		this.shoudEndSession = shoudEndSession;
	}

	public ResVoice getVoice() {
		return voice;
	}

	public void setVoice(ResVoice voice) {
		this.voice = voice;
	}

	public ResDisplay getDisplay() {
		return display;
	}

	public void setDisplay(ResDisplay display) {
		this.display = display;
	}

	public ResMedia getMedia() {
		return media;
	}

	public void setMedia(ResMedia media) {
		this.media = media;
	}

}
