package com.rokid.skill.protocol.response.response.action.directive.voice;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import com.rokid.skill.protocol.response.response.action.directive.voice.item.ResVoiceItem;

/**
 * Created by Bassam on 25/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResVoice extends ResDirective implements Serializable {
	public static final String VOICE_ACTION_PLAY = "PLAY";// 从头开始播放操作，最好带上VoiceItem，否则一旦设备那边没有Item，则会出错
	public static final String VOICE_ACTION_PAUSE = "PAUSE";// 暂停播放操作，暂停当前的Voice播放
	public static final String VOICE_ACTION_RESUME = "RESUME";// 继续播放操作，继续当前的播放，需要注意的是客户端那边必须要有VoiceItem，否则会出错（可以校验一下当前的Voice状态是否是PAUSE的状态）
	public static final String VOICE_ACTION_STOP = "STOP";// 停止播放操作，停止当前的VOICE播放
	/**
	 * 
	 */
	private static final long serialVersionUID = -5413825634268582630L;
	private boolean disableEvent;
	private String action;// 播放控制操作 "PLAY/PAUSE/RESUME/STOP"
	private ResVoiceItem item;// tts 内容

	public ResVoice() {
		setType("voice");
	}

	public boolean isDisableEvent() {
		return disableEvent;
	}

	public void setDisableEvent(boolean disableEvent) {
		this.disableEvent = disableEvent;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ResVoiceItem getItem() {
		return item;
	}

	public void setItem(ResVoiceItem item) {
		this.item = item;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
