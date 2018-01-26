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
 * 系统级的媒体控制类Intent请求，目前暂时还没有生效
 * 
 * @author Bassam
 *
 */
public interface SkillIntentMediaDispatcher {

	/**
	 * 继续播放请求
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
	ResResponse resumeIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 暂停请求
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
	ResResponse pauseIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 取消播放请求
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
	ResResponse stopIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 循环播放请求
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
	ResResponse loopIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 取消循环请求
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
	ResResponse loopOffIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 下一首请求
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
	ResResponse nextIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 上一首请求
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
	ResResponse previousIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 重复播放请求
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
	ResResponse repeatIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 重新播放请求
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
	ResResponse startOverIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 随机播放请求
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
	ResResponse shuffleIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;

	/**
	 * 关闭随机播放请求
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
	ResResponse shuffleOffIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException;
}
