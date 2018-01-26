package com.rokid.skill.protocol.request.request.content.enums;

import org.apache.commons.lang.StringUtils;

public enum RokidEventCommonEnums {

	// EVENT
	SKILL_EXIT_EVENT("Skill.EXIT", "skillSuspendEvent", "EventRequest,event=Skill.EXIT"), // 当应用挂起的时候 TODO 需要修改名字

	SKILL_SUSPEND_EVENT("Skill.SUSPEND", "skillSuspendEvent", "EventRequest,event=Skill.SUSPEND"), // 当应用挂起的时候的事件

	SESSION_ENDED_EVENT("Session.ENDED", "sessionEndedEvent", "EventRequest,event=Session.ENDED");// 当Domain被切换到的时候的事件，可以用于关闭资源

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

	RokidEventCommonEnums(String event, String methodName, String cnHit) {
		this.event = event;
		this.methodName = methodName;
		this.cnHit = cnHit;
	}

	public static RokidEventCommonEnums convert(String event) {
		for (RokidEventCommonEnums v : RokidEventCommonEnums.values()) {
			if (StringUtils.equals(v.event, event)) {
				return v;
			}
		}
		return null;
	}
}
