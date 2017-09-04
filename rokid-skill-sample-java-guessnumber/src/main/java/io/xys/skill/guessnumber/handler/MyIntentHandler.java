package io.xys.skill.guessnumber.handler;

import static io.xys.skill.guessnumber.constant.IntentConstant.GUESS_NUMBER;
import static io.xys.skill.guessnumber.constant.IntentConstant.RE_START_GAME;
import static io.xys.skill.guessnumber.constant.IntentConstant.START_GAME;
import static io.xys.skill.guessnumber.constant.TtsConstant.BIG_TTS;
import static io.xys.skill.guessnumber.constant.TtsConstant.BINGGO_TTS;
import static io.xys.skill.guessnumber.constant.TtsConstant.SMALL_TTS;
import static io.xys.skill.guessnumber.constant.TtsConstant.START_GAME_TTS;
import static io.xys.skill.guessnumber.domain.Numbers.NUMBERS;

import com.google.common.base.Strings;
import com.rokid.skill.dispatcher.RokidSubscribe;
import com.rokid.skill.protocol.IntentContent;
import com.rokid.skill.protocol.RokidContext;
import com.rokid.skill.protocol.RokidRequest;
import com.rokid.springboot.autoconfigure.skill.dispathcer.RokidRequestHandler;
import io.xys.skill.guessnumber.utils.MyUtils;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;

/**
 * 自定义意图处理器
 *
 * @author lion.xys
 */
@Slf4j
@RokidRequestHandler
@SuppressWarnings({"unused", "WeakerAccess"})
public class MyIntentHandler {

  private final DSLContext dsl;

  @SuppressWarnings("SpringJavaAutowiringInspection")
  public MyIntentHandler(DSLContext dsl) {

    this.dsl = dsl;

  }

  /**
   * 开始游戏意图处理
   *
   * @param request 请求参数
   */
  @RokidSubscribe(START_GAME)
  public void handleStartIntent(RokidRequest<IntentContent> request) {

    // 取得用户Id
    String userId = getUser(request);

    log.info("get userId : {}", userId);

    // 生成随机数字
    int needGuessNumber = MyUtils.randomInHundred();

    log.info("need guess number : {}", needGuessNumber);

    // 将随机数字存入到数据库
    dsl.insertInto(NUMBERS).columns(NUMBERS.USER_ID, NUMBERS.NUM).values(userId, needGuessNumber)
        .execute();

    RokidContext.setVoice(START_GAME_TTS);

  }

  /**
   * 处理重新开始游戏意图
   *
   * @param request 请求参数
   */
  @RokidSubscribe(RE_START_GAME)
  public void handleReStartIntent(RokidRequest<IntentContent> request) {

    handleStartIntent(request);

  }

  /**
   * 处理猜数字意图
   *
   * @param request 请求参数
   */
  @RokidSubscribe(GUESS_NUMBER)
  public void handleGuessNumberIntent(RokidRequest<IntentContent> request) {

    // 取得用信息
    String userId = getUser(request);

    int number = MyUtils.numberCN2Arab(RokidContext.getSlot("number"));

    log.info("user guess number : {}", number);

    int needGuessNumber = dsl.select().from(NUMBERS)
        .where(NUMBERS.USER_ID.equal(userId)).fetchOne(NUMBERS.NUM);

    log.info("random number : {}", needGuessNumber);

    String tts;

    if (number == needGuessNumber) {

      tts = BINGGO_TTS;

    } else if (number > needGuessNumber) {

      tts = BIG_TTS;

    } else {

      tts = SMALL_TTS;

    }

    RokidContext.setVoice(tts);

  }

  /**
   * 取得用户信息
   *
   * @param request 请求体
   * @return 用户Id
   */
  private String getUser(RokidRequest<IntentContent> request) {

    // 取得用户信息
    String userId = request.getContext().getUser().getUserId();

    // 如果取不到用户的情况下都默认为测试用户
    userId = Strings.isNullOrEmpty(userId) ? "test_user_id" : userId;

    log.info("get user info : {}", userId);

    return userId;
  }

}
