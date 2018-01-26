package com.rokid.skill.protocol.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.request.context.ReqContext;
import com.rokid.skill.protocol.request.request.ReqRequestValue;
import com.rokid.skill.protocol.request.session.ReqSession;

/**
 * Created by Bassam on 15/03/2017.
 */

/**
 *
 *
 */
/**
 * @类描述 CloudApp协议，Request对象
 * @说明 Request主要包含四块内容 1、version：CloudApp协议版本； 2、session：当前的会话信息；
 *     3、context：设备、用户、以及其他状态信息； 4、request：业务请求内容
 * @param <T>
 *            Slots对象
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -490292141289020844L;
	/**
	 * 
	 */
	private String version;// 协议版本号，当前版本2.0.0
	private ReqSession session;// 会话的信息
	private ReqContext context;// 当前的设备信息，用户信息和应用状态
	private ReqRequestValue request;

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

	public ReqRequestValue getRequest() {
		return request;
	}

	public void setRequest(ReqRequestValue request) {
		this.request = request;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
