package com.rokid.skill.protocol.response.response.action.directive.media.mediaitem;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Bassam on 25/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResMediaItem implements Serializable {
	public static String MEDIA_TYPE_AUDIO = "AUDIO";
	public static String MEDIA_TYPE_VIDEO = "VIDEO";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5505517316901645087L;
	private String itemId;
	private String token;
	private String type;
	private String url;
	private Long offsetInMilliseconds;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getOffsetInMilliseconds() {
		return offsetInMilliseconds;
	}

	public void setOffsetInMilliseconds(Long offsetInMilliseconds) {
		this.offsetInMilliseconds = offsetInMilliseconds;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
