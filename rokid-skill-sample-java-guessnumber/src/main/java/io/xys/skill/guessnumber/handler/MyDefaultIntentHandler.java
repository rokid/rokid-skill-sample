package io.xys.skill.guessnumber.handler;

import static io.xys.skill.guessnumber.constant.TtsConstant.EXIT_GAME_TTS;
import static io.xys.skill.guessnumber.constant.TtsConstant.WELCOME_TTS;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.rokid.skill.dispatcher.RokidDefaultIntentHandler;
import com.rokid.skill.protocol.IntentContent;
import com.rokid.skill.protocol.RokidContext;
import com.rokid.skill.protocol.RokidRequest;
import com.rokid.skill.protocol.RokidResponse.CardType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

/**
 * 技能欢迎处理类
 *
 * @author lion.xys
 */
@Slf4j
@Component
public class MyDefaultIntentHandler implements RokidDefaultIntentHandler {

  /**
   * intent命中访问
   */
  private final CounterService counterService;

  /**
   * intent执行耗时
   */
  private final GaugeService gaugeService;

  @SuppressWarnings("SpringJavaAutowiringInspection")
  public MyDefaultIntentHandler(
      CounterService counterService,
      GaugeService gaugeService) {
    this.counterService = counterService;
    this.gaugeService = gaugeService;
  }

  @Override
  public void handleWelcomeIntent(RokidRequest<IntentContent> request) {

    log.info("get welcome intent request : {}", request);

    long startTime = System.currentTimeMillis();

    RokidContext.setVoice(WELCOME_TTS);

    String metricKey = Joiner.on(".").skipNulls().join("INTENT", "WELCOME", "SUCCESS");

    counterService.increment(metricKey);

    gaugeService.submit(metricKey, System.currentTimeMillis() - startTime);

  }

  @Override
  public void hadnleExitIntent(RokidRequest<IntentContent> rokidRequest) {

    log.info("get exit intent request : {}", rokidRequest);

    long startTime = System.currentTimeMillis();

    // 结束session
    RokidContext.setShouldEndSession(true);

    // 退出技能语音提示
    RokidContext.setVoice(EXIT_GAME_TTS);

    String metricKey = Joiner.on(".").skipNulls().join("INTENT", "EXIT", "SUCCESS");

    counterService.increment(metricKey);

    gaugeService.submit(metricKey, System.currentTimeMillis() - startTime);

  }

}
