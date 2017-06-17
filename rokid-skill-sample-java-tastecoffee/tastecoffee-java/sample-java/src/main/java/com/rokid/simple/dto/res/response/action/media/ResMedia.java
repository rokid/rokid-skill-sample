package com.rokid.simple.dto.res.response.action.media;

import java.io.Serializable;

import com.rokid.simple.dto.res.response.action.media.item.ResMediaItem;

public class ResMedia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean needEventCallback;
	private String action;// PLAY/PAUSE
	private String behaviour;// APPEND/REPLACE_ALL/REPLACE_APPEND/CLEAR
	private ResMediaItem item;

	public boolean isNeedEventCallback() {
		return needEventCallback;
	}

	public void setNeedEventCallback(boolean needEventCallback) {
		this.needEventCallback = needEventCallback;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public ResMediaItem getItem() {
		return item;
	}

	public void setItem(ResMediaItem item) {
		this.item = item;
	}

}
