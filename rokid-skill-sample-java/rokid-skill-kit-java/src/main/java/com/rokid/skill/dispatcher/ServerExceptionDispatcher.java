package com.rokid.skill.dispatcher;

import javax.servlet.http.HttpServletRequest;

import com.rokid.skill.exception.RokidException;
import com.rokid.skill.exception.RokidSpeechletException;

/**
 * 异常处理分发器
 * 
 * @author Bassam
 *
 */
public interface ServerExceptionDispatcher {
	/**
	 * 系统级异常
	 * 
	 * @param request
	 * @param serviceName
	 * @param message
	 * @param excetion
	 * @return
	 */
	String exceptionDisPatcher(HttpServletRequest request, String serviceName, Exception exception, String message,
			String excetionContent);

	/**
	 * Rokid Skill Kit通用异常
	 * 
	 * @param request
	 * @param serviceName
	 * @param exception
	 * @param httpCode
	 * @param errorCode
	 * @param message
	 * @param excetionContent
	 * @return
	 */
	String rokidExcepionDisPatcher(HttpServletRequest request, String serviceName, RokidException exception,
			int httpCode, String errorCode, String message, String excetionContent);

	/**
	 * 语音服务处理异常
	 * 
	 * @param request
	 * @param serviceName
	 * @param exception
	 * @param actionType
	 * @param actionName
	 * @param httpCode
	 * @param errorCode
	 * @param message
	 * @param excetionContent
	 * @return
	 */
	String rokidSpeechletExcepionDisPatcher(HttpServletRequest request, String serviceName,
			RokidSpeechletException exception, String actionType, String actionName, int httpCode, String errorCode,
			String message, String excetionContent);
}
