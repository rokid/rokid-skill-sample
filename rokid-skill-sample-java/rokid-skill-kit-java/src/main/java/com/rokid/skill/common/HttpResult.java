package com.rokid.skill.common;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Http请求结果
 * 
 * @author Bassam
 *
 */
public class HttpResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1280583941684134735L;
	private int httpcode;// Http请求状态码
	private String result;// Http响应结果
	private String errorMessage;// Http异常信息

	public int getHttpcode() {
		return httpcode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setHttpcode(int httpcode) {
		this.httpcode = httpcode;
	}

	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
