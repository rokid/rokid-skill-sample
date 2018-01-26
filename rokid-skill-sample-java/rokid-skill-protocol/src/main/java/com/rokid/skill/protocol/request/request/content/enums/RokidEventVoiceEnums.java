package com.rokid.skill.protocol.request.request.content.enums;

import org.apache.commons.lang.StringUtils;

public enum RokidEventVoiceEnums {

	// EVENT
	VOICE_STARTED_EVENT("Voice.STARTED", "voiceStartedEvent", "EventRequest,event=Voice.STARTED"), // 当Voice开始播放时发生。

	VOICE_FAILED_EVENT("Voice.FAILED", "voiceFailedEvent", "EventRequest,event=Voice.FAILED"), // 当Voice播放失败时发生。

	VOICE_FINISHED_EVENT("Voice.FINISHED", "voiceFinishedEvent", "EventRequest,event=Voice.FINISHED"); // 当Voice播放内容结束时发生。

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

	RokidEventVoiceEnums(String event, String methodName, String cnHit) {
		this.event = event;
		this.methodName = methodName;
		this.cnHit = cnHit;
	}

	public static RokidEventVoiceEnums convert(String event) {
		for (RokidEventVoiceEnums v : RokidEventVoiceEnums.values()) {
			if (StringUtils.equals(v.event, event)) {
				return v;
			}
		}
		return null;
	}
}
