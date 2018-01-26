package com.rokid.skill.tastecoffee.dispatcher;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rokid.skill.dispatcher.ServerExceptionDispatcher;
import com.rokid.skill.exception.RokidException;
import com.rokid.skill.exception.RokidSpeechletException;

/**
 * 异常处理
 * 
 * @author Bassam
 *
 */
@Component("serverExceptionDispatcher")
public class SkillExceptionDispatcher implements ServerExceptionDispatcher {
	private static final Logger logger = LoggerFactory.getLogger(ServerExceptionDispatcher.class.getName());

	@Override
	public String exceptionDisPatcher(HttpServletRequest request, String serviceName, Exception exception,
			String message, String excetionContent) {
		// TODO Auto-generated method stub
		logger.debug("exceptionDisPatcher");
		return null;
	}

	@Override
	public String rokidExcepionDisPatcher(HttpServletRequest request, String serviceName, RokidException exception,
			int httpCode, String errorCode, String message, String excetionContent) {
		logger.debug("rokidExcepionDisPatcher");
		return null;
	}

	@Override
	public String rokidSpeechletExcepionDisPatcher(HttpServletRequest request, String serviceName,
			RokidSpeechletException exception, String actionType, String actionName, int httpCode, String errorCode,
			String message, String excetionContent) {
		logger.debug("rokidSpeechletExcepionDisPatcher");
		return null;
	}

}
