package com.rokid.skill.common;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 
 * 设置有最大线程数的后台线程池，后期可以用于日志插入操作
 * 
 */
public class FixedMaximumPoolScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

	public FixedMaximumPoolScheduledThreadPoolExecutor(int corePoolSize, int maximumPoolSize) {
		super(corePoolSize);
		super.setMaximumPoolSize(maximumPoolSize);
	}

	public int getMaximumPoolSize() {
		return super.getMaximumPoolSize();
	}
}
