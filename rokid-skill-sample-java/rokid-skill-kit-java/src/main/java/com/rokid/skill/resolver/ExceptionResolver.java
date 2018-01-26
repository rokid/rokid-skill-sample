package com.rokid.skill.resolver;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.rokid.skill.bean.DefServiceLog;
import com.rokid.skill.bean.DefSpeechletLog;
import com.rokid.skill.common.IpHolder;
import com.rokid.skill.constants.GlobalConstants;
import com.rokid.skill.constants.ReqAttrName;
import com.rokid.skill.constants.ResponseProduces;
import com.rokid.skill.constants.URI;
import com.rokid.skill.dispatcher.ServerExceptionDispatcher;
import com.rokid.skill.exception.RokidException;
import com.rokid.skill.exception.RokidSpeechletException;
import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.protocol.utils.ResponseUtils;
import com.rokid.skill.service.ServiceLogService;
import com.rokid.skill.service.SpeechletLogService;

public class ExceptionResolver implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class.getName());
	@Resource
	ServiceLogService serviceLogService;// 请求服务日志服务
	@Resource
	SpeechletLogService speechletLogService;// 语音请求日志服务
	@Resource
	ServerExceptionDispatcher serverExceptionDispatcher;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception ex) {
		response.setContentType(ResponseProduces.APP_JSON);
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (logger.isDebugEnabled()) {
			logger.debug("Access-Control-Allow-Origin:*");
		}
		String serviceName = (String) request.getAttribute(ReqAttrName.SERVICE_NAME);
		String message=ex.getMessage();
		String result = null;
		if (ex.getCause() != null && ex.getCause() instanceof RokidException) {
			RokidException ie = (RokidException) ex;
			int httpCode = ie.getHttpStatusCode();
			String errorCode = String.valueOf(ie.getErrorCode());
			if (logger.isDebugEnabled()) {
				logger.debug("ServiceName:" + serviceName + "\n httpCode:" + httpCode + "\n errorCode:" + errorCode
						+ "\n Exception:" + ExceptionUtils.getStackTrace(ex));
			}
			if (serviceName.equals(URI.HEALTH_CHECK)) {// 健康检查错误信息
				response.setStatus(httpCode);// 虽然是异常，但以正确的http状态返回
			} else {
				response.setStatus(HttpStatus.OK.value());// 虽然是异常，但以正确的http状态返回
			}
			result = serverExceptionDispatcher.rokidExcepionDisPatcher(request, serviceName, ie, httpCode, errorCode,
					message, ExceptionUtils.getStackTrace(ex));
			if (StringUtils.isBlank(result)) {
				try {
					result = ResponseUtils.responseToString(ResponseUtils.buildIngoreEventResponse());
				} catch (ProtocolException e) {
					e.printStackTrace();
				}
			}
			writeErrorResponse(response, result);
		} else if (ex.getCause() != null && ex.getCause() instanceof RokidSpeechletException) {
			RokidSpeechletException ie = (RokidSpeechletException) ex;
			int httpCode = ie.getHttpStatusCode();
			String errorCode = String.valueOf(ie.getErrorCode());
			String actionType = ie.getActionType();
			String actionName = ie.getActionName();
			if (logger.isDebugEnabled()) {
				logger.debug("ServiceName:" + serviceName + "\n httpCode:" + httpCode + "\n errorCode:" + errorCode
						+ "\n actionType:" + actionType + "\n actionName:" + actionName + "\n Exception:"
						+ ExceptionUtils.getStackTrace(ex));
			}
			if (serviceName.equals(URI.HEALTH_CHECK)) {// 健康检查错误信息
				response.setStatus(httpCode);// 虽然是异常，但以正确的http状态返回
			} else {
				response.setStatus(HttpStatus.OK.value());// 虽然是异常，但以正确的http状态返回
			}
			result = serverExceptionDispatcher.rokidSpeechletExcepionDisPatcher(request, serviceName, ie, actionType,
					actionName, httpCode, errorCode, message, ExceptionUtils.getStackTrace(ex));
			if (StringUtils.isBlank(result)) {
				try {
					result = ResponseUtils.responseToString(ResponseUtils.buildIngoreEventResponse());
				} catch (ProtocolException e) {
					e.printStackTrace();
				}
			}
			writeErrorResponse(response, result);

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("ServiceName:" + serviceName + "\n Exception:" + ExceptionUtils.getStackTrace(ex));
			}
			if (!serviceName.equals(URI.HEALTH_CHECK)) {// 健康检查错误信息
				response.setStatus(HttpStatus.OK.value());// 虽然是异常，但以正确的http状态返回
			}
			result = serverExceptionDispatcher.exceptionDisPatcher(request, serviceName, ex, message,
					ExceptionUtils.getStackTrace(ex));
			if (StringUtils.isBlank(result)) {
				try {
					result = ResponseUtils.responseToString(ResponseUtils.buildIngoreEventResponse());
				} catch (ProtocolException e) {
					e.printStackTrace();
				}
			}
			writeErrorResponse(response, result);
		}
		request.setAttribute(ReqAttrName.RESULT, result);
		createServiceLog(request, ExceptionUtils.getStackTrace(ex));
		createSpeechletLog(request);
		MDC.clear();
		return null;
	}

	/**
	 * 写入响应
	 * 
	 * @param response
	 * @param result
	 */
	private void writeErrorResponse(HttpServletResponse response, String result) {
		try {
			response.getWriter().write(result);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("write response io exception.", e);
			}
		}
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
	private void createServiceLog(HttpServletRequest request, String exception) {
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
		status = GlobalConstants.STATUS_FAILED;
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
		sl.setException(exception);
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
		status = GlobalConstants.STATUS_FAILED;
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
}
