package com.rokid.skill.tastecoffee.common;

import org.springframework.http.HttpStatus;

import com.rokid.skill.exception.RokidException;
import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ResponseBuilder;
import com.rokid.skill.protocol.utils.ResponseUtils;

public class ProtocolUtils {
	/**
	 * 忽略事件，比如Voice.STARTED的EventRequest来的时候我们可以使用这个操作忽略这个请求
	 * 
	 * @return
	 * @throws ProtocolException
	 */
	public static final ResResponse igonre() {
		try {
			return ResponseUtils.buildIngoreEventResponse();
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 退出
	 * 
	 * @举例 若琪停止播放
	 * @return
	 * @throws ProtocolException
	 */
	public static final ResResponse exit() throws RokidException {
		try {
			return ResponseUtils.buildExitNowResponse();
		} catch (ProtocolException e) {
			return null;
		}
	}

	/**
	 * 从0开始播放音乐，并且停止TTS
	 * 
	 * @param songId
	 * @param songUrl
	 * @return
	 * @throws CasException
	 */
	public static final ResResponse playSongAndFinished(String songId, String songUrl) throws RokidException {
		try {
			return ResponseBuilder.build().audioPlay(songId, null, songUrl, 0).mediaEventDisable().afterFinish()
					.create();
		} catch (ProtocolException e) {
			throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
					e.getCause());
		}
	}

	/**
	 * 暂停音乐
	 * 
	 * @举例 若琪暂停播放
	 * @return
	 * @throws ProtocolException
	 */
	public static final ResResponse pauseMusic() throws RokidException {
		try {
			return ResponseBuilder.build().mediaPause().mediaEventDisable().create();
		} catch (ProtocolException e) {
			throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
					e.getCause());
		}
	}
	public static final ResResponse playTTs(String content) throws RokidException {
		try {
			return ResponseBuilder.build().voicePlay(null, content).sendChatCard(content).create();
		} catch (ProtocolException e) {
			throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
					e.getCause());
		}
	}
	/**
	 * 播放完tts退出示例
	 * @param content
	 * @return
	 * @throws RokidException
	 */
	public static final ResResponse playTts(String content) throws RokidException{
		try {
			return ResponseBuilder.build().voicePlay(null, content).sendChatCard(content).afterFinish().create();
		} catch (ProtocolException e) {
			throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
					e.getCause());
		}
	}
	/**
	 *   进入六十秒拾音状态
	 * @param content
	 * @return
	 * @throws RokidException
	 */
	public static final ResResponse pickUp(String content) throws RokidException{
		try {
			return ResponseBuilder.build().voicePlay(null, content).pickupOpen().sendChatCard(content).create();
		} catch (ProtocolException e) {
			throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
					e.getCause());
		}
	}
	
	/**
	 * 继续播放音乐
	 * 
	 * @举例 若琪继续播放音乐
	 * @return
	 * @throws ProtocolException
	 */
	public static final ResResponse resumeMusic() throws RokidException {
		try {
			return ResponseBuilder.build().mediaResume().mediaEventDisable().afterFinish().create();
		} catch (ProtocolException e) {
			throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
					e.getCause());
		}
	}
}
