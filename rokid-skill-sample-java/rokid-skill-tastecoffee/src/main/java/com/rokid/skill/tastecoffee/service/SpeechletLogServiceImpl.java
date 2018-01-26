package com.rokid.skill.tastecoffee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rokid.skill.bean.DefSpeechletLog;
import com.rokid.skill.service.SpeechletLogService;

@Component("speechletLogService")
public class SpeechletLogServiceImpl implements SpeechletLogService {
	private static final Logger logger = LoggerFactory.getLogger(SpeechletLogServiceImpl.class.getName());
	@Override
	public void createSpeechletLog(DefSpeechletLog speechletLog) {
		// TODO Auto-generated method stub
		logger.debug("Log："+speechletLog.toString());
	}

}
