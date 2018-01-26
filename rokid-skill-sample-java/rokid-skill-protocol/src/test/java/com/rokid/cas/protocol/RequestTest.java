package com.rokid.cas.protocol;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.utils.RequestUtils;

public class RequestTest {

	@Test
	public void requestInfoTest() {
		String requestInfo = "{\"context\":{\"application\":{\"applicationId\":\"R233A4F187F34C94B93EE3BAECFCE2E3\"},\"device\":{\"basic\":{\"deviceId\":\"0201021712001513\",\"deviceType\":\"98EA4B548AEB4A329D21615B9ED060E5\",\"locale\":\"zh-cn\",\"masterId\":\"18657180257\",\"timestamp\":0,\"vendor\":\"910B21C0AF987D67AE7B2D50D6197421\"},\"location\":{},\"media\":{}},\"user\":{\"userId\":\"18657180257\"}},\"request\":{\"content\":{\"applicationId\":\"R233A4F187F34C94B93EE3BAECFCE2E3\",\"intent\":\"play_song\",\"slots\":{\"song\":\"看\"}},\"reqId\":\"55E0350488B9435699926CCEE106262C\",\"reqType\":\"INTENT\"},\"session\":{\"attributes\":{},\"newSession\":false,\"sessionId\":\"69447B7C013D4500B9C7BBF119BEDA89\"},\"version\":\"2.0.0\"}";
		try {
			ReqRequest reqRequest = RequestUtils.getRequest(requestInfo);
			System.out.println(reqRequest.toString());
		} catch (ProtocolException e) {
			e.printStackTrace();
			fail("数据解析失败");
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
