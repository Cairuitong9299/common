package com.bdp.idmapping.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: CAI
 * @Date: 2022/11/5 - 11 - 05 - 17:08
 * @Description: com.bdp.idmapping.utils
 * @version: 1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
    private static ApplicationContext applicationContext;

    public ApplicationContextHolder() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
        logger.info("ApplicationContext holder init");
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBeans(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }
}
