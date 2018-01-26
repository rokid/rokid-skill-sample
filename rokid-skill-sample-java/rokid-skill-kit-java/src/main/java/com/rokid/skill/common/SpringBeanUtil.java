package com.rokid.skill.common;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
/**
 * Spring MVC获取对象工具类
 * @author Bassam
 *
 */
public class SpringBeanUtil {
	private static ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz, String beanName) {
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		if (context == null) {
			context = applicationContext;
		}
		return (T) context.getBean(beanName);
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringBeanUtil.applicationContext = applicationContext;
	}
}
