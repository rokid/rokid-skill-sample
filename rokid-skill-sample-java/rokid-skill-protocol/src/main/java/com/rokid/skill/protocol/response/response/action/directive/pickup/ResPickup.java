package com.rokid.skill.protocol.response.response.action.directive.pickup;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.action.directive.ResDirective;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResPickup extends ResDirective implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1498955831240619651L;
	private boolean enable;
	private Long durationInMilliseconds;
	private String retryTts;// 重试一次的TTS

	public ResPickup() {
		setType("pickup");
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Long getDurationInMilliseconds() {
		return durationInMilliseconds;
	}

	public void setDurationInMilliseconds(Long durationInMilliseconds) {
		this.durationInMilliseconds = durationInMilliseconds;
	}

	public String getRetryTts() {
		return retryTts;
	}

	public void setRetryTts(String retryTts) {
		this.retryTts = retryTts;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
