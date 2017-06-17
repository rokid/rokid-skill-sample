package com.rokid.simple.dto.res.response.action.display;

import java.io.Serializable;
import java.util.HashMap;

public class ResDisplay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;// "SHOW / DISMISS"
	private boolean needEventCallback;
	private int duration;
	private HashMap<String, String> template;// TODO

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isNeedEventCallback() {
		return needEventCallback;
	}

	public void setNeedEventCallback(boolean needEventCallback) {
		this.needEventCallback = needEventCallback;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public HashMap<String, String> getTemplate() {
		return template;
	}

	public void setTemplate(HashMap<String, String> template) {
		this.template = template;
	}

}
