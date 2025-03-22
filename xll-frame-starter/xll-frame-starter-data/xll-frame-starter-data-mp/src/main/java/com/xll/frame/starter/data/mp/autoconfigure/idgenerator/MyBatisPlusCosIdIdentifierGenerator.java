package com.xll.frame.starter.data.mp.autoconfigure.idgenerator;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import me.ahoo.cosid.snowflake.SnowflakeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

/**
 * 功能描述: <br>
 * <p>
 *  <MyBatis Plus ID 生成器 - CosId>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:36
 * @version 1.0.0
 */
public class MyBatisPlusCosIdIdentifierGenerator implements IdentifierGenerator {

    @Qualifier("__share__SnowflakeId")
    @Lazy
    @Autowired
    private SnowflakeId snowflakeId;

    @Override
    public Number nextId(Object entity) {
        return snowflakeId.generate();
    }
}
