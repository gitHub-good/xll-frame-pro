package com.xll.frame.starter.security.mask.strategy;

/**
 * 功能描述: <br>
 * <p>
 *  <脱敏策略>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 15:41
 * @version 1.0.0
 */
public interface IMaskStrategy {

    /**
     * 数据脱敏
     *
     * @param str       原始字符串
     * @param character 脱敏符号
     * @param left      左侧保留位数
     * @param right     右侧保留位数
     * @return 脱敏后的数据
     */
    String mask(String str, char character, int left, int right);
}
