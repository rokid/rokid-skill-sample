package com.rokid.skill.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.rokid.skill.constants.ReqAttrName;
import com.rokid.skill.constants.URI;
import com.rokid.skill.exception.RokidSpeechletException;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.service.SkillSecurityService;

/**
 * 健康检查拦截器
 * 
 * @author Bassam
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class.getName());
	@Resource
	SkillSecurityService skillSecurityService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 服务器健康检查不需要鉴权直接交给controller处理
		String serviceName = (String) request.getAttribute(ReqAttrName.SERVICE_NAME);
		if (URI.HEALTH_CHECK.equals(serviceName)) {// 健康检查
			return super.preHandle(request, response, handler);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Start Security check");
		}
		ReqBasicInfo basicInfo = (ReqBasicInfo) request.getAttribute(ReqAttrName.REQ_BASIC_INFO);
		if (skillSecurityService != null) {
			if (!skillSecurityService.security(request, basicInfo)) {// 安全检查
				new RokidSpeechletException(HttpStatus.NOT_ACCEPTABLE.value(),
						RokidSpeechletException.ERROR_CODE_SECURITY, basicInfo.getActionType(),
						basicInfo.getActionName(), "SecurityError");
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
