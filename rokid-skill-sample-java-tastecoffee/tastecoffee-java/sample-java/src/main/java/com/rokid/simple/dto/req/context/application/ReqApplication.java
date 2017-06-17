package com.rokid.simple.dto.req.context.application;

import java.io.Serializable;

/**
 * Created by Bassam on 15/03/2017.
 */
public class ReqApplication implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String applicationId;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
