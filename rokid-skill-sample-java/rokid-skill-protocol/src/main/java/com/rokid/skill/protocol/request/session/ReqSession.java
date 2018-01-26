package com.rokid.skill.protocol.request.session;
import java.io.Serializable;
import java.util.HashMap;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -845013936250311340L;
	private String sessionId;//每次会话的唯一ID，由系统填充
	private boolean newSession;//是否是新会话
	private HashMap<String, Object> attributes;//为CloudApp提供attributes字段留保存上下文信息的字段

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isNewSession() {
		return newSession;
	}

	public void setNewSession(boolean newSession) {
		this.newSession = newSession;
	}

	public HashMap<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
