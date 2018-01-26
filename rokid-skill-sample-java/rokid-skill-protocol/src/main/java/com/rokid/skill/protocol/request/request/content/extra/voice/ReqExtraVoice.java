package com.rokid.skill.protocol.request.request.content.extra.voice;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqExtraVoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4975096988067404659L;

	private String itemId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
