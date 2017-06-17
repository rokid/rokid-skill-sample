package com.rokid.simple.dto.req.request;

import java.io.Serializable;

import com.rokid.simple.dto.req.request.intent.ReqIntent;

public class ReqRequestContent<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reqType;// "intent / event"
	private String reqId;
	private String currentReqId;
	private ReqIntent<T> content;

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getCurrentReqId() {
		return currentReqId;
	}

	public void setCurrentReqId(String currentReqId) {
		this.currentReqId = currentReqId;
	}

	public ReqIntent<T> getContent() {
		return content;
	}

	public void setContent(ReqIntent<T> content) {
		this.content = content;
	}

}
