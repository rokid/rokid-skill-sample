package com.rokid.skill.tastecoffee.dispatcher;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.rokid.skill.dispatcher.base.AbstractSpeechletDispatcher;
import com.rokid.skill.dispatcher.base.SkillEventCommonDispatcher;
import com.rokid.skill.dispatcher.base.SkillEventMediaDispatcher;
import com.rokid.skill.dispatcher.base.SkillEventVoiceDispatcher;
import com.rokid.skill.dispatcher.base.SkillIntentCommonDispatcher;
import com.rokid.skill.dispatcher.base.SkillIntentMediaDispatcher;
import com.rokid.skill.exception.RokidException;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;
import com.rokid.skill.protocol.request.request.content.enums.RokidIntentMediaEnums;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.tastecoffee.common.*;

/**
 * 事件处理
 * 
 * @author Bassam
 *
 */
@Component("speechletDispatcher")
public class SkillDispatcher extends AbstractSpeechletDispatcher
		implements SkillIntentCommonDispatcher, SkillEventCommonDispatcher {
	// private static final Logger logger =
	// LoggerFactory.getLogger(SkillDispatcher.class.getName());

	@Override
	public ResResponse sessionEndedEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
		return ProtocolUtils.igonre();
	}

	@Override
	public ResResponse skillSuspendEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
		return ProtocolUtils.exit();
	}

	@Override
	public ResResponse skillEvent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
		return ProtocolUtils.igonre();
	}

	@Override
	public ResResponse appStartIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
	    return ProtocolUtils.pickUp("您好，请问有什么可以帮您");
	
	}

	@Override
	public ResResponse appStopIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
		return ProtocolUtils.exit();
	}

	@Override
	public ResResponse unkonwnIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
		return ProtocolUtils.igonre();
	}

	@Override
	public ResResponse skillIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
			HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
			ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
		if (basicInfo.getActionName().equals("bestcoffeebar")) {// 
			return ProtocolUtils.playTts("我看位于问溪路89号的米萨咖啡就很不错");
		} else if (basicInfo.getActionName().equals("nicedrink")) {// 
			return ProtocolUtils.playTts("只要是咖啡都很好喝");
		} 
		return ProtocolUtils.igonre();
	}

	@Override
	public SkillIntentCommonDispatcher getSkillIntentCommonDispatcher() {
		return this;
	}

	@Override
	public SkillIntentMediaDispatcher getSkillIntentMediaDispatcher() {
		return null;
	}

	@Override
	public SkillEventCommonDispatcher getSkillEventCommonDispatcher() {
		return this;
	}

	@Override
	public SkillEventMediaDispatcher getSkillEventMediaDispatcher() {
		return null;
	}

	@Override
	public SkillEventVoiceDispatcher getSkillEventVoiceDispatcher() {
		return null;
	}

}
