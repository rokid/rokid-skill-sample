package com.rokid.simple.dto.req.context.device.screen;

import java.io.Serializable;

/**
 * Created by Bassam on 25/03/2017.
 */
public class ReqScreen implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String x;
    private String y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
