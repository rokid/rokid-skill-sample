package com.rokid.skill.protocol.request.request.content.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 系统级的Intent事件
 * 
 * @author Bassam
 *
 */
public enum RokidIntentMediaEnums {
	RESUME_INTENT("ROKID.INTENT.AUDIO_RESUME", "resumeIntent", "继续播放事件"),

	PAUSE_INTENT("ROKID.INTENT.AUDIO_PAUSE", "pauseIntent", "暂停播放事件"),

	STOP_INTENT("ROKID.INTENT.AUDIO_CANCEL", "stopIntent", "取消播放事件"),

	LOOP_INTENT("ROKID.INTENT.AUDIO_LOOPON", "loopIntent", "循环播放事件"),

	LOOPOFF_INTENT("ROKID.INTENT.AUDIO_LOOPOFF", "loopOffIntent", "取消循环播放事件"),

	NEXT_INTENT("ROKID.INTENT.AUDIO_NEXT", "nextIntent", "下一首播放事件"),

	PREVIOUS_INTENT("ROKID.INTENT.AUDIO_PREVIOUS", "previousIntent", "上一首播放事件"),

	REPEAT_INTENT("ROKID.INTENT.AUDIO_REPEAT", "repeatIntent", "重复播放事件"),

	STARTOVER_INTENT("ROKID.INTENT.AUDIO_STARTOVER", "startOverIntent", "重新播放事件"),

	SHUFFLE_INTENT("ROKID.INTENT.AUDIO_SHUFFLEON", "shuffleIntent", "随机播放事件"),

	SHUFFLEOFF_INTENT("ROKID.INTENT.AUDIO_SHUFFLEOFF", "shuffleOffIntent", "关闭随机播放事件");

	private String intent;
	private String methodName;
	private String cnHit; // 中文提示，仅作阅读代码的提示。

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getCnHit() {
		return cnHit;
	}

	public void setCnHit(String cnHit) {
		this.cnHit = cnHit;
	}

	RokidIntentMediaEnums(String intent, String methodName, String cnHit) {
		this.intent = intent;
		this.methodName = methodName;
		this.cnHit = cnHit;
	}

	public static RokidIntentMediaEnums convert(String intent) {
		for (RokidIntentMediaEnums v : RokidIntentMediaEnums.values()) {
			if (StringUtils.equals(v.intent, intent)) {
				return v;
			}
		}
		return null;
	}
}
