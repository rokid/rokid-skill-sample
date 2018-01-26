package com.rokid.skill.tastecoffee.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rokid.skill.exception.RokidException;
import com.rokid.skill.service.HealthCheckService;
@Component("healthCheckService")
public class HealthCheckServiceImlp implements HealthCheckService{
	private static final Logger logger = LoggerFactory.getLogger(HealthCheckService.class.getName());
	@Override
	public String check(HttpServletRequest request) throws RokidException {
		logger.debug("check");
		return null;
	}

}
