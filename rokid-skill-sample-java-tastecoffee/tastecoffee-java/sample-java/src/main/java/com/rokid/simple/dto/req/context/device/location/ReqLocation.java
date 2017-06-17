package com.rokid.simple.dto.req.context.device.location;

import java.io.Serializable;

/**
 * Created by Bassam on 15/03/2017.
 */
public class ReqLocation implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String longitude;
    private String latitude;//oldVersion 老版本特有
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    

}
