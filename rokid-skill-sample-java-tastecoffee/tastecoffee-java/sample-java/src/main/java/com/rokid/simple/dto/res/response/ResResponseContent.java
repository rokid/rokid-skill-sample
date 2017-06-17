package com.rokid.simple.dto.res.response;

import java.io.Serializable;

import com.rokid.simple.dto.res.response.action.ResAction;
import com.rokid.simple.dto.res.response.card.ResCard;

public class ResResponseContent implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	// corresponding response id for the request, autofill by system
	private String respId;
	// corresponding response type for the request, autofill by system
	private String resType;// "INTENT / EVENT"
	// the application domain for current response, autofill by system
	private String domain;
	// application type, autofill by system
	private String shot;// SCENE / CUT

	private ResCard card;
	private ResAction action;

	public String getRespId() {
		return respId;
	}

	public void setRespId(String respId) {
		this.respId = respId;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getShot() {
		return shot;
	}

	public void setShot(String shot) {
		this.shot = shot;
	}

	public ResCard getCard() {
		return card;
	}

	public void setCard(ResCard card) {
		this.card = card;
	}

	public ResAction getAction() {
		return action;
	}

	public void setAction(ResAction action) {
		this.action = action;
	}

}
