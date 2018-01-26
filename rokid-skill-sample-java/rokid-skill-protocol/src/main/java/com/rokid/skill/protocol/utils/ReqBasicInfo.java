package com.rokid.skill.protocol.utils;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ReqBasicInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String protocolVersion;// 当前协议版本
	private String requestId;// 请求ID
	private String sessionId;// SessionId
	private String appId;// 应用ID
	private String deviceId;// 设备ID
	private String deviceType;// 设备类型
	private String deviceVendor;// 厂商ID
	private String masterId;// 设备主人的ID
	private String userId;// 用户id
	private String locale;// 用户语言
	private String actionType;// 操作类型
	private String actionName;// 操作名称
	private long timestamp;// 设备时间戳
	private String voicetrigger;// 当前设备激活词

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public String getRequestId() {
		return requestId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getAppId() {
		return appId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getDeviceVendor() {
		return deviceVendor;
	}

	public String getMasterId() {
		return masterId;
	}

	public String getUserId() {
		return userId;
	}

	public String getLocale() {
		return locale;
	}

	public String getActionType() {
		return actionType;
	}

	public String getActionName() {
		return actionName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public void setDeviceVendor(String deviceVendor) {
		this.deviceVendor = deviceVendor;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getVoicetrigger() {
		return voicetrigger;
	}

	public void setVoicetrigger(String voicetrigger) {
		this.voicetrigger = voicetrigger;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
