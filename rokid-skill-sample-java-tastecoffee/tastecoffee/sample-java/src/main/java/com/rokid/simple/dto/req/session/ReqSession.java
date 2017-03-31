package com.rokid.simple.dto.req.session;

import java.io.Serializable;
import java.util.HashMap;

public class ReqSession implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sessionId;
	private boolean newSession;
	private String applicationId;
	private String domain;
	private HashMap<String,String>attributes;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public boolean isNewSession() {
		return newSession;
	}
	public void setNewSession(boolean newSession) {
		this.newSession = newSession;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
	
	

}
