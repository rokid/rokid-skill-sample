package com.rokid.cas.protocol.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TestSlots implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5673367771389578577L;
	private String song;

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
