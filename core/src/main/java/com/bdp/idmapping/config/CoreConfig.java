package com.bdp.idmapping.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: CAI
 * @Date: 2022/11/8 - 11 - 08 - 23:33
 * @Description: com.bdp.idmapping.config
 * @version: 1.0
 */
@Component
public class CoreConfig {
    //核心配置类

    //日志
    private Logger logger = LoggerFactory.getLogger(CoreConfig.class);
    public static CoreConfig INSTANCE;
    private long maxSsoidSetSize = 5;

    private long maxSetSize = 5;

    public long getMaxSsoidSetSize() {
        return maxSsoidSetSize;
    }

    public long getMaxSetSize() {
        return maxSetSize;
    }
}
