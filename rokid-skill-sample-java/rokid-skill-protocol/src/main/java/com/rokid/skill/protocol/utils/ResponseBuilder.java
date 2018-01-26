package com.rokid.skill.protocol.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.response.response.ResResponseContent;
import com.rokid.skill.protocol.response.response.action.ResAction;
import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import com.rokid.skill.protocol.response.response.action.directive.confirm.ResConfirm;
import com.rokid.skill.protocol.response.response.action.directive.media.ResMedia;
import com.rokid.skill.protocol.response.response.action.directive.media.mediaitem.ResMediaItem;
import com.rokid.skill.protocol.response.response.action.directive.pickup.ResPickup;
import com.rokid.skill.protocol.response.response.action.directive.voice.ResVoice;
import com.rokid.skill.protocol.response.response.action.directive.voice.item.ResVoiceItem;
import com.rokid.skill.protocol.response.response.card.ResCard;
import com.rokid.skill.protocol.response.response.card.chat.ResChatCard;
import com.rokid.skill.protocol.response.session.ResSession;

public class ResponseBuilder {
	private ResResponse response;
	private boolean shouldEndSession = false;// 执行完这个action完毕
	private String actionType = ResAction.ACTION_TYPE_NORMAL;
	private HashMap<String, Object> attributes;
	private ResVoice resVoice;
	private ResConfirm resConfirm;
	private ResMedia resMedia;
	private ResPickup pickup;
	private ResCard card;

	public static ResponseBuilder build() {
		return new ResponseBuilder();
	}

	public ResponseBuilder() {
		response = buildEmptyActionResponse(ResResponse.RES_VERSION_V2, null, ResAction.ACTION_VERSION_V2, actionType,
				shouldEndSession);
	}

	/**
	 * 下一次请求原样带回的参数
	 * 
	 * @param attributes
	 * @return
	 */
	public ResponseBuilder setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
		return this;
	}

	/**
	 * 播放TTS
	 * 
	 * @param voiceId
	 * @param voiceContent
	 * @return
	 */
	public ResponseBuilder voicePlay(String voiceId, String voiceContent) {
		if (resVoice == null) {
			resVoice = new ResVoice();
		}
		ResVoiceItem resVoiceItem = new ResVoiceItem();
		resVoiceItem.setItemId(voiceId);
		resVoiceItem.setTts(voiceContent);
		resVoice.setAction(ResVoice.VOICE_ACTION_PLAY);
		resVoice.setItem(resVoiceItem);
		return this;
	}

	/**
	 * 暂停Media
	 * 
	 * @return
	 */
	public ResponseBuilder voicePause() {
		if (resVoice == null) {
			resVoice = new ResVoice();
		}
		resVoice.setAction(ResVoice.VOICE_ACTION_PAUSE);
		return this;
	}

	/**
	 * 继续播放TTS
	 * 
	 * @return
	 */
	public ResponseBuilder voiceResume() {
		if (resVoice == null) {
			resVoice = new ResVoice();
		}
		resVoice.setAction(ResVoice.VOICE_ACTION_RESUME);
		return this;
	}

	/**
	 * 停止播放TTS
	 * 
	 * @return
	 */
	public ResponseBuilder voiceStop() {
		if (resVoice == null) {
			resVoice = new ResVoice();
		}
		resVoice.setAction(ResVoice.VOICE_ACTION_STOP);
		return this;
	}

	/**
	 * 关闭Voice的EventRequest
	 * 
	 * @return
	 */
	public ResponseBuilder voiceEventDisable() {
		if (resVoice == null) {
			resVoice = new ResVoice();
		}
		resVoice.setDisableEvent(true);
		return this;
	}

	/**
	 * 发起confirm
	 * 
	 * @param confirmIntent
	 * @param confirmSlot
	 * @param optionWords
	 * @return
	 */
	public ResponseBuilder confirmOpen(String confirmIntent, String confirmSlot, List<String> optionWords) {
		if (resConfirm == null) {
			resConfirm = new ResConfirm();
		}
		resConfirm.setConfirmSlot(confirmSlot);
		resConfirm.setConfirmIntent(confirmIntent);
		resConfirm.setOptionWords(optionWords);
		return this;
	}

	/**
	 * 播放音频
	 * 
	 * @param mediaId
	 * @param mediaToken
	 * @param mediaUrl
	 * @param offsetInMilliseconds
	 * @return
	 */
	public ResponseBuilder audioPlay(String audioId, String audioToken, String audioUrl, long offsetInMilliseconds) {
		return mediaPlay(audioId, audioToken, ResMediaItem.MEDIA_TYPE_AUDIO, audioUrl, offsetInMilliseconds);
	}

	/**
	 * 播放视频
	 * 
	 * @param mediaId
	 * @param mediaToken
	 * @param mediaUrl
	 * @param offsetInMilliseconds
	 * @return
	 */
	public ResponseBuilder videoPlay(String mediaId, String videoToken, String videoUrl, long offsetInMilliseconds) {
		return mediaPlay(mediaId, videoToken, ResMediaItem.MEDIA_TYPE_VIDEO, videoUrl, offsetInMilliseconds);
	}

	/**
	 * 播放Media
	 * 
	 * @param mediaType
	 * @param mediaId
	 * @param mediaToken
	 * @param mediaUrl
	 * @param offsetInMilliseconds
	 * @return
	 */
	public ResponseBuilder mediaPlay(String mediaId, String mediaToken, String mediaType, String mediaUrl,
			long offsetInMilliseconds) {
		if (resMedia == null) {
			resMedia = new ResMedia();
		}
		ResMediaItem resMediaItem = new ResMediaItem();
		resMediaItem.setItemId(mediaId);
		resMediaItem.setType(mediaType);
		resMediaItem.setToken(mediaToken);
		resMediaItem.setUrl(mediaUrl);
		resMediaItem.setOffsetInMilliseconds(offsetInMilliseconds);
		resMedia.setAction(ResMedia.MEDIA_ACTION_PLAY);
		resMedia.setItem(resMediaItem);
		return this;
	}

	/**
	 * 暂停Media
	 * 
	 * @return
	 */
	public ResponseBuilder mediaPause() {
		if (resMedia == null) {
			resMedia = new ResMedia();
		}
		resMedia.setAction(ResMedia.MEDIA_ACTION_PAUSE);
		return this;
	}

	/**
	 * 继续播放Meida
	 * 
	 * @return
	 */
	public ResponseBuilder mediaResume() {
		if (resMedia == null) {
			resMedia = new ResMedia();
		}
		resMedia.setAction(ResMedia.MEDIA_ACTION_RESUME);
		return this;
	}

	/**
	 * 停止播放TTS
	 * 
	 * @return
	 */
	public ResponseBuilder mediaStop() {
		if (resMedia == null) {
			resMedia = new ResMedia();
		}
		resMedia.setAction(ResMedia.MEDIA_ACTION_STOP);
		return this;
	}

	/**
	 * 关闭Voice的EventRequest
	 * 
	 * @return
	 */
	public ResponseBuilder mediaEventDisable() {
		if (resMedia == null) {
			resMedia = new ResMedia();
		}
		resMedia.setDisableEvent(true);
		return this;
	}

	/**
	 * 打开拾音
	 * 
	 * @return
	 */
	public ResponseBuilder pickupOpen() {
		return pickupOpen(null);
	}

	/**
	 * 打开识音，并且设置重复播报的TTS
	 * 
	 * @param retryTts
	 * @return
	 */
	public ResponseBuilder pickupOpen(String retryTts) {
		if (pickup == null) {
			pickup = new ResPickup();
		}
		pickup.setEnable(true);
		pickup.setDurationInMilliseconds(6000l);
		if (StringUtils.isNotBlank(retryTts)) {
			pickup.setRetryTts(retryTts);
		}
		return this;
	}


	/**
	 * 执行完毕以后退出应用
	 * 
	 * @return
	 */
	public ResponseBuilder afterFinish() {
		shouldEndSession = true;
		return this;
	}

	/**
	 * 立即退出应用
	 * 
	 * @return
	 */
	public ResponseBuilder finishNow() {
		actionType = ResAction.ACTION_TYPE_EXIT;
		shouldEndSession = true;
		return this;
	}

	public ResponseBuilder sendChatCard(String content) {
		card = new ResChatCard(content);
		return this;
	}

	public ResResponse create() throws ProtocolException {
		response.getSession().setAttributes(attributes);
		List<ResDirective> directives = new ArrayList<>();
		response.getResponse().getAction().setDirectives(directives);
		response.getResponse().getAction().setShouldEndSession(shouldEndSession);
		if (!ActionUtils.checkActionType(actionType)) {
			throw new ProtocolException("actionType is error");
		}
		response.getResponse().getAction().setType(actionType);
		if (!ActionUtils.checkVoice(resVoice)) {
			throw new ProtocolException("resVoice is error");
		}
		if (resVoice != null) {
			response.getResponse().getAction().getDirectives().add(resVoice);
		}
		if (!ActionUtils.checkMedia(resMedia)) {
			throw new ProtocolException("resMedia is error");
		}
		if (resMedia != null) {
			response.getResponse().getAction().getDirectives().add(resMedia);
		}
		if (!ActionUtils.checkConfirm(resConfirm)) {
			throw new ProtocolException("resConfirm is error");
		}
		if (resConfirm != null) {
			response.getResponse().getAction().getDirectives().add(resConfirm);
		}
		if (!ActionUtils.checkPickup(pickup)) {
			throw new ProtocolException("resPickup is error");
		}
		if (pickup != null) {
			response.getResponse().getAction().getDirectives().add(pickup);
		}
		if (card != null) {
			response.getResponse().setCard(card);
		}
		return response;
	}

	private ResResponse buildEmptyActionResponse(String resVersion, HashMap<String, Object> attributes,
			String actionVersion, String actionType, boolean shouldEndSession) {
		ResResponse resResponse = new ResResponse();
		resResponse.setVersion(resVersion);
		ResSession resSession = new ResSession();
		if (attributes != null && !attributes.isEmpty()) {
			resSession.setAttributes(attributes);
		}
		resResponse.setSession(resSession);
		ResResponseContent resResponseContent = new ResResponseContent();
		ResAction resAction;
		try {
			resAction = ActionUtils.getEmptyAction(actionVersion, actionType, shouldEndSession);
			resResponseContent.setAction(resAction);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		resResponse.setResponse(resResponseContent);
		return resResponse;
	}
}
