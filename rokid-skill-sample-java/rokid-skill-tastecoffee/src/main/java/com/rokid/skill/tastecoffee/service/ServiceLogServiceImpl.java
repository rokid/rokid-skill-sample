package com.rokid.skill.tastecoffee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rokid.skill.bean.DefServiceLog;
import com.rokid.skill.service.ServiceLogService;

@Component("serviceLogService")
public class ServiceLogServiceImpl implements ServiceLogService {
	private static final Logger logger = LoggerFactory.getLogger(ServiceLogService.class.getName());
	@Override
	public void createServiceLog(DefServiceLog serviceLog) {
		// TODO Auto-generated method stub
		logger.debug("Log："+serviceLog.toString());
	}

}
