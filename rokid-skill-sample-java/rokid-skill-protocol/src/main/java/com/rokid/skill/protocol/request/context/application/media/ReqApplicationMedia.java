package com.rokid.skill.protocol.request.context.application.media;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqApplicationMedia implements Serializable {
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
	private String token;
	private Long progress;// 毫秒
	private Long duration;// 毫秒

	public String getState() {
		return state;
	}

	public String getItemId() {
		return itemId;
	}

	public String getToken() {
		return token;
	}

	public Long getProgress() {
		return progress;
	}

	public void setProgress(Long progress) {
		this.progress = progress;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
