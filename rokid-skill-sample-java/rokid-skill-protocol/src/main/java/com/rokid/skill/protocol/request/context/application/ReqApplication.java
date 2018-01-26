package com.rokid.skill.protocol.request.context.application;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqApplication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6035458330596077339L;
	private String applicationId;// 当前的应用ID
	private ReqApplicationMedia media;
	private ReqApplicationVoice voice;

	public String getApplicationId() {
		return applicationId;
	}

	public ReqApplicationMedia getMedia() {
		return media;
	}

	public ReqApplicationVoice getVoice() {
		return voice;
	}

	public void setMedia(ReqApplicationMedia media) {
		this.media = media;
	}

	public void setVoice(ReqApplicationVoice voice) {
		this.voice = voice;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
