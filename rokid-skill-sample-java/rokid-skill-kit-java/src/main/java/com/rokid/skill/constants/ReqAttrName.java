package com.rokid.skill.constants;

/**
 * 请求参数存储key
 * 
 * @author Bassam
 *
 */
public interface ReqAttrName {
	// 过滤器中处理
	public static final String REQ_BEGIN_TIME = "ReqBeginTime";// 请求接入时间
	public static final String SERVICE_NAME = "ServiceName";// 请求服务名称
	// 前置拦截器处理
	public static final String SPEECHLET_ID = "SpeechletId";// 语音请求请求ID
	public static final String REQ_HEADER = "HEADER";// 请求头
	public static final String REQ_BODY = "BODY";// 请求体
	// 前置拦截器数据处理服务处理
	public static final String REQ_REQUEST = "ReqRequest";// 请求内容
	public static final String REQ_ATTRIBUTES = "ReqAttributes";// IntentRequest中的拓展信息
	public static final String REQ_BASIC_INFO = "ReqBasicInfo";// 基本请求信息
	public static final String REQ_MEDIA = "ReqMedia";// 当前Skill的Media和Voice状态信息
	public static final String REQ_VOICE = "ReqVoice";// 当前Voice状态信息
	public static final String REQ_LOCATION = "ReqLocation";// 当前地址位置
	public static final String REQ_MEDIA_EXTRA = "ReqMediaExtra";// Media事件中的Media拓展信息
	public static final String REQ_VOICE_EXTRA = "ReqVoiceExtra";// Voice事件中的Voice拓展信息
	public static final String REQ_SLOTS = "ReqSlots";// IntentRequest中的Slots信息
	// Controller或者Resolver中处理
	public static final String RESULT = "Result";
}
