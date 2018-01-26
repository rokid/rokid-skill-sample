package com.rokid.skill.service;

import com.rokid.skill.bean.DefSpeechletLog;

/**
 * 语音请求日志服务
 * 
 * @author Bassam
 *
 */
public interface SpeechletLogService {
	/**
	 * 创建speech日志
	 * 
	 * @param serviceLog
	 */
	void createSpeechletLog(DefSpeechletLog speechletLog);
}
