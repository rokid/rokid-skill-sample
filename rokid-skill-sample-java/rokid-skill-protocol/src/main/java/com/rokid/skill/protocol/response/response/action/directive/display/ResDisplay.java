package com.rokid.skill.protocol.response.response.action.directive.display;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.action.directive.ResDirective;


/**
 * Created by Bassam on 25/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResDisplay extends ResDirective implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2540353337364483125L;
	private String action;
	private boolean needEventCallback;
	private long duration;
	public ResDisplay() {
		setType("display");
	}
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isNeedEventCallback() {
		return needEventCallback;
	}

	public void setNeedEventCallback(boolean needEventCallback) {
		this.needEventCallback = needEventCallback;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
