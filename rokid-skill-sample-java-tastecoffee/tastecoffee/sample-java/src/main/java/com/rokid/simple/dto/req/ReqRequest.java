package com.rokid.simple.dto.req;

import java.io.Serializable;

import com.rokid.simple.dto.req.context.ReqContext;
import com.rokid.simple.dto.req.request.ReqRequestContent;
import com.rokid.simple.dto.req.session.ReqSession;

public class ReqRequest<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String version;
	private ReqSession session;
	private ReqContext context;
	private ReqRequestContent<T> request;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ReqSession getSession() {
		return session;
	}

	public void setSession(ReqSession session) {
		this.session = session;
	}

	public ReqContext getContext() {
		return context;
	}

	public void setContext(ReqContext context) {
		this.context = context;
	}

	public ReqRequestContent<T> getRequest() {
		return request;
	}

	public void setRequest(ReqRequestContent<T> request) {
		this.request = request;
	}

}
