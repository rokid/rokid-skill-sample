package com.rokid.skill.protocol.request.context;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.request.context.application.ReqApplication;
import com.rokid.skill.protocol.request.context.device.ReqDevice;
import com.rokid.skill.protocol.request.context.user.ReqUser;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqContext implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5297187036481770538L;
	private ReqApplication application; //ApplicationInfo对象，目前只有应用ID
	private ReqDevice device;//此次请求发生时当前设备信息的描述
	private ReqUser user;//展示了与当前设备绑定的用户信息，通常是设备对应手机应用的账号

	public ReqApplication getApplication() {
		return application;
	}

	public void setApplication(ReqApplication application) {
		this.application = application;
	}

	public ReqDevice getDevice() {
		return device;
	}

	public void setDevice(ReqDevice device) {
		this.device = device;
	}

	public ReqUser getUser() {
		return user;
	}

	public void setUser(ReqUser user) {
		this.user = user;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
