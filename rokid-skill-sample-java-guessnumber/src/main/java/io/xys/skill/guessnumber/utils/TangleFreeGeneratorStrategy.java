package io.xys.skill.guessnumber.utils;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

/**
 * JOOQ domain 自动生成策略
 *
 * @author lion.xys
 */
public class TangleFreeGeneratorStrategy extends DefaultGeneratorStrategy {

  @Override
  public String getJavaPackageName(Definition definition, Mode mode) {
    return getTargetPackage();
  }

}
