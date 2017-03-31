package com.rokid.simple.dto.res.session;

import java.io.Serializable;
import java.util.HashMap;

public class ResSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> attributes;

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

}
