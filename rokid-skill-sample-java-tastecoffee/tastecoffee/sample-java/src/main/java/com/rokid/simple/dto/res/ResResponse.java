package com.rokid.simple.dto.res;

import java.io.Serializable;

import com.rokid.simple.dto.res.response.ResResponseContent;
import com.rokid.simple.dto.res.session.ResSession;

public class ResResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String version;
	private ResSession session;
	private ResResponseContent response;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ResSession getSession() {
		return session;
	}

	public void setSession(ResSession session) {
		this.session = session;
	}

	public ResResponseContent getResponse() {
		return response;
	}

	public void setResponse(ResResponseContent response) {
		this.response = response;
	}

}
