package com.rokid.skill.protocol.utils;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;
import com.rokid.skill.protocol.request.context.device.location.ReqLocation;
import com.rokid.skill.protocol.request.request.ReqRequestValue;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import com.rokid.skill.protocol.request.request.content.slot.Slot;

/**
 * 请求处理工具类
 * 
 * @author Bassam
 *
 */
public class RequestUtils {
	/**
	 * 获取ReqRequest对象
	 * 
	 * @param resuest
	 * @param slots
	 * @return
	 * @throws ProtocolException
	 */
	public static ReqRequest getRequest(String resuest) throws ProtocolException {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.fromJson(resuest, ReqRequest.class);
	}

	/**
	 * 获得基本请求信息
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static ReqBasicInfo getBasicInfo(ReqRequest reqRequest) {
		ReqBasicInfo reqBasicInfo = new ReqBasicInfo();
		if (reqRequest != null) {
			reqBasicInfo.setProtocolVersion(reqRequest.getVersion());
			if (reqRequest.getSession() != null) {
				reqBasicInfo.setSessionId(reqRequest.getSession().getSessionId());
			}
			if (reqRequest.getContext() != null) {
				if (reqRequest.getContext().getApplication() != null) {
					reqBasicInfo.setAppId(reqRequest.getContext().getApplication().getApplicationId());
				}
				if (reqRequest.getContext().getDevice() != null
						&& reqRequest.getContext().getDevice().getBasic() != null) {
					reqBasicInfo.setDeviceId(reqRequest.getContext().getDevice().getBasic().getDeviceId());
					reqBasicInfo.setDeviceType(reqRequest.getContext().getDevice().getBasic().getDeviceType());
					reqBasicInfo.setDeviceVendor(reqRequest.getContext().getDevice().getBasic().getVendor());
					reqBasicInfo.setTimestamp(reqRequest.getContext().getDevice().getBasic().getTimestamp());
					reqBasicInfo.setMasterId(reqRequest.getContext().getDevice().getBasic().getMasterId());
					reqBasicInfo.setLocale(reqRequest.getContext().getDevice().getBasic().getLocale());
					reqBasicInfo.setVoicetrigger(reqRequest.getContext().getDevice().getBasic().getVoicetrigger());
				}
				if (reqRequest.getContext().getUser() != null) {
					reqBasicInfo.setUserId(reqRequest.getContext().getUser().getUserId());
				}
			}
			if (reqRequest.getRequest() != null) {
				reqBasicInfo.setRequestId(reqRequest.getRequest().getReqId());
				reqBasicInfo.setActionType(reqRequest.getRequest().getReqType());
				if (reqRequest.getRequest().getContent() != null) {
					if (ReqRequestValue.REQTYPE_EVENT.equals(reqBasicInfo.getActionType())) {
						reqBasicInfo.setActionName(reqRequest.getRequest().getContent().getEvent());
					} else if (ReqRequestValue.REQTYPE_INTENT.equals(reqBasicInfo.getActionType())) {
						reqBasicInfo.setActionName(reqRequest.getRequest().getContent().getIntent());
					}
				}
			}
		}
		return reqBasicInfo;
	}

	/**
	 * 获得Media状态信息
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static ReqApplicationMedia getMediaState(ReqRequest reqRequest) {
		ReqApplicationMedia reqApplicationMedia = null;
		if (reqRequest != null && reqRequest.getContext() != null && reqRequest.getContext().getApplication() != null) {
			reqApplicationMedia = reqRequest.getContext().getApplication().getMedia();
		}
		if (reqApplicationMedia == null) {
			reqApplicationMedia = new ReqApplicationMedia();
		}
		if (StringUtils.isBlank(reqApplicationMedia.getState())) {
			reqApplicationMedia.setState(ReqApplicationMedia.STATE_IDLE);
		}
		return reqApplicationMedia;
	}

	/**
	 * 获得VoiceState
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static ReqApplicationVoice getVoiceState(ReqRequest reqRequest) {
		ReqApplicationVoice reqApplicationVoice = null;
		if (reqRequest != null && reqRequest.getContext() != null && reqRequest.getContext().getApplication() != null) {
			reqApplicationVoice = reqRequest.getContext().getApplication().getVoice();
		}
		if (reqApplicationVoice == null) {
			reqApplicationVoice = new ReqApplicationVoice();
		}
		if (StringUtils.isBlank(reqApplicationVoice.getState())) {
			reqApplicationVoice.setState(ReqApplicationVoice.STATE_IDLE);
		}
		return reqApplicationVoice;
	}

	/**
	 * 获取位置信息
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static ReqLocation getLocation(ReqRequest reqRequest) {
		ReqLocation reqLocation = null;
		if (reqRequest != null && reqRequest.getContext() != null && reqRequest.getContext().getDevice() != null) {
			reqLocation = reqRequest.getContext().getDevice().getLocation();
		}
		if (reqLocation == null) {
			reqLocation = new ReqLocation();
		}
		return reqLocation;
	}

	/**
	 * 获取Media拓展信息
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static ReqExtraMedia getMediaExtra(ReqRequest reqRequest) {
		ReqExtraMedia reqExtraMedia = null;
		if (reqRequest != null && reqRequest.getRequest() != null && reqRequest.getRequest().getContent() != null
				&& reqRequest.getRequest().getContent().getExtra() != null) {
			reqExtraMedia = reqRequest.getRequest().getContent().getExtra().getMedia();
		}
		if (reqExtraMedia == null) {
			reqExtraMedia = new ReqExtraMedia();
		}
		return reqExtraMedia;
	}

	/**
	 * 获取Voice拓展信息
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static ReqExtraVoice getVoiceExtra(ReqRequest reqRequest) {
		ReqExtraVoice reqExtraVoice = null;
		if (reqRequest != null && reqRequest.getRequest() != null && reqRequest.getRequest().getContent() != null
				&& reqRequest.getRequest().getContent().getExtra() != null) {
			reqExtraVoice = reqRequest.getRequest().getContent().getExtra().getVoice();
		}
		if (reqExtraVoice == null) {
			reqExtraVoice = new ReqExtraVoice();
		}
		return reqExtraVoice;
	}

	/**
	 * 获取属性信息
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static HashMap<String, Object> getAttributes(ReqRequest reqRequest) {
		HashMap<String, Object> attributes = null;
		if (reqRequest != null && reqRequest.getSession() != null && reqRequest.getSession().getAttributes() != null) {
			attributes = reqRequest.getSession().getAttributes();
		}
		if (attributes == null) {
			attributes = new HashMap<>();
		}
		return attributes;
	}

	/**
	 * 获取Slots
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static HashMap<String, Slot> getSlots(ReqRequest reqRequest) {
		HashMap<String, Slot> slots = null;
		if (reqRequest != null && reqRequest.getRequest() != null && reqRequest.getRequest().getContent() != null) {
			slots = reqRequest.getRequest().getContent().getSlots();
		}
		if (slots == null) {
			slots = new HashMap<>();
		}
		return slots;
	}

	/**
	 * 获取Intent Request的ASR结果
	 * 
	 * @param reqRequest
	 * @return
	 */
	public static String getSentence(ReqRequest reqRequest) {
		String scentence = "";
		if (reqRequest != null && reqRequest.getRequest() != null && reqRequest.getRequest().getContent() != null) {
			scentence = reqRequest.getRequest().getContent().getSentence();
		}
		return scentence;
	}
}
