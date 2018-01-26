package com.rokid.skill.protocol.request.context.application.voice;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqApplicationVoice implements Serializable {
	public static final String STATE_PLAYING = "PLAYING";// 当前在播放状态
	public static final String STATE_PAUSED = "PAUSED";// 当前播放器在暂停状态
	public static final String STATE_IDLE = "IDLE";// 当前播放器状态在闲置状态
	public static final String STATE_UNKNOW = "UNKNOW";// 未知状态
	/**
	 * 
	 */
	private static final long serialVersionUID = -6035458330596077339L;
	private String state;// "PLAYING / PAUSED / IDLE",
	private String itemId;

	public String getState() {
		return state;
	}

	public String getItemId() {
		return itemId;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
