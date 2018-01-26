package com.rokid.skill.protocol.request.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.request.request.content.ReqRequestContent;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRequestValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6876155972421972250L;
	public static final String REQTYPE_INTENT = "INTENT";
	public static final String REQTYPE_EVENT = "EVENT";
	private String reqType;// 表明请求的类型： INTENT 和 EVENT 分别对应 IntentRequest 和
							// EventRequest。
	private String reqId;// 每次请求都会对应一个唯一ID用以区分每一次的请求。请求ID将会与返回ID一一对应。
	private ReqRequestContent content;// IntentRequest 或 EventRequest的对象

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

	public ReqRequestContent getContent() {
		return content;
	}

	public void setContent(ReqRequestContent content) {
		this.content = content;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
