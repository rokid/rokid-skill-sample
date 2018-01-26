package com.rokid.skill.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 系统请求日志信息
 * 
 * @author Bassam
 *
 */
public class DefServiceLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5417626676633419146L;
	private String speechletId;// 语音请求ID
	private String requestIp;// 请求过来的用户IP
	private String serverIp;// 当前服务器的IP（用于判断是那台服务器处理了这个请求）
	private String serviceName;// 服务名称
	private String serviceVersion;// 系统服务版本
	private String methodName;// 方法名称
	private String requestHeader;// 请求头
	private String requestBody;// 请求体
	private String status;// 状态
	private String result;// 返回结果
	private String exception;// 异常记录信息
	private Long gmtCreated;// 请求时间
	private Long costsTime;// 耗时

	public String getSpeechletId() {
		return speechletId;
	}

	public void setSpeechletId(String speechletId) {
		this.speechletId = speechletId;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(String requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Long getCostsTime() {
		return costsTime;
	}

	public void setCostsTime(Long costsTime) {
		this.costsTime = costsTime;
	}

	public Long getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Long gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}