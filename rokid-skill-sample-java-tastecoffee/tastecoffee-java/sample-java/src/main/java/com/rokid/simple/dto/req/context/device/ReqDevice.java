package com.rokid.simple.dto.req.context.device;


import java.io.Serializable;

import com.rokid.simple.dto.req.context.device.location.ReqLocation;
import com.rokid.simple.dto.req.context.device.media.ReqMedia;
import com.rokid.simple.dto.req.context.device.screen.ReqScreen;

/**
 * Created by Bassam on 15/03/2017.
 */
public class ReqDevice implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deviceId;
    private String deviceType;
    private String vendor;//厂商ID new version
    private ReqScreen screen;//new version
    private String locale;//语言
    private long timestamp;
    private ReqMedia media;//new Version
    private ReqLocation location;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public ReqScreen getScreen() {
        return screen;
    }

    public void setScreen(ReqScreen screen) {
        this.screen = screen;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ReqMedia getMedia() {
        return media;
    }

    public void setMedia(ReqMedia media) {
        this.media = media;
    }

    public ReqLocation getLocation() {
        return location;
    }

    public void setLocation(ReqLocation location) {
        this.location = location;
    }
}
