package com.rokid.skill.protocol.request.request.content.extra;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqEventExtra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6312382694862019116L;

	private ReqExtraMedia media;
	private ReqExtraVoice voice;

	public ReqExtraMedia getMedia() {
		return media;
	}

	public void setMedia(ReqExtraMedia media) {
		this.media = media;
	}

	public ReqExtraVoice getVoice() {
		return voice;
	}

	public void setVoice(ReqExtraVoice voice) {
		this.voice = voice;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
