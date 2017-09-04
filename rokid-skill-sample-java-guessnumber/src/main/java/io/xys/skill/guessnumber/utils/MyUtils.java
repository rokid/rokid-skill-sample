package io.xys.skill.guessnumber.utils;

import java.util.Random;

/**
 * 我的工具类
 *
 * @author lion.xys
 */
public class MyUtils {

  /**
   * 基本数字单位;
   */
  private static final String[] units =
      {
          "千",
          "百",
          "十",
          "" //个位
      };

  /**
   * 大数字单位;
   */
  private static final String[] bigUnits =
      {
          "万",
          "亿"
      };

  /**
   * 中文数字;
   */
  private static final char[] numChars =
      {
          '一',
          '二',
          '三',
          '四',
          '五',
          '六',
          '七',
          '八',
          '九'
      };

  private static char numZero = '零';

  /**
   * 随机生成一个0-100的数字
   *
   * @return  随机数字
   */
  public static int randomInHundred() {

    Random random = new Random();

    return random.nextInt(100);

  }

  /**
   * 将中文数字转换为阿拉伯数字;
   *
   * @param numberCN 中文数字组
   * @return 阿拉伯数字
   */
  public static int numberCN2Arab(String numberCN) {

    String tempNumberCN = numberCN;

    // 异常数据处理;
    if (tempNumberCN == null) {
      return 0;
    }
    /**
     * nums[0]
     * 保存以千单位; nums[1]
     * 保存以万单位; nums[2]
     * 保存以亿单位;
     */
    String[] nums = new String[bigUnits.length + 1];

    // 千位以内,直接处理;
    nums[0] = tempNumberCN;

       /*
        * 分割大数字,以千为单位进行运算;
        */
    for (int i = (bigUnits.length - 1); i >= 0; i--) {

      //是否存在大单位(万,亿...);
      int find = tempNumberCN.indexOf(bigUnits[i]);

      if (find != -1) {
        String[] tempStrs = tempNumberCN.split(bigUnits[i]);

        //清空千位内容;
        if (nums[0] != null) {
          nums[0] = null;
        }

        if (tempStrs[0] != null) {
          nums[i + 1] = tempStrs[0];
        }

        if (tempStrs.length > 1) {
          tempNumberCN = tempStrs[1];

          if (i == 0) {
            nums[0] = tempStrs[1];
          }

        } else {
          tempNumberCN = null;

          break;
        }
      }
    }

    String tempResultNum = "";

    for (int i = nums.length
        - 1; i >= 0; i--) {
      if (nums[i] !=
          null) {
        tempResultNum += numberKCN2Arab(nums[i]);
      } else {
        tempResultNum +=
            "0000";
      }
    }

    return Integer.parseInt(tempResultNum);
  }

  /**
   * 处理千以内中文数字,返回4位数字字符串,位数不够以"0"补齐;
   *
   * @param numberCN 千以内数字
   * @return 阿拉伯数字
   */
  private static String numberKCN2Arab(String numberCN) {

    if ("".equals(numberCN)) {
      return "";
    }

    int[] nums = new int[4];

    if (numberCN !=
        null) {

      for (int i = 0; i <
          units.length; i++) {
        int idx = numberCN.indexOf(units[i]);

        if (idx > 0) {
          char tempNumChar = numberCN.charAt(idx - 1);

          int tempNumInt =
              numberCharCN2Arab(tempNumChar);

          nums[i] = tempNumInt;
        }
      }

      // 处理十位
      char ones = numberCN.charAt(numberCN.length() - 1);
      nums[nums.length - 1] =
          numberCharCN2Arab(ones);

      // 处理个位
      if ((numberCN.length() == 2 || numberCN.length() == 1)
          && numberCN.charAt(0) == '十') {
        nums[nums.length - 2] = 1;
      }
    }

    // 返回结果
    String tempNum =
        "";
    for (int i = 0; i < nums.length; i++) {
      tempNum += nums[i];
    }

    return (tempNum);
  }

  /**
   * 将一位中文数字转换为一位数字;eg:一返回1;
   *
   * @param onlyCNNumber 中文数字
   * @return 阿拉伯数字
   */
  public static int numberCharCN2Arab(char onlyCNNumber) {

    if (numChars[0] == onlyCNNumber) {
      return 1;
    } else if (numChars[1] == onlyCNNumber || onlyCNNumber == '两') {
      // 处理中文习惯用法(二,两)
      return 2;
    } else if (numChars[2] == onlyCNNumber) {
      return 3;
    } else if (numChars[3] == onlyCNNumber) {
      return 4;
    } else if (numChars[4] == onlyCNNumber) {
      return 5;
    } else if (numChars[5] == onlyCNNumber) {
      return 6;
    } else if (numChars[6] == onlyCNNumber) {
      return 7;
    } else if (numChars[7] == onlyCNNumber) {
      return 8;
    } else if (numChars[8] == onlyCNNumber) {
      return 9;
    }

    return 0;
  }

}
