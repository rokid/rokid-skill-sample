package io.xys.skill.guessnumber.constant;

/**
 * TTS 相关常量
 *
 * @author lion.xys
 */
public interface TtsConstant {

  /**
   * 欢迎TTS
   */
  String WELCOME_TTS = "HI, 欢迎来到猜数字，游戏开始后，我会生成一个0到100的随机数字，你可以告诉我你想到的" +
      "数字，我会对你的每一次猜测进行提示，直到猜中后游戏结束。";

  /**
   * 开始游戏TTS
   */
  String START_GAME_TTS = "游戏开始,请说一个0到100的数字。";

  /**
   * 重新开始游戏TTS
   */
  String RE_START_GAME_TTS = START_GAME_TTS;

  /**
   * 离开游戏TTS
   */
  String EXIT_GAME_TTS = "好的，再见";

  /**
   * 猜对了TTS
   */
  String BINGGO_TTS = "binggo, 恭喜你猜对了。你可以对我说再玩一次。";

  /**
   * 大了TTS
   */
  String BIG_TTS = "大了";

  /**
   * 小了TTS
   */
  String SMALL_TTS = "小了";

  /**
   * 错误TTS
   */
  String ERROR_TTS = "我出现了一点小问题！";

}
