package com.rokid.skill.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rokid.skill.constants.ReqAttrName;
import com.rokid.skill.constants.ResponseProduces;
import com.rokid.skill.constants.URI;
import com.rokid.skill.service.HealthCheckService;

/**
 * 健康检查处理器
 * 
 * @author Bassam
 *
 */
@Controller
public class HealthCheckController {
	private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class.getName());
	@Resource
	HealthCheckService healthCheckService;

	@RequestMapping(value = URI.HEALTH_CHECK, produces = { ResponseProduces.APP_JSON })
	public @ResponseBody String handleRequest(HttpServletRequest request) throws Exception {
		String checkResult = null;
		if (healthCheckService != null) {
			healthCheckService.check(request);// 需要开发者自己实现健康检查服务，主要用于开发者自己的服务稳定性监控
		}
		if (logger.isDebugEnabled()) {
			logger.debug("HealthCheck Result:" + checkResult);
		}
		request.setAttribute(ReqAttrName.RESULT, checkResult);
		return checkResult;
	}
}
