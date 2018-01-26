package com.rokid.skill.protocol.response.response.action.directive.confirm;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.action.directive.ResDirective;


/**
 * Created by Bassam on 25/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResConfirm extends ResDirective implements Serializable {
	public ResConfirm() {
		setType("confirm");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416039262704445675L;
	private String confirmIntent;
	private String confirmSlot;
	private List<String> optionWords;

	public String getConfirmIntent() {
		return confirmIntent;
	}

	public void setConfirmIntent(String confirmIntent) {
		this.confirmIntent = confirmIntent;
	}

	public String getConfirmSlot() {
		return confirmSlot;
	}

	public void setConfirmSlot(String confirmSlot) {
		this.confirmSlot = confirmSlot;
	}

	public List<String> getOptionWords() {
		return optionWords;
	}

	public void setOptionWords(List<String> optionWords) {
		this.optionWords = optionWords;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
