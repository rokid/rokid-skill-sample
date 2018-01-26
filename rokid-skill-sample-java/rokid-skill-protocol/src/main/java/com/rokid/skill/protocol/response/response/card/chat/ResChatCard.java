package com.rokid.skill.protocol.response.response.card.chat;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.card.ResCard;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResChatCard extends ResCard implements Serializable {
	private static final String CARD_TYPE = "chat";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5802546637629946742L;

	public ResChatCard() {
		setType(CARD_TYPE);
	}

	public ResChatCard(String tts) {
		setType(CARD_TYPE);
		setContent(getTTSContent(tts));
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String getTTSContent(String tts) {
		return tts.replaceAll("<[^>]*>", "").replaceAll("\\[[^]]*\\]", "");
	}

}
