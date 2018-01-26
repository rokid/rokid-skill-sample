package com.rokid.skill.protocol.request.context.device;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.request.context.device.basic.ReqBasic;
import com.rokid.skill.protocol.request.context.device.location.ReqLocation;
import com.rokid.skill.protocol.request.context.device.media.ReqMedia;
import com.rokid.skill.protocol.request.context.device.screen.ReqScreen;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqDevice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3966116657482862685L;
	private ReqBasic basic;// 展示了当前设备的基础信息，主要包含设备制造信息、时间信息、国家文字信息。
	private ReqScreen screen;// 展示了当前设备的屏幕信息，主要包含屏幕的分辨率信息。
	private ReqMedia media;// 向CloudApp表明当前设备上CloudAppClient中的MediaPlayer的状态信息。
	private ReqMedia voice;// 向CloudApp表明当前设备上CloudAppClient中的Voice的状态信息。
	private ReqLocation location;// 位置信息，现在只有一个经纬度

	public ReqBasic getBasic() {
		return basic;
	}

	public void setBasic(ReqBasic basic) {
		this.basic = basic;
	}

	public ReqScreen getScreen() {
		return screen;
	}

	public void setScreen(ReqScreen screen) {
		this.screen = screen;
	}

	public ReqMedia getMedia() {
		return media;
	}

	public void setMedia(ReqMedia media) {
		this.media = media;
	}

	public ReqMedia getVoice() {
		return voice;
	}

	public void setVoice(ReqMedia voice) {
		this.voice = voice;
	}

	public ReqLocation getLocation() {
		return location;
	}

	public void setLocation(ReqLocation location) {
		this.location = location;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
