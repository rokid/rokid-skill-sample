package com.rokid.skill.protocol.response;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.ResResponseContent;
import com.rokid.skill.protocol.response.session.ResSession;
/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResResponse implements Serializable {
	public static final String RES_VERSION_V2 = "2.0.0";// 协议版本
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4299224159622592090L;
	private String version;// 表明了Response协议的版本，必须由 CloudApp 填充。当前协议版本是 2.0.0.
	private ResSession session;// 表示当前应用的session，与Request中的信息一致，CloudApp 可以在
								// attributes 里填充自己需要的上下文信息用于后面的请求。
	private ResResponseContent response;// 返回给 CloudAppClient 的Response内容。包括了
										// card 和 action 两个部分。card
										// 会在之后的协议更新中作详细说明。

	public ResResponse() {
		ResSession resSession = new ResSession();
		this.session = resSession;
		ResResponseContent resResponseContent = new ResResponseContent();
		this.response = resResponseContent;
	}

	public ResResponse(String resVersion) {
		this();
		this.version = resVersion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ResSession getSession() {
		return session;
	}

	public void setSession(ResSession session) {
		this.session = session;
	}

	public ResResponseContent getResponse() {
		return response;
	}

	public void setResponse(ResResponseContent response) {
		this.response = response;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
