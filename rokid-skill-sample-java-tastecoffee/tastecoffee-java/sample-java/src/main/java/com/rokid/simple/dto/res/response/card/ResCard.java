package com.rokid.simple.dto.res.response.card;

import java.io.Serializable;
import java.util.HashMap;

public class ResCard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String version;
	private String type;
	private HashMap<String,String>template;//TODO
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
	public HashMap<String, String> getTemplate() {
		return template;
	}
	public void setTemplate(HashMap<String, String> template) {
		this.template = template;
	}

}
