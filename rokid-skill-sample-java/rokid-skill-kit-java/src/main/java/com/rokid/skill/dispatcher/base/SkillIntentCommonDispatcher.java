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

public interface SkillIntentCommonDispatcher {
	/**
	 * 应用开始请求
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
	ResResponse appStartIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 应用退出请求
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
	ResResponse appStopIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 未知意图的Intent请求，当Skill开启PickUp以后，用户三次命中都出错，会调用该Intent，方便Skill进行兜底处理
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
	ResResponse unkonwnIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 产品/开发者自定义的相关Intent处理
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
	ResResponse skillIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

}
