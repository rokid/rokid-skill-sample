package com.rokid.skill.protocol.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.response.action.ResAction;
import com.rokid.skill.protocol.response.response.action.directive.confirm.ResConfirm;
import com.rokid.skill.protocol.response.response.action.directive.media.ResMedia;
import com.rokid.skill.protocol.response.response.action.directive.media.mediaitem.ResMediaItem;
import com.rokid.skill.protocol.response.response.action.directive.pickup.ResPickup;
import com.rokid.skill.protocol.response.response.action.directive.voice.ResVoice;
import com.rokid.skill.protocol.response.response.action.directive.voice.item.ResVoiceItem;

public class ActionUtils {
	/**
	 * 
	 * @param actionVersion
	 * @param actionType
	 * @param shouldEndSession
	 * @return
	 * @throws ProtocolException
	 */
	public static ResAction getEmptyAction(String actionVersion, String actionType, boolean shouldEndSession)
			throws ProtocolException {
		ResAction resAction = new ResAction();
		if (StringUtils.isBlank(actionVersion)) {
			throw new ProtocolException("actionVersion is isBlank");
		}
		resAction.setVersion(actionVersion);
		if (StringUtils.isBlank(actionType) || (!ResAction.ACTION_TYPE_NORMAL.equals(actionType)
				&& !ResAction.ACTION_TYPE_EXIT.equals(actionType))) {
			throw new ProtocolException("actionType is error");
		}
		resAction.setType(actionType);
		resAction.setShouldEndSession(shouldEndSession);
		return resAction;
	}

	/**
	 * 构建Voice节点
	 * 
	 * @param voiceAction
	 *            详见 ResVoice.VOICE_ACTION
	 * @param disableEvent
	 *            是否关闭Event事件
	 * @param itemId
	 * @param voiceContent
	 * @return
	 * @throws ProtocolException
	 */
	public static ResVoice getResVoice(String voiceAction, boolean disableEvent, String itemId, String voiceContent)
			throws ProtocolException {
		if (checkVoiceAction(voiceAction)) {
			ResVoice resVoice = new ResVoice();
			resVoice.setAction(voiceAction);
			resVoice.setDisableEvent(disableEvent);
			if (ResVoice.VOICE_ACTION_PLAY.equals(voiceAction)) {
				if (StringUtils.isBlank(voiceContent)) {
					throw new ProtocolException("voiceContent is isBlank");
				}
				ResVoiceItem voiceItem = new ResVoiceItem();
				voiceItem.setItemId(itemId);
				voiceItem.setTts(voiceContent);
				resVoice.setItem(voiceItem);
			}
			return resVoice;
		}
		throw new ProtocolException("voiceAction is error");
	}

	/**
	 * 构建Confirm节点
	 * 
	 * @param confirmIntent
	 * @param confirmSlot
	 * @param optionWords
	 * @return
	 * @throws ProtocolException
	 */
	public static ResConfirm getResConfirm(String confirmIntent, String confirmSlot, List<String> optionWords)
			throws ProtocolException {
		if (!StringUtils.isBlank(confirmIntent) && !StringUtils.isBlank(confirmSlot)) {
			ResConfirm resConfirm = new ResConfirm();
			resConfirm.setConfirmIntent(confirmIntent);
			resConfirm.setConfirmSlot(confirmSlot);
			resConfirm.setOptionWords(optionWords);
			return resConfirm;
		} else {
			throw new ProtocolException("confirmIntent or confirmSlot isBlank");
		}
	}

	/**
	 * 检查Voice的Action事件
	 * 
	 * @param voiceAction
	 * @return
	 * @throws ProtocolException
	 */
	public static boolean checkVoiceAction(String voiceAction) throws ProtocolException {
		if (StringUtils.isBlank(voiceAction)) {
			throw new ProtocolException("Voice Directive's action is blank");
		}
		if (!ResVoice.VOICE_ACTION_PLAY.equals(voiceAction) && !ResVoice.VOICE_ACTION_PAUSE.equals(voiceAction)
				&& !ResVoice.VOICE_ACTION_RESUME.equals(voiceAction)
				&& !ResVoice.VOICE_ACTION_STOP.equals(voiceAction)) {
			throw new ProtocolException("Voice Directive's action is error");
		}
		return true;
	}

	/**
	 * 构建 ResMedia节点
	 * 
	 * @param mediaAction
	 * @param disableEvent
	 * @param itemId
	 * @param mediaToken
	 * @param mediaType
	 * @param mediaUrl
	 * @param mediaOffset
	 * @return
	 * @throws ProtocolException
	 */
	public static ResMedia getResMeida(String mediaAction, boolean disableEvent, String itemId, String mediaToken,
			String mediaType, String mediaUrl, long mediaOffset) throws ProtocolException {
		if (checkMediaAction(mediaAction)) {
			ResMedia resMedia = new ResMedia();
			resMedia.setDisableEvent(disableEvent);
			resMedia.setAction(mediaAction);
			if (ResMedia.MEDIA_ACTION_PLAY.equals(mediaAction)) {
				ResMediaItem resMediaItem = new ResMediaItem();
				if (StringUtils.isBlank(mediaUrl)) {
					throw new ProtocolException("mediaUrl isBlank");
				}
				resMediaItem.setUrl(mediaUrl);
				if (StringUtils.isBlank(mediaType) || (!ResMediaItem.MEDIA_TYPE_AUDIO.equals(mediaType)
						&& !ResMediaItem.MEDIA_TYPE_VIDEO.equals(mediaType))) {
					throw new ProtocolException("mediaType is error");
				}
				resMediaItem.setType(mediaType);
				resMediaItem.setToken(mediaToken);
				if (mediaOffset < 0) {
					throw new ProtocolException("mediaOffset is error");
				}
				resMediaItem.setOffsetInMilliseconds(mediaOffset);
				resMedia.setItem(resMediaItem);
			}
		}
		throw new ProtocolException("Error mediaAction:" + mediaAction);
	}

	/**
	 * 检查VoiceAction
	 * 
	 * @param mediaAction
	 * @return
	 * @throws ProtocolException
	 */
	public static boolean checkMediaAction(String mediaAction) throws ProtocolException {
		if (StringUtils.isBlank(mediaAction)) {
			throw new ProtocolException("Media Directive's action is blank");
		}
		if (!ResMedia.MEDIA_ACTION_PLAY.equals(mediaAction) && !ResMedia.MEDIA_ACTION_PAUSE.equals(mediaAction)
				&& !ResMedia.MEDIA_ACTION_RESUME.equals(mediaAction)
				&& !ResMedia.MEDIA_ACTION_STOP.equals(mediaAction)) {
			throw new ProtocolException("Media Directive's action is error");
		}
		return true;
	}

	/**
	 * 获取ResPickUp节点
	 * 
	 * @param enable
	 * @param durationInMilliseconds
	 * @return
	 * @throws ProtocolException
	 */
	public static ResPickup getResPickup(boolean enable, long durationInMilliseconds) throws ProtocolException {
		ResPickup resPickup = new ResPickup();
		resPickup.setEnable(enable);
		if (durationInMilliseconds < 0) {
			throw new ProtocolException("durationInMilliseconds is error");
		}
		resPickup.setDurationInMilliseconds(durationInMilliseconds);
		return resPickup;
	}

	/**
	 * 检查Action类型
	 * 
	 * @param actionType
	 * @return
	 * @throws ProtocolException
	 */
	public static boolean checkActionType(String actionType) throws ProtocolException {
		if (ResAction.ACTION_TYPE_EXIT.equals(actionType) || ResAction.ACTION_TYPE_NORMAL.equals(actionType)) {
			return true;
		}
		throw new ProtocolException("actionType is error");
	}

	/**
	 * 检查ResVoice的合法性
	 * 
	 * @param resVoice
	 * @return
	 * @throws ProtocolException
	 */
	public static boolean checkVoice(ResVoice resVoice) throws ProtocolException {
		if (resVoice != null) {
			if (!checkVoiceAction(resVoice.getAction())) {
				throw new ProtocolException("Voice Directive is error");
			}
			if (ResVoice.VOICE_ACTION_PLAY.equals(resVoice.getAction())) {
				if (resVoice.getItem() == null) {
					throw new ProtocolException("Voice Directive's action is play,but item is null");
				}
				if (StringUtils.isBlank(resVoice.getItem().getTts())) {
					throw new ProtocolException("Voice Directive's action is play,but item's tts is blank");
				}
			}
		}
		return true;
	}

	/**
	 * 检查Media节点的合法性
	 * 
	 * @param resMedia
	 * @return
	 * @throws ProtocolException
	 */
	public static boolean checkMedia(ResMedia resMedia) throws ProtocolException {
		if (resMedia != null) {
			if (!checkMediaAction(resMedia.getAction())) {
				throw new ProtocolException("Media Directivei is error");
			}
			if (ResMedia.MEDIA_ACTION_PLAY.equals(resMedia.getAction())) {
				if (resMedia.getItem() == null) {
					throw new ProtocolException("Meida Directive's action is play,but item is null");
				}
				if (StringUtils.isBlank(resMedia.getItem().getUrl())) {
					throw new ProtocolException("Meida Directive's action is play,but item's url is Blank");
				}
				if (resMedia.getItem().getOffsetInMilliseconds() < 0) {
					throw new ProtocolException(
							"Meida Directive's action is play,but item's OffsetInMilliseconds is <0");
				}
			}
		}
		return true;
	}

	/**
	 * 检查PickUp节点
	 * 
	 * @param resPickup
	 * @return
	 * @throws ProtocolException
	 */
	public static boolean checkPickup(ResPickup resPickup) throws ProtocolException {
		if (resPickup != null) {
			if (resPickup.getDurationInMilliseconds() < 0) {
				throw new ProtocolException("PickUp Directive's DurationInMilliseconds is <0");
			}
		}
		return true;
	}

	/**
	 * 检查confirm节点
	 * 
	 * @param resConfirm
	 * @return
	 * @throws ProtocolException
	 */
	public static boolean checkConfirm(ResConfirm resConfirm) throws ProtocolException {
		if (resConfirm != null) {
			if (StringUtils.isBlank(resConfirm.getConfirmIntent())
					|| StringUtils.isBlank(resConfirm.getConfirmSlot())) {
				throw new ProtocolException("Confirm Directive's Intent or slot is blank");
			}
		}
		return true;
	}
}
