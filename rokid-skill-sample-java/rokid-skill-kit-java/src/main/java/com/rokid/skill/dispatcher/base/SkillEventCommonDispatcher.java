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
 * 通用的事件回调
 * 
 * @author Bassam
 *
 */
public interface SkillEventCommonDispatcher {
	/**
	 * Domain被关闭事件,Skill完全退出
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
	ResResponse sessionEndedEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 应用挂起事件，Skill实体资源已经执行完毕或者后台挂起，Domain没有销毁
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
	ResResponse skillSuspendEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 后期新增的事件处理，非Media和非Voice定义的事件
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
	ResResponse skillEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;
}
