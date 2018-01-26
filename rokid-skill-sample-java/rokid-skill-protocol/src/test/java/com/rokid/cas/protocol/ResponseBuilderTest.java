package com.rokid.cas.protocol;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.utils.ResponseBuilder;
import com.rokid.skill.protocol.utils.ResponseUtils;

public class ResponseBuilderTest {
//	@Test
//	public void emptyResponse() {
//		// 空的ResResponse
//		try {
//			System.out.println(
//					"emptyResponse:" + ResponseUtils.responseToString(ResponseUtils.buildIngoreEventResponse()));
//		} catch (ProtocolException e) {
//			fail(e.getMessage());
//		}
//	}

//	@Test
//	public void afterFinishResponse() {
//		// 执行完毕退出应用
//		try {
//			System.out.println("afterFinishResponse:"
//					+ ResponseUtils.responseToString(ResponseBuilder.build().afterFinish().create()));
//		} catch (ProtocolException e) {
//			fail(e.getMessage());
//		}
//	}
//
	@Test
	public void finishNowResponse() {
		// 立即退出应用
		try {
			System.out.println("finishNowResponse:"
					+ ResponseUtils.responseToString(ResponseBuilder.build().finishNow().create()));
		} catch (ProtocolException e) {
			fail(e.getMessage());
		}
	}
//	@Test
//	public void playTTS() {
//		try {
//			System.out.println("playTTS:"
//					+ ResponseUtils.responseToString(ResponseBuilder.build().voicePlay(null, "1").create()));
//		} catch (ProtocolException e) {
//			fail(e.getMessage());
//		}
//	}
//	@Test
//	public void playDisableTTS() {
//		try {
//			System.out.println("playDisableTTS:"
//					+ ResponseUtils.responseToString(ResponseBuilder.build().voicePlay(null, "1").voiceEventDisable().create()));
//		} catch (ProtocolException e) {
//			fail(e.getMessage());
//		}
//	}
//	@Test
//	public void playMediaAndVoiceResponse() {
//		// 立即退出应用
//		try {
//			System.out.println("playMediaAndVoiceResponse:" + ResponseUtils.responseToString(ResponseBuilder.build()
//					.voicePlay("", "啦啦啦").audioPlay("", "", "www.baidu.com", 0).setAttributes(null).create()));
//		} catch (ProtocolException e) {
//			fail(e.getMessage());
//		}
//	}
}
