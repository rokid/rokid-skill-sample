package com.rokid.skill.constants;

/**
 * 宏定义
 * 
 * @author Bassam
 *
 */
public interface GlobalConstants {
	public static final String HEADER_NAME_REAL_IP = "x-real-ip";
	public static final String SERVICE_VERSION = "1.0.0";//服务版本
	public static final String SIGNATURE = "signature";// 签名
	public static final String BACK_SLASH = "/";// 分隔符
	public static final String COLON = ":";// 冒号
	public static final String DEFAULT_ENCODING = "UTF-8";// 默认的请求编码
	public static final int MONITOR_TIME = 1000;// 业务处理耗时警告预值

	public static final String STATUS_SUCCESS="Success";//成功状态
	public static final String STATUS_FAILED="Failed";//失败状态
	
	public static final String DEFAULT_UNKNOWN_VENDOR = "UNKNOWN_VENDOR";// 未知的厂商ID
	public static final String DEFAULT_UNKNOWN_DEVICETYPE = "UNKNOWN_DEVICETYPE";// 未知的设备类型
	public static final String DEFAULT_UNKNOWN_DEVICEID = "UNKNOWN_DEVICEID";// 未知设备Id
	public static final String DEFAULT_UNKNOWN_MASTERID = "UNKNOWN_MASTERID";// 未知的机主ID
	public static final String DEFAULT_UNKNOWN_USERID = "UNKNOWN_USERID";// 未知的用户ID
}
