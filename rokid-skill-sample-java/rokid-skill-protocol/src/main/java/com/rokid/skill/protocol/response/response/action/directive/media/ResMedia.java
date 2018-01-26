package com.rokid.skill.protocol.response.response.action.directive.media;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import com.rokid.skill.protocol.response.response.action.directive.media.mediaitem.ResMediaItem;

/**
 * Created by Bassam on 25/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResMedia extends ResDirective implements Serializable {
	public static final String MEDIA_ACTION_PLAY = "PLAY";// 从头开始播放操作，最好带上MediaItem，否则一旦设备那边没有Item，则会出错
	public static final String MEDIA_ACTION_PAUSE = "PAUSE";// 暂停播放操作，暂停当前的Media播放
	public static final String MEDIA_ACTION_RESUME = "RESUME";// 继续播放操作，继续当前的播放，需要注意的是客户端那边必须要有MediaItem，否则会出错（可以校验一下当前的Media状态是否是PAUSE的状态）
	public static final String MEDIA_ACTION_STOP = "STOP";// 停止播放操作，停止当前的Media播放
	/**
	 * 
	 */
	private static final long serialVersionUID = -8814555042830620352L;
	private boolean disableEvent;
	private String action;
	private ResMediaItem item;
	
	public ResMedia() {
		setType("media");
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

	public ResMediaItem getItem() {
		return item;
	}

	public void setItem(ResMediaItem item) {
		this.item = item;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
