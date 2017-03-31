package com.rokid.simple.dto.req.context.user;

import java.io.Serializable;

/**
 * Created by Bassam on 15/03/2017.
 */
public class ReqUser implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
