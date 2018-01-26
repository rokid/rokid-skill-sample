package com.rokid.skill.service;

import javax.servlet.http.HttpServletRequest;

import com.rokid.skill.exception.RokidException;
/**
 * 监控检查服务
 * @author Bassam
 *
 */
public interface HealthCheckService {
	String check(HttpServletRequest request) throws RokidException;
}
