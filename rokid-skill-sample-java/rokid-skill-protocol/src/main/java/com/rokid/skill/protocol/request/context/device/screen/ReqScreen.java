package com.rokid.skill.protocol.request.context.device.screen;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Bassam on 25/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqScreen implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3108410377906776393L;
	private String x;//X 方向上的像素大小
	private String y;//Y 方向上的像素大小

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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
