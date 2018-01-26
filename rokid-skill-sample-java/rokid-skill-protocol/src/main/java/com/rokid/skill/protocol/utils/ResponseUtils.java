package com.rokid.skill.protocol.utils;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.google.gson.GsonBuilder;
import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.response.response.action.directive.media.mediaitem.ResMediaItem;

/**
 * @类描述 用于快速构造CloudApp的Response的工具类
 * @对应结构以及命名 Response对应ResResponse：协议内容； response对应ResResponseContent：响应；
 *           action对应ResAction：事件； voice：文本； media：媒体； confirm:主动询问；
 *           confirmIntent：主动询问意图（比如询问歌手）； confirmSlot：主动询问意图内容（比如歌手）；
 *           optionWords：主动询问辅助信息； 场景化：scen；非场景化：cut
 */
public class ResponseUtils {

	/**
	 * @方法 构造一个退出应用的协议内容
	 * @描述 客户端接收到该协议内容以后会立即退出应用
	 * @使用场景 当我们的应用需要退出的时候返回这个协议内容
	 * @注意 此协议内容的版本以及事件版本号都已经被固定为RES_VERSION_V2和ACTION_VERSION_V2,如协议版本变更，请自行修正一下；另外应用退出以后，nlp那边的session也会被清理
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildExitNowResponse() throws ProtocolException {
		return ResponseBuilder.build().finishNow().create();
	}

	/**
	 * @方法 构造一个空的协议内容，Voice以及Media继续保持原有执行状态
	 * @描述 客户端接收到该协议内容以后不会对当前的操作有任何的影响
	 * @使用场景 当我们的应用接受到不需要处理，需要忽略的Event请求是，我们可以返回该响应
	 * @注意 此协议内容的版本以及事件版本号都已经被固定为RES_VERSION_V2和ACTION_VERSION_V2,如协议版本变更，请自行修正一下，另外此协议内容会的shouldEndSession=false的
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildIngoreEventResponse() throws ProtocolException {
		return ResponseBuilder.build().create();
	}

	/**
	 * @方法 播报一句TTS,Media继续保持原有执行状态,并且有EventRequest
	 * @param voiceId
	 *            tts的ID，可以为空
	 * @param voiceContent
	 *            tts 内容
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildVoicePlayResponse(String voiceId, String voiceContent) throws ProtocolException {
		if (StringUtils.isBlank(voiceContent)) {
			throw new ProtocolException("voiceContent Can not be null");
		}
		return ResponseBuilder.build().voicePlay(voiceId, voiceContent).create();
	}

	/**
	 * @方法 暂停播放Voice
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildVoicePauseResponse() throws ProtocolException {
		return ResponseBuilder.build().voicePause().create();
	}

	/**
	 * @方法 继续播放Voice
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildVoiceResumeResponse() throws ProtocolException {
		return ResponseBuilder.build().voiceResume().create();
	}

	/**
	 * @方法 停止播放Voice
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildVoiceStopResponse() throws ProtocolException {
		return ResponseBuilder.build().voiceStop().create();
	}

	/**
	 * @方法 播放Media事件，TTS继续保持原有执行状态，
	 * 
	 * @param mediaToken
	 * @param mediaType
	 * @param mediaUrl
	 * @param mediaOffset
	 * @return
	 */
	public static ResResponse buildMediaPlayResponse(String mediaId, String mediaToken, String mediaType,
			String mediaUrl, long mediaOffset) throws ProtocolException {
		if (StringUtils.isBlank(mediaUrl)) {
			throw new ProtocolException("mediaUrl Can not be null");
		}
		if (mediaOffset < 0) {
			throw new ProtocolException("mediaOffset Can not be < 0");
		}
		if (!ResMediaItem.MEDIA_TYPE_AUDIO.equals(mediaType) && ResMediaItem.MEDIA_TYPE_VIDEO.equals(mediaType)) {
			throw new ProtocolException("mediaType is error");
		}
		return ResponseBuilder.build().mediaPlay(mediaId, mediaToken, mediaType, mediaUrl, mediaOffset).create();
	}

	/**
	 * @方法 暂停播放Media
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildMediaPauseResponse() throws ProtocolException {
		return ResponseBuilder.build().mediaPause().create();
	}

	/**
	 * @方法 继续播放Media
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildMediaResumeResponse() throws ProtocolException {
		return ResponseBuilder.build().mediaResume().create();
	}

	/**
	 * @方法 停止播放Media
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildMediaStopResponse() throws ProtocolException {
		return ResponseBuilder.build().mediaStop().create();
	}

	/**
	 * @方法 暂停播放Media和Voice
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildPauseResponse() throws ProtocolException {
		return ResponseBuilder.build().voicePause().mediaPause().create();
	}

	/**
	 * @方法 继续播放Voice和Meida
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildResumeResponse() throws ProtocolException {
		return ResponseBuilder.build().voiceResume().mediaResume().create();
	}

	/**
	 * @方法 停止播放Voice和Media
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static ResResponse buildStopResponse() throws ProtocolException {
		return ResponseBuilder.build().voiceStop().mediaStop().create();
	}

	/**
	 * 构造一个Confirm，并且把Media停止
	 * 
	 * @param voiceContent
	 * @param confirmIntent
	 *            需要在平台上面定义过得intent，不能随便写
	 * @param confirmSlot
	 *            需要在平台上面定义过得slot，不能随便写
	 * @param confirmOptionWords
	 * @return
	 */
	public static ResResponse buildConfirmOpenResponse(String voiceContent, String confirmIntent, String confirmSlot,
			List<String> confirmOptionWords) throws ProtocolException {
		if (StringUtils.isBlank(voiceContent)) {
			throw new ProtocolException("voiceContent Can not be null");
		}
		if (StringUtils.isBlank(confirmIntent)) {
			throw new ProtocolException("confirmIntent Can not be null");
		}
		if (StringUtils.isBlank(confirmSlot)) {
			throw new ProtocolException("confirmSlot Can not be null");
		}
		return ResponseBuilder.build().voicePlay(null, voiceContent).mediaStop()
				.confirmOpen(confirmIntent, confirmSlot, confirmOptionWords).create();
	}

	/**
	 * 将ResResponse变成Json字符串
	 * 
	 * @param response
	 * @return
	 */
	public static String responseToString(ResResponse response) {
		if (response != null) {
			return new GsonBuilder().disableHtmlEscaping().create().toJson(response);
		} else {
			return null;
		}

	}
}
