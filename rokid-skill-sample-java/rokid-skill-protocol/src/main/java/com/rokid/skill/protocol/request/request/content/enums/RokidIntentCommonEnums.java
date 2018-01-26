package com.rokid.skill.protocol.request.request.content.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 系统级的Intent事件
 * 
 * @author Bassam
 *
 */
public enum RokidIntentCommonEnums {
	WELECOME_INTENT("ROKID.INTENT.WELCOME", "appStartIntent", "打开应用事件"), // 通过激活词打开应用时会发送这个Intent

	UNKONWN_INTENT("ROKID.INTENT.UNKNOWN", "unkonwnIntent", "未知意图的Intent事件"), // 未知意图的Intent事件，当Skill开启PickUp以后，用户三次处理都出错，会命中该Intent

	EXIT_INTENT("ROKID.INTENT.EXIT", "appStopIntent", "退出应用事件");// Skill退出请求

	private String intent;
	private String methodName;
	private String cnHit; // 中文提示，仅作阅读代码的提示。

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
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

	RokidIntentCommonEnums(String intent, String methodName, String cnHit) {
		this.intent = intent;
		this.methodName = methodName;
		this.cnHit = cnHit;
	}

	public static RokidIntentCommonEnums convert(String intent) {
		for (RokidIntentCommonEnums v : RokidIntentCommonEnums.values()) {
			if (StringUtils.equals(v.intent, intent)) {
				return v;
			}
		}
		return null;
	}
}
