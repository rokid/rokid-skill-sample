package com.rokid.simple.utils;

import com.rokid.simple.dto.res.ResResponse;
import com.rokid.simple.dto.res.response.ResResponseContent;
import com.rokid.simple.dto.res.response.action.ResAction;
import com.rokid.simple.dto.res.response.action.voice.ResVoice;
import com.rokid.simple.dto.res.response.action.voice.item.ResVoiceItem;

public class ResponseUtils {
	private static final String RESVERSION = "2.0.0";
	private static final String CARDVERSION = "2.0.0";

	public static ResResponse getTTSResponse(boolean souldEndSession, String ttscontent) {
		ResResponse resResponse = new ResResponse();
		resResponse.setVersion(RESVERSION);
		ResResponseContent resResponseContent = new ResResponseContent();
		resResponseContent.setCard(null);
		ResAction resAction = new ResAction();
		resAction.setVersion(CARDVERSION);
		resAction.setShoudEndSession(souldEndSession);
		resAction.setType("NORMAL");
		ResVoice voice = new ResVoice();
		voice.setNeedEventCallback(false);
		voice.setBehaviour("REPLACE_ALL");
		ResVoiceItem resVoiceItem = new ResVoiceItem();
		resVoiceItem.setTts(ttscontent);
		voice.setItem(resVoiceItem);
		resAction.setVoice(voice);
		resResponseContent.setAction(resAction);
		resResponse.setResponse(resResponseContent);
		return resResponse;
	}

}
