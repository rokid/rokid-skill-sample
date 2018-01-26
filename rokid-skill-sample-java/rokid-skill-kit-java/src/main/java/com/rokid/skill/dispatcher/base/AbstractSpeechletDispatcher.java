package com.rokid.skill.dispatcher.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.rokid.skill.exception.RokidException;
import com.rokid.skill.exception.RokidSpeechletException;
import com.rokid.skill.dispatcher.SpeechletDispatcher;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;
import com.rokid.skill.protocol.request.request.ReqRequestValue;
import com.rokid.skill.protocol.request.request.content.enums.RokidEventCommonEnums;
import com.rokid.skill.protocol.request.request.content.enums.RokidEventMediaEnums;
import com.rokid.skill.protocol.request.request.content.enums.RokidEventVoiceEnums;
import com.rokid.skill.protocol.request.request.content.enums.RokidIntentCommonEnums;
import com.rokid.skill.protocol.request.request.content.enums.RokidIntentMediaEnums;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ReqBasicInfo;

/**
 * 已经写好的分发器样例，开发者自己自己实现SpeechletDispatcher来进行业务分发，或者直接继承AbstractSpeechletDispatcher，由上层进行接管
 * 
 * @author Bassam
 *
 */
public abstract class AbstractSpeechletDispatcher implements SpeechletDispatcher {
	@Override
	public ResResponse dispatchSpeechletCall(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws Exception {
		if (ReqRequestValue.REQTYPE_INTENT.equals(basicInfo.getActionType())) {// Intent请求分发器
			RokidIntentMediaEnums rmEm = RokidIntentMediaEnums.convert(basicInfo.getActionName());
			SkillIntentMediaDispatcher skillIntentMediaDispatcher = getSkillIntentMediaDispatcher();
			if (rmEm != null && skillIntentMediaDispatcher != null) {// 媒体控制事件请求分发器
				try {
					Method method = skillIntentMediaDispatcher.getClass().getMethod(rmEm.getMethodName(),
							HttpServletRequest.class, ReqRequest.class, ReqBasicInfo.class, HashMap.class,
							HashMap.class, ReqApplicationMedia.class, ReqApplicationVoice.class, ReqExtraMedia.class,
							ReqExtraVoice.class);
					return (ResResponse) method.invoke(skillIntentMediaDispatcher, request, reqRequest, basicInfo,
							attributes, slots, mediaState, voiceState, mediaExtra, voiceExtra);
				} catch (Exception e) {
					throw e;
				}
			}
			RokidIntentCommonEnums rcEm = RokidIntentCommonEnums.convert(basicInfo.getActionName());
			SkillIntentCommonDispatcher skillIntentCommonDispatcher = getSkillIntentCommonDispatcher();
			if (skillIntentCommonDispatcher != null) {// 通用的Skill语音请求分发器
				if (rcEm != null) {
					try {
						Method method = skillIntentCommonDispatcher.getClass().getMethod(rcEm.getMethodName(),
								HttpServletRequest.class, ReqRequest.class, ReqBasicInfo.class, HashMap.class,
								HashMap.class, ReqApplicationMedia.class, ReqApplicationVoice.class,
								ReqExtraMedia.class, ReqExtraVoice.class);
						return (ResResponse) method.invoke(skillIntentCommonDispatcher, request, reqRequest, basicInfo,
								attributes, slots, mediaState, voiceState, mediaExtra, voiceExtra);
					} catch (Exception e) {
						throw e;
					}
				} else {
					return skillIntentCommonDispatcher.skillIntent(request, reqRequest, basicInfo, attributes, slots,
							mediaState, voiceState, mediaExtra, voiceExtra);
				}
			} else {
				throw new RokidSpeechletException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						RokidException.ERROR_CODE_METHODNOTALLOWED, basicInfo.getActionType(),
						basicInfo.getActionName(), "SkillIntentDispatcher is null");
			}
		} else if (ReqRequestValue.REQTYPE_EVENT.equals(basicInfo.getActionType())) {// Event请求分发器
			RokidEventMediaEnums rmEm = RokidEventMediaEnums.convert(basicInfo.getActionName());
			SkillEventMediaDispatcher skillEventMediaDispatcher = getSkillEventMediaDispatcher();
			if (rmEm != null && skillEventMediaDispatcher != null) {// 媒体事件分发器
				try {
					Method method = skillEventMediaDispatcher.getClass().getMethod(rmEm.getMethodName(),
							HttpServletRequest.class, ReqRequest.class, ReqBasicInfo.class, HashMap.class,
							HashMap.class, ReqApplicationMedia.class, ReqApplicationVoice.class, ReqExtraMedia.class,
							ReqExtraVoice.class);
					return (ResResponse) method.invoke(skillEventMediaDispatcher, request, reqRequest, basicInfo,
							attributes, slots, mediaState, voiceState, mediaExtra, voiceExtra);
				} catch (Exception e) {
					throw e;
				}
			}
			RokidEventVoiceEnums rvEm = RokidEventVoiceEnums.convert(basicInfo.getActionName());
			SkillEventVoiceDispatcher skillEventVoiceDispatcher = getSkillEventVoiceDispatcher();
			if (rvEm != null && skillEventVoiceDispatcher != null) {// 语音事件分发器
				try {
					Method method = skillEventVoiceDispatcher.getClass().getMethod(rvEm.getMethodName(),
							HttpServletRequest.class, ReqRequest.class, ReqBasicInfo.class, HashMap.class,
							HashMap.class, ReqApplicationMedia.class, ReqApplicationVoice.class, ReqExtraMedia.class,
							ReqExtraVoice.class);
					return (ResResponse) method.invoke(skillEventVoiceDispatcher, request, reqRequest, basicInfo,
							attributes, slots, mediaState, voiceState, mediaExtra, voiceExtra);
				} catch (Exception e) {
					throw e;
				}
			}
			RokidEventCommonEnums rcEm = RokidEventCommonEnums.convert(basicInfo.getActionName());
			SkillEventCommonDispatcher skillEventCommonDispatcher = getSkillEventCommonDispatcher();
			if (skillEventCommonDispatcher != null) {// 通用的事件分发器
				if (rcEm != null) {
					try {
						Method method = skillEventCommonDispatcher.getClass().getMethod(rcEm.getMethodName(),
								HttpServletRequest.class, ReqRequest.class, ReqBasicInfo.class, HashMap.class,
								HashMap.class, ReqApplicationMedia.class, ReqApplicationVoice.class,
								ReqExtraMedia.class, ReqExtraVoice.class);
						return (ResResponse) method.invoke(skillEventCommonDispatcher, request, reqRequest, basicInfo,
								attributes, slots, mediaState, voiceState, mediaExtra, voiceExtra);
					} catch (Exception e) {
						throw e;
					}
				} else {
					return skillEventCommonDispatcher.skillEvent(request, reqRequest, basicInfo, attributes, slots,
							mediaState, voiceState, mediaExtra, voiceExtra);
				}
			} else {
				throw new RokidSpeechletException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						RokidException.ERROR_CODE_METHODNOTALLOWED, basicInfo.getActionType(),
						basicInfo.getActionName(), "SkillEventDispatcher is null");
			}
		}
		throw new RokidSpeechletException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				RokidException.ERROR_CODE_METHODNOTALLOWED, basicInfo.getActionType(), basicInfo.getActionName(),
				"Unkonw Request Type");
	}

	/**
	 * 获取Intent分发器
	 * 
	 * @return
	 */
	public abstract SkillIntentCommonDispatcher getSkillIntentCommonDispatcher();

	/**
	 * 获取媒体控制Ingent分发器
	 * 
	 * @return
	 */
	public abstract SkillIntentMediaDispatcher getSkillIntentMediaDispatcher();

	/**
	 * 获取Event分发器
	 * 
	 * @return
	 */
	public abstract SkillEventCommonDispatcher getSkillEventCommonDispatcher();

	/**
	 * 获取媒体事件分发器
	 * 
	 * @return
	 */
	public abstract SkillEventMediaDispatcher getSkillEventMediaDispatcher();

	/**
	 * 获取语音事件分发器
	 * 
	 * @return
	 */
	public abstract SkillEventVoiceDispatcher getSkillEventVoiceDispatcher();

}
