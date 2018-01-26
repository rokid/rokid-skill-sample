package com.rokid.skill.protocol.request.request.content;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.request.request.content.extra.ReqEventExtra;
import com.rokid.skill.protocol.request.request.content.slot.Slot;

/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRequestContent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8296524576831663917L;
	/**
	 * @see com.rokid.skill.protocol.request.request.content.enums.RokidIntentEnums
	 */
	private String intent;// IntentRequest,CloudApp 对应的 nlp intent
	private HashMap<String, Slot> slots;
	private String sentence;// asr 语音内容
	/**
	 * @see com.rokid.skill.protocol.request.request.content.enums.RokidEventEnums
	 */
	private String event; // EventRequest,事件类型
	private ReqEventExtra extra;// EventRequest

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public HashMap<String, Slot> getSlots() {
		return slots;
	}

	public void setSlots(HashMap<String, Slot> slots) {
		this.slots = slots;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public ReqEventExtra getExtra() {
		return extra;
	}

	public void setExtra(ReqEventExtra extra) {
		this.extra = extra;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
