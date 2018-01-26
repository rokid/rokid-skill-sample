package com.rokid.skill.protocol.request.request.content.enums;

import org.apache.commons.lang.StringUtils;

public enum RokidEventMediaEnums {

	// EVENT
	MEDIA_STARTED_EVENT("Media.STARTED", "mediaStartedEvent", "EventRequest,event=Media.STARTED"), // 当MediaPlayer开始播放时发生。

	MEDIA_FAILED_EVENT("Media.FAILED", "mediaFailedEvent", "EventRequest,event=Media.FAILED"), // 当MediaPlayer播放失败时发生。

	MEDIA_PAUSED_EVENT("Media.PAUSED", "mediaPausedEvent", "EventRequest,event=Media.PAUSED"), // 当MediaPlayer中途停止时发生。

	MEDIA_FINISHED_EVENT("Media.FINISHED", "mediaFinishedEvent", "EventRequest,event=Media.FINISHED");// 当播放内容结束时发生。
	
	private String event;
	private String methodName;
	private String cnHit; // 中文提示，仅作阅读代码的提示。

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getCnHit() {
		return cnHit;
	}

	public void setCnHit(String cnHit) {
		this.cnHit = cnHit;
	}

	RokidEventMediaEnums(String event, String methodName, String cnHit) {
		this.event = event;
		this.methodName = methodName;
		this.cnHit = cnHit;
	}

	public static RokidEventMediaEnums convert(String event) {
		for (RokidEventMediaEnums v : RokidEventMediaEnums.values()) {
			if (StringUtils.equals(v.event, event)) {
				return v;
			}
		}
		return null;
	}
}
