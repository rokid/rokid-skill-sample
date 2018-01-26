package com.rokid.skill.exception;

/**
 * 语音业务异常情况
 * 
 * @author Bassam
 *
 */
public class RokidSpeechletException extends RokidException {

	private static final long serialVersionUID = 9135801019232303753L;
	private String actionType;
	private String actionName;

	public RokidSpeechletException(int httpStatusCode, String errorCode, String message, Throwable throwable) {
		super(httpStatusCode, errorCode, message, throwable);
	}

	public RokidSpeechletException(int httpStatusCode, String errorCode, String message) {
		super(httpStatusCode, errorCode, message);
	}

	public RokidSpeechletException(int httpStatusCode, String errorCode, Throwable throwable) {
		super(httpStatusCode, errorCode, throwable);
	}

	public RokidSpeechletException(int httpStatusCode, String errorCode) {
		super(httpStatusCode, errorCode);
	}

	public RokidSpeechletException(int httpStatusCode, String errorCode, String actionType, String actionName) {
		super(httpStatusCode, errorCode);
		this.actionType = actionType;
		this.actionName = actionName;
	}

	public RokidSpeechletException(int httpStatusCode, String errorCode, String actionType, String actionName,
			Throwable throwable) {
		super(httpStatusCode, errorCode, throwable);
		this.actionType = actionType;
		this.actionName = actionName;
	}

	public RokidSpeechletException(int httpStatusCode, String errorCode, String actionType, String actionName,
			String message, Throwable throwable) {
		super(httpStatusCode, errorCode, message, throwable);
		this.actionType = actionType;
		this.actionName = actionName;
	}

	public RokidSpeechletException(int httpStatusCode, String errorCode, String actionType, String actionName,
			String message) {
		super(httpStatusCode, errorCode, message);
		this.actionType = actionType;
		this.actionName = actionName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
