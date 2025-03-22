package com.xll.frame.starter.data.mp.autoconfigure.idgenerator;

import com.xll.frame.starter.data.mp.enums.MyBatisPlusIdGeneratorType;
import lombok.Data;

/**
 * 功能描述: <br>
 * <p>
 *  <yBatis ID 生成器配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:38
 * @version 1.0.0
 */
@Data
public class MyBatisPlusIdGeneratorProperties {

    /**
     * ID 生成器类型
     */
    private MyBatisPlusIdGeneratorType type = MyBatisPlusIdGeneratorType.DEFAULT;

}
