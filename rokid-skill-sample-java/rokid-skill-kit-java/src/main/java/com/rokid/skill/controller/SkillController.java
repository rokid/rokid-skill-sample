package com.rokid.skill.controller;

import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rokid.skill.constants.ReqAttrName;
import com.rokid.skill.constants.ResponseProduces;
import com.rokid.skill.constants.URI;
import com.rokid.skill.dispatcher.SpeechletDispatcher;
import com.rokid.skill.exception.RokidException;
import com.rokid.skill.exception.RokidSpeechletException;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.request.request.enums.RokidRequestTypeEnums;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.protocol.utils.ResponseUtils;

/**
 * 语音业务请求入口
 * 
 * @author Bassam
 *
 */
@Controller
public class SkillController {
	private static final Logger logger = LoggerFactory.getLogger(SkillController.class.getName());

	@Resource
	SpeechletDispatcher speechletDispatcher;// 业务分发器

	@RequestMapping(value = URI.SPEECHLET, produces = { ResponseProduces.APP_JSON })
	public @ResponseBody String handleRequest(HttpServletRequest request) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("SkillController handleRequest");
		}
		ReqRequest reqRequest = (ReqRequest) request.getAttribute(ReqAttrName.REQ_REQUEST);// 原始的请求数据对象
		ReqBasicInfo basicInfo = (ReqBasicInfo) request.getAttribute(ReqAttrName.REQ_BASIC_INFO);// 二次处理后的请求数据
		@SuppressWarnings("unchecked")
		HashMap<String, Object> attributes = (HashMap<String, Object>) request.getAttribute(ReqAttrName.REQ_ATTRIBUTES);// 属性信息
		ReqApplicationMedia mediaState = (ReqApplicationMedia) request.getAttribute(ReqAttrName.REQ_MEDIA);//当前Skill的媒体状态信息
		ReqApplicationVoice voiceState = (ReqApplicationVoice) request.getAttribute(ReqAttrName.REQ_VOICE);//当前语音的状态信息
		@SuppressWarnings("unchecked")
		HashMap<String, Slot> slots = (HashMap<String, Slot>) request.getAttribute(ReqAttrName.REQ_SLOTS);// Intent请求的Slots信息
		ReqExtraMedia mediaExtra = (ReqExtraMedia) request.getAttribute(ReqAttrName.REQ_MEDIA_EXTRA);//当前媒体事件上报的Media拓展信息
		ReqExtraVoice voiceExtra = (ReqExtraVoice) request.getAttribute(ReqAttrName.REQ_VOICE_EXTRA);//当前Voice事件上报的Voice拓展信息
		if (basicInfo != null && StringUtils.isNoneBlank(basicInfo.getActionType())
				&& RokidRequestTypeEnums.convert(basicInfo.getActionType()) != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("RequestId ：" + basicInfo.getRequestId());
				logger.debug("SessionId ：" + basicInfo.getSessionId());
				logger.debug("DeviceId ：" + basicInfo.getDeviceId());
				logger.debug("UserId ：" + basicInfo.getUserId());
				logger.debug("Action Type ：" + basicInfo.getActionType());
				logger.debug("Action Name ：" + basicInfo.getActionName());
			}
			ResResponse resResponse = speechletDispatcher.dispatchSpeechletCall(request, reqRequest, basicInfo,
					attributes, slots, mediaState, voiceState, mediaExtra, voiceExtra);// 进行业务分发
			String result = ResponseUtils.responseToString(resResponse);
			request.setAttribute(ReqAttrName.RESULT, result);
			return result;
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("Action type is error");
			}
			throw new RokidSpeechletException(HttpStatus.BAD_REQUEST.value(), RokidException.ERROR_CODE_INVALIDARGUMENT,
					"Action type is error");
		}
	}
}
