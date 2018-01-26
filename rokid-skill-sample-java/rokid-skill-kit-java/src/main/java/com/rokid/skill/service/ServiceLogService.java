package com.rokid.skill.service;

import com.rokid.skill.bean.DefServiceLog;

public interface ServiceLogService {
	/**
	 * 创建请求日志
	 * 
	 * @param serviceLog
	 */
	void createServiceLog(DefServiceLog serviceLog);
}
