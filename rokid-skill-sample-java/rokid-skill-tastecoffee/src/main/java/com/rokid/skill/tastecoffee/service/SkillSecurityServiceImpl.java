package com.rokid.skill.tastecoffee.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rokid.skill.exception.RokidException;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.service.SkillSecurityService;

@Component("skillSecurityService")
public class SkillSecurityServiceImpl implements SkillSecurityService {
	private static final Logger logger = LoggerFactory.getLogger(SkillSecurityServiceImpl.class.getName());

	@Override
	public boolean security(HttpServletRequest request, ReqBasicInfo reqBasicInfo) throws RokidException {
		logger.debug("security");
		return false;
	}

}
