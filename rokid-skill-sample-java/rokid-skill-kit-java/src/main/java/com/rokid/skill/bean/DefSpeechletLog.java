package com.rokid.skill.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 语音业务日志
 * 
 * @author Bassam
 *
 */
public class DefSpeechletLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1130343572705737587L;
	private String speechletId;// 语音请求ID
	private String requestVersion;// 请求的版本
	private String requestId;// 语音请求过来的内容Id
	private String sessionId;// Session的Id
	private String applicationId;// 应用ID
	private String appVersion;// 该字段目前填写请求的版本号
	private String userId;// 用户ID
	private String masterId;// 设备主人Id（Alexa没有这个字段）
	private String deviceId;// 设备Id
	private String deviceType;// 设备类型(ALexa没有这个字段)
	private String deviceVendor;// 设备认证码（Alexa没有这个字段）
	private String actionType;// 事件类型
	private String actionName;// 操作名称/事件名称
	private Long timesTamp;// 设备时间戳
	private String status;// 状态码
	private Long gmtCreated;// 创建时间

	public String getSpeechletId() {
		return speechletId;
	}

	public void setSpeechletId(String speechletId) {
		this.speechletId = speechletId;
	}

	public String getRequestVersion() {
		return requestVersion;
	}

	public void setRequestVersion(String requestVersion) {
		this.requestVersion = requestVersion;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceVendor() {
		return deviceVendor;
	}

	public void setDeviceVendor(String deviceVendor) {
		this.deviceVendor = deviceVendor;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Long getTimestamp() {
		return timesTamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timesTamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Long gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}