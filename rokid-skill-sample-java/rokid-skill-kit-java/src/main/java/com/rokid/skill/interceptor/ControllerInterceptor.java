package com.rokid.skill.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.rokid.skill.bean.DefServiceLog;
import com.rokid.skill.bean.DefSpeechletLog;
import com.rokid.skill.common.IpHolder;
import com.rokid.skill.constants.GlobalConstants;
import com.rokid.skill.constants.ReqAttrName;
import com.rokid.skill.constants.URI;
import com.rokid.skill.exception.RokidException;
import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;
import com.rokid.skill.protocol.request.context.device.ReqDevice;
import com.rokid.skill.protocol.request.context.device.basic.ReqBasic;
import com.rokid.skill.protocol.request.context.device.location.ReqLocation;
import com.rokid.skill.protocol.request.context.user.ReqUser;
import com.rokid.skill.protocol.request.request.ReqRequestValue;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.protocol.utils.RequestUtils;
import com.rokid.skill.service.ServiceLogService;
import com.rokid.skill.service.SpeechletLogService;

/**
 * 控制拦截器
 * 
 * @author Bassam
 *
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class.getName());
	@Resource
	ServiceLogService serviceLogService;// 请求服务日志服务
	@Resource
	SpeechletLogService speechletLogService;// 语音请求日志服务

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String serviceName = (String) request.getAttribute(ReqAttrName.SERVICE_NAME);
		if (URI.HEALTH_CHECK.equals(serviceName)) {// 健康检查
			return super.preHandle(request, response, handler);
		} else if (URI.SPEECHLET.equals(serviceName)) {// Skill请求
			String speechletId = UUID.randomUUID().toString().replace("-", "");// 生成请求ID
			request.setAttribute(ReqAttrName.SPEECHLET_ID, speechletId);// 放置请求ID
			MDC.put(ReqAttrName.SPEECHLET_ID, speechletId);// 格式化日志输出，在日志输出头前面加上请求ID，这样方便进行全链路日志排查
			String headers = readRequestHeaders(request).toString();// 获取请求头
			request.setAttribute(ReqAttrName.REQ_HEADER, headers);
			String body = getBodyFromRequest(request);// 获取请求内容
			request.setAttribute(ReqAttrName.REQ_BODY, body);// 放置请求ID
			String url = StringUtils.isBlank(request.getQueryString()) ? request.getRequestURI()
					: request.getRequestURI() + "?" + request.getQueryString();
			String message = String.format("REQUEST_BEGIN:Method=%s,Url=%s,Headers=%s", request.getMethod(), url,
					headers);
			if (logger.isInfoEnabled()) {
				logger.info(message);
			}
			if (StringUtils.isBlank(body)) {
				if (logger.isErrorEnabled()) {
					logger.error("no body input.");
				}
				throw new RokidException(HttpStatus.BAD_REQUEST.value(), RokidException.ERROR_CODE_INVALIDBODY,
						"body is empty");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("inputBody:\n" + body);
			}
			processRokidProcotolData(speechletId, serviceName, request, body);// 处理请求数据
		}
		return super.preHandle(request, response, handler);// 数据处理
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		createServiceLog(request);
		createSpeechletLog(request);
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		MDC.clear();
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 数据预处理
	 * 
	 * @param speechletId
	 * @param serviceName
	 * @param request
	 * @param body
	 * @throws RokidException
	 * @throws IOException
	 */
	private void processRokidProcotolData(String speechletId, String serviceName, HttpServletRequest request,
			String body) throws RokidException, IOException {
		ReqRequest reqRequest = null;
		try {
			reqRequest = RequestUtils.getRequest(body);// 获取请求体
		} catch (ProtocolException e) {
			if (logger.isErrorEnabled()) {
				logger.error("parseRequestError", e);
			}
			throw new RokidException(HttpStatus.BAD_REQUEST.value(), RokidException.ERROR_CODE_INVALIDBODY,
					"body parse error");
		}
		if (reqRequest != null && reqRequest.getContext() != null) {// 进行一次数据矫正功能，保证服务不会出现空指针异常
			if (reqRequest.getContext().getUser() == null) {
				ReqUser reqUser = new ReqUser();
				reqRequest.getContext().setUser(reqUser);
				if (logger.isWarnEnabled()) {
					logger.warn("NO USER NODE");
				}
			}
			if (reqRequest.getContext().getDevice() == null) {
				ReqDevice reqDevice = new ReqDevice();
				reqRequest.getContext().setDevice(reqDevice);
				if (logger.isWarnEnabled()) {
					logger.warn("NO DEVICE NODE");
				}
			}
			if (reqRequest.getContext().getDevice().getBasic() == null) {
				ReqBasic reqBasic = new ReqBasic();
				reqRequest.getContext().getDevice().setBasic(reqBasic);
				if (logger.isWarnEnabled()) {
					logger.warn("NO DEVICE BASIC NODE");
				}
			}
			String vendor = reqRequest.getContext().getDevice().getBasic().getVendor();
			String deviceType = reqRequest.getContext().getDevice().getBasic().getDeviceType();
			String deviceId = reqRequest.getContext().getDevice().getBasic().getDeviceId();
			String masterId = reqRequest.getContext().getDevice().getBasic().getMasterId();
			String userId = reqRequest.getContext().getUser().getUserId();
			if (StringUtils.isBlank(vendor)) {
				vendor = GlobalConstants.DEFAULT_UNKNOWN_VENDOR;
				if (logger.isWarnEnabled()) {
					logger.warn("NO vendor put it like:" + GlobalConstants.DEFAULT_UNKNOWN_VENDOR);
				}
			}
			if (StringUtils.isBlank(deviceType)) {
				deviceType = GlobalConstants.DEFAULT_UNKNOWN_DEVICETYPE;
				if (logger.isWarnEnabled()) {
					logger.warn("NO deviceType put it like:" + GlobalConstants.DEFAULT_UNKNOWN_DEVICETYPE);
				}
			}
			if (StringUtils.isBlank(deviceId)) {
				deviceId = GlobalConstants.DEFAULT_UNKNOWN_DEVICEID;
				if (logger.isWarnEnabled()) {
					logger.warn("NO deviceId put it like:" + GlobalConstants.DEFAULT_UNKNOWN_DEVICEID);
				}
			}
			if (StringUtils.isBlank(masterId)) {
				masterId = GlobalConstants.DEFAULT_UNKNOWN_MASTERID;
				if (logger.isWarnEnabled()) {
					logger.warn("NO masterId put it like:" + GlobalConstants.DEFAULT_UNKNOWN_MASTERID);
				}
			}
			if (StringUtils.isBlank(userId)) {
				userId = GlobalConstants.DEFAULT_UNKNOWN_USERID;
				if (logger.isWarnEnabled()) {
					logger.warn("NO userId put it like:" + GlobalConstants.DEFAULT_UNKNOWN_USERID);
				}
			}
		}
		ReqBasicInfo reqBasicInfo = RequestUtils.getBasicInfo(reqRequest);// 基本请求信息
		HashMap<String, Slot> slots = RequestUtils.getSlots(reqRequest);// 获取Slots信息
		HashMap<String, Object> attributes = RequestUtils.getAttributes(reqRequest);// 获取拓展信息
		ReqApplicationMedia mediaState = RequestUtils.getMediaState(reqRequest);// Media状态
		ReqApplicationVoice voiceState = RequestUtils.getVoiceState(reqRequest);// Voice状态
		ReqLocation reqLocation = RequestUtils.getLocation(reqRequest);// 获取地理位置
		ReqExtraMedia mediaExtra = RequestUtils.getMediaExtra(reqRequest);// 获取媒体状态信息
		ReqExtraVoice voiceExtra = RequestUtils.getVoiceExtra(reqRequest);// 获取TTS状态信息
		String reqReqId = reqBasicInfo.getRequestId();
		if (StringUtils.isBlank(reqReqId)) {
			if (logger.isWarnEnabled()) {
				logger.warn("NO RequestId put it like:" + speechletId);
			}
			reqReqId = speechletId;
		}
		if (reqRequest.getRequest() == null) {// 进行一次数据预处理，防止空指针异常
			ReqRequestValue reqRequestValue = new ReqRequestValue();
			reqRequest.setRequest(reqRequestValue);
		}
		reqRequest.getRequest().setReqId(reqReqId);
		reqBasicInfo.setRequestId(reqReqId);
		request.setAttribute(ReqAttrName.REQ_REQUEST, reqRequest);// 放置请求内容
		request.setAttribute(ReqAttrName.REQ_ATTRIBUTES, attributes);
		request.setAttribute(ReqAttrName.REQ_BASIC_INFO, reqBasicInfo);
		request.setAttribute(ReqAttrName.REQ_MEDIA, mediaState);
		request.setAttribute(ReqAttrName.REQ_VOICE, voiceState);
		request.setAttribute(ReqAttrName.REQ_LOCATION, reqLocation);
		request.setAttribute(ReqAttrName.REQ_MEDIA_EXTRA, mediaExtra);
		request.setAttribute(ReqAttrName.REQ_VOICE_EXTRA, voiceExtra);
		request.setAttribute(ReqAttrName.REQ_SLOTS, slots);
	}

	/**
	 * 开始日志记录
	 * 
	 * @param request
	 * @param requestId
	 * @param requestTime
	 * @param serviceName
	 * @param headers
	 * @param body
	 */
	private void createServiceLog(HttpServletRequest request) {
		String speechletId = null;// 语音请求ID
		String requestIp;// 请求过来的用户IP
		String serverIp;// 当前服务器的IP（用于判断是那台服务器处理了这个请求）
		String serviceName = null;// 服务名称
		String serviceVersion;// 服务版本
		String methodName;// 方法名称
		String requestHeader = null;// 请求头
		String requestBody = null;// 请求体
		String status;// 状态
		String result = null;// 返回结果
		Long gmtCreated = Long.valueOf(0l);// 请求时间
		Long costsTime;// 耗时
		DefServiceLog sl = new DefServiceLog();
		Object data = request.getAttribute(ReqAttrName.SPEECHLET_ID);
		if (data != null) {
			speechletId = data.toString();
		}
		requestIp = request.getHeader(GlobalConstants.HEADER_NAME_REAL_IP);// 获取请求IP
		serverIp = IpHolder.getSelfIp();
		data = request.getAttribute(ReqAttrName.SERVICE_NAME);
		if (data != null) {
			serviceName = data.toString();
		}
		serviceVersion = GlobalConstants.SERVICE_VERSION;// 服务版本，正在处理日志的时候最好重载掉
		methodName = request.getMethod();
		data = request.getAttribute(ReqAttrName.REQ_HEADER);
		if (data != null) {
			requestHeader = data.toString();
		}
		data = request.getAttribute(ReqAttrName.REQ_BODY);
		if (data != null) {
			requestBody = data.toString();
		}
		status = GlobalConstants.STATUS_SUCCESS;
		data = request.getAttribute(ReqAttrName.RESULT);
		if (data != null) {
			result = data.toString();
		}
		data = request.getAttribute(ReqAttrName.REQ_BEGIN_TIME);
		if (data != null) {
			gmtCreated = (Long) data;
		}
		costsTime = System.currentTimeMillis() - gmtCreated;
		sl.setSpeechletId(speechletId);
		sl.setRequestIp(requestIp);
		sl.setServerIp(serverIp);
		sl.setServiceName(serviceName);
		sl.setServiceVersion(serviceVersion);
		sl.setMethodName(methodName);
		sl.setRequestHeader(requestHeader);
		sl.setRequestBody(requestBody);
		sl.setStatus(status);
		sl.setResult(result);
		sl.setGmtCreated(gmtCreated);
		sl.setCostsTime(costsTime);
		StringBuilder sb = new StringBuilder(128);
		sb.append("Total costs= ").append(costsTime).append(" DefServiceLog= ").append(sl.toString());
		if (costsTime > GlobalConstants.MONITOR_TIME && logger.isWarnEnabled()) {
			logger.warn(sb.toString());
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(sb.toString());
			}
		}
		if (serviceLogService != null) {
			serviceLogService.createServiceLog(sl);
		}
	}

	/**
	 * 开始语音请求记录
	 * 
	 * @param request
	 */
	private void createSpeechletLog(HttpServletRequest request) {
		String speechletId = null;// 语音请求ID
		String requestVersion = null;// 请求的版本
		String requestId = null;// 语音请求ID
		String sessionId = null;// Session的Id
		String applicationId = null;// 应用ID
		String appVersion = null;// 该字段目前填写请求的版本号
		String userId = null;// 用户ID
		String masterId = null;// 设备主人Id（Alexa没有这个字段）
		String deviceId = null;// 设备Id
		String deviceType = null;// 设备类型(ALexa没有这个字段)
		String deviceVendor = null;// 设备认证码（Alexa没有这个字段）
		String actionType = null;// 事件类型
		String actionName = null;// 操作名称/事件名称
		long timestamp = 0;// 设备时间戳
		String status;// 状态码
		Long gmtCreated = Long.valueOf(0l);// 请求时间
		DefSpeechletLog sl = new DefSpeechletLog();
		Object data = request.getAttribute(ReqAttrName.SPEECHLET_ID);
		if (data != null) {
			speechletId = data.toString();
		}
		data = request.getAttribute(ReqAttrName.REQ_BASIC_INFO);
		ReqBasicInfo basicInfo = null;
		if (data != null) {
			basicInfo = (ReqBasicInfo) data;
		}
		if (basicInfo != null) {
			requestVersion = basicInfo.getProtocolVersion();
			requestId = basicInfo.getRequestId();
			sessionId = basicInfo.getSessionId();
			applicationId = basicInfo.getAppId();
			appVersion = basicInfo.getProtocolVersion();
			userId = basicInfo.getUserId();
			masterId = basicInfo.getMasterId();
			deviceId = basicInfo.getDeviceId();
			deviceType = basicInfo.getDeviceType();
			deviceVendor = basicInfo.getDeviceVendor();
			actionType = basicInfo.getActionType();
			actionName = basicInfo.getActionName();
			timestamp = basicInfo.getTimestamp();
		}
		status = GlobalConstants.STATUS_SUCCESS;
		data = request.getAttribute(ReqAttrName.REQ_BEGIN_TIME);
		if (data != null) {
			gmtCreated = (Long) data;
		}
		sl.setSpeechletId(speechletId);
		sl.setRequestVersion(requestVersion);
		sl.setRequestId(requestId);
		sl.setSessionId(sessionId);
		sl.setApplicationId(applicationId);
		sl.setAppVersion(appVersion);
		sl.setUserId(userId);
		sl.setMasterId(masterId);
		sl.setDeviceId(deviceId);
		sl.setDeviceType(deviceType);
		sl.setDeviceVendor(deviceVendor);
		sl.setActionType(actionType);
		sl.setActionName(actionName);
		sl.setTimestamp(timestamp);
		sl.setStatus(status);
		sl.setGmtCreated(gmtCreated);
		StringBuilder sb = new StringBuilder(128);
		sb.append("DefSpeechletLog= ").append(sl.toString());
		if (logger.isDebugEnabled()) {
			logger.debug(sb.toString());
		}
		if (speechletLogService != null) {
			speechletLogService.createSpeechletLog(sl);
		}
	}

	private String getBodyFromRequest(HttpServletRequest request) throws IOException {
		StringBuilder buffer = new StringBuilder();
		ServletInputStream sis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			sis = request.getInputStream();
			isr = new InputStreamReader(sis, GlobalConstants.DEFAULT_ENCODING);
			reader = new BufferedReader(isr);
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("getInputStreamError", e);
			}
			throw e;
		} finally {
			if (sis != null) {
				try {
					sis.close();
				} catch (IOException e) {
					if (logger.isErrorEnabled()) {
						logger.error("ServletInputStream close error", e);
					}
					// ignore
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					if (logger.isErrorEnabled()) {
						logger.error("InputStreamReader close error", e);
					}
					// ignore
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					if (logger.isErrorEnabled()) {
						logger.error("BufferedReader close error", e);
					}
					// ignore
				}
			}
		}
	}

	/**
	 * 获取请求头
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> readRequestHeaders(HttpServletRequest request) {
		Map<String, String> headers = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				headers.put(headerName, headerValue);
			}
		}
		return headers;
	}

}
