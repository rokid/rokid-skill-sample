package com.rokid.skill.dispatcher.base;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.rokid.skill.exception.RokidException;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ReqBasicInfo;

/**
 * 媒体播放回调事件
 * 
 * @author Bassam
 *
 */
public interface SkillEventMediaDispatcher {
	/**
	 * Media开始事件
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param reqRequest
	 *            原始请求协议解析结果
	 * @param basicInfo
	 *            处理后的基本请求信息
	 * @param attributes
	 *            Intent 请求中的Session里面的属性信息
	 * @param slots
	 *            Intent请求的 Slots信息
	 * @param mediaState
	 *            Intent请求的当前Skill的媒体状态信息
	 * @param voiceState
	 *            Intent请求的当前Skill的媒体语音状态信息
	 * @param mediaExtra
	 *            Event请求的媒体拓展信息
	 * @param voiceExtra
	 *            Event请求的Voice状态信息
	 * @return
	 * @throws RokidException
	 */
	ResResponse mediaStartedEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * Media播放失败事件
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param reqRequest
	 *            原始请求协议解析结果
	 * @param basicInfo
	 *            处理后的基本请求信息
	 * @param attributes
	 *            Intent 请求中的Session里面的属性信息
	 * @param slots
	 *            Intent请求的 Slots信息
	 * @param mediaState
	 *            Intent请求的当前Skill的媒体状态信息
	 * @param voiceState
	 *            Intent请求的当前Skill的媒体语音状态信息
	 * @param mediaExtra
	 *            Event请求的媒体拓展信息
	 * @param voiceExtra
	 *            Event请求的Voice状态信息
	 * @return
	 * @throws RokidException
	 */
	ResResponse mediaFailedEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * Media暂停事件
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param reqRequest
	 *            原始请求协议解析结果
	 * @param basicInfo
	 *            处理后的基本请求信息
	 * @param attributes
	 *            Intent 请求中的Session里面的属性信息
	 * @param slots
	 *            Intent请求的 Slots信息
	 * @param mediaState
	 *            Intent请求的当前Skill的媒体状态信息
	 * @param voiceState
	 *            Intent请求的当前Skill的媒体语音状态信息
	 * @param mediaExtra
	 *            Event请求的媒体拓展信息
	 * @param voiceExtra
	 *            Event请求的Voice状态信息
	 * @return
	 * @throws RokidException
	 */
	ResResponse mediaPausedEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * Media播放完毕事件
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param reqRequest
	 *            原始请求协议解析结果
	 * @param basicInfo
	 *            处理后的基本请求信息
	 * @param attributes
	 *            Intent 请求中的Session里面的属性信息
	 * @param slots
	 *            Intent请求的 Slots信息
	 * @param mediaState
	 *            Intent请求的当前Skill的媒体状态信息
	 * @param voiceState
	 *            Intent请求的当前Skill的媒体语音状态信息
	 * @param mediaExtra
	 *            Event请求的媒体拓展信息
	 * @param voiceExtra
	 *            Event请求的Voice状态信息
	 * @return
	 * @throws RokidException
	 */
	ResResponse mediaFinishedEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

}
