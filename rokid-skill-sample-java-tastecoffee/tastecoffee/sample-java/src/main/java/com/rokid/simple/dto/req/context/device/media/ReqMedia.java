package com.rokid.simple.dto.req.context.device.media;

import java.io.Serializable;

/**
 * Created by Bassam on 25/03/2017.
 */
public class ReqMedia implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String state;//PLAYING PAUSED

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
