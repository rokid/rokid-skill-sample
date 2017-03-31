package com.rokid.simple.dto.req.context;

import java.io.Serializable;

import com.rokid.simple.dto.req.context.application.ReqApplication;
import com.rokid.simple.dto.req.context.device.ReqDevice;
import com.rokid.simple.dto.req.context.user.ReqUser;

public class ReqContext implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReqApplication application;
	private ReqUser user;
	private ReqDevice device;
	public ReqApplication getApplication() {
		return application;
	}
	public void setApplication(ReqApplication application) {
		this.application = application;
	}
	public ReqUser getUser() {
		return user;
	}
	public void setUser(ReqUser user) {
		this.user = user;
	}
	public ReqDevice getDevice() {
		return device;
	}
	public void setDevice(ReqDevice device) {
		this.device = device;
	}
	
	
	
	

}
