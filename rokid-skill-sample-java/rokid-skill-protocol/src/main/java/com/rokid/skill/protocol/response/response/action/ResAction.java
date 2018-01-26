package com.rokid.skill.protocol.response.response.action;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
/**
 * Created by Bassam on 15/03/2017.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8270863105569783649L;
	public static final String ACTION_TYPE_NORMAL = "NORMAL";
	public static final String ACTION_TYPE_EXIT = "EXIT";
	public static final String ACTION_VERSION_V2 = "2.0.0";// 协议版本

	private String version;// "version": "2.0.0",
	private String type;// 当前action的类型：NORMAL 或 EXIT。 当 type 是 NORMAL 时，voice 和
						// media 会同时执行；当 type 是 EXIT
						// 时，action会立即退出，并且在这种情况下，voice 和 media 将会被会被忽略
	private String form;// 当前action的展现形式：scene、cut、service。scene的action会在被打断后压栈，cut的action会在被打断后直接结束，service会在后台执行，但没有任何界面。该字段在技能创建时被确定，无法由cloud
						// app更改。
	private boolean shouldEndSession;// 表明当此次返回的action执行完后 CloudAppClient
										// 是否要退出，同时，当 shouldEndSession 为 true
										// 时，CloudAppClient 将会忽略
										// EventRequests，即在action执行过程中不会产生
										// EventRequest。
	private List<ResDirective> directives;// 行为操作

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public boolean isShouldEndSession() {
		return shouldEndSession;
	}

	public void setShouldEndSession(boolean shouldEndSession) {
		this.shouldEndSession = shouldEndSession;
	}

	public List<ResDirective> getDirectives() {
		return directives;
	}

	public void setDirectives(List<ResDirective> directives) {
		this.directives = directives;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
