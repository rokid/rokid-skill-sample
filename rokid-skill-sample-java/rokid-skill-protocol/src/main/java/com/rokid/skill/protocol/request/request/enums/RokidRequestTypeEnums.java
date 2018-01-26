package com.rokid.skill.protocol.request.request.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 请求类型
 * 
 * @author Bassam
 *
 */
public enum RokidRequestTypeEnums {
	EVENT_REQUEST("EVENT", "事件类型"), // 通过激活词打开应用时会发送这个Intent

	INTENT_REQUEST("INTENT", "语音请求类型");

	private String type;
	private String cnHit; // 中文提示，仅作阅读代码的提示。

	public String getCnHit() {
		return cnHit;
	}

	public void setCnHit(String cnHit) {
		this.cnHit = cnHit;
	}

	RokidRequestTypeEnums(String type, String cnHit) {
		this.type = type;
		this.cnHit = cnHit;
	}

	public static RokidRequestTypeEnums convert(String type) {
		for (RokidRequestTypeEnums v : RokidRequestTypeEnums.values()) {
			if (StringUtils.equals(v.type, type)) {
				return v;
			}
		}
		return null;
	}
}
