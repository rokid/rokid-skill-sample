package com.rokid.simple.dto.res.response.action.media.item;

import java.io.Serializable;

public class ResMediaItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private String type;// AUDIO/VIDEO
	private String url;
	private int offsetInMilliseconds;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOffsetInMilliseconds() {
		return offsetInMilliseconds;
	}

	public void setOffsetInMilliseconds(int offsetInMilliseconds) {
		this.offsetInMilliseconds = offsetInMilliseconds;
	}

}
