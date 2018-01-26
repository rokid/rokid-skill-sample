package com.rokid.skill.protocol.response.response.action.directive.voice.item;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * Created by Bassam on 25/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResVoiceItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2690547231606519182L;
	private String itemId;
	private String tts;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTts() {
		return tts;
	}

	public void setTts(String tts) {
		this.tts = tts;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
