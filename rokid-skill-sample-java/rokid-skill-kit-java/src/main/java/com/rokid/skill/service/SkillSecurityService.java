package com.rokid.skill.service;

import javax.servlet.http.HttpServletRequest;

import com.rokid.skill.exception.RokidException;
import com.rokid.skill.protocol.utils.ReqBasicInfo;

/**
 * 安全检查服务
 * 
 * @author Bassam
 *
 */
public interface SkillSecurityService {
	boolean security(HttpServletRequest request, ReqBasicInfo reqBasicInfo) throws RokidException;
}
