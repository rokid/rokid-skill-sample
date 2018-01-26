package com.rokid.skill.protocol.response.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.action.ResAction;
import com.rokid.skill.protocol.response.response.card.ResCard;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResResponseContent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1480333446516655906L;
	private ResCard card;
	private ResAction action;//voice 和 media。voice 表示了语音交互的返回。media 是对媒体播放的返回。
	public ResCard getCard() {
		return card;
	}

	public void setCard(ResCard card) {
		this.card = card;
	}

	public ResAction getAction() {
		return action;
	}

	public void setAction(ResAction action) {
		this.action = action;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
