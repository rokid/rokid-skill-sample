package com.rokid.skill.protocol.request.context.device.basic;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vendor;// 注册生产商ID
	private String deviceType;// 该生产商设定的设备型号
	private String deviceId;// 该型号下的设备ID
	private String locale;// 国家及语言，标准locale格式
	private Long timestamp;// 设备当前时间，unix timestamp
	private String masterId;// 设备主人的Id;
	private String voicetrigger;// 设备当前激活词

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getVoicetrigger() {
		return voicetrigger;
	}

	public void setVoicetrigger(String voicetrigger) {
		this.voicetrigger = voicetrigger;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
