package com.bdp.idmapping.jedis;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;


/**
 * @Auther: CAI
 * @Date: 2022/11/5 - 11 - 05 - 16:24
 * @Description: com.bdp.idmapping.jedis
 * @version: 1.0
 */
@Component
public class JedisClusterUtil implements Serializable, InitializingBean {

    //日志文件
    private static final Logger logger = LoggerFactory.getLogger(JedisClusterUtil.class);

    private JedisPool uniqueSsoidImeiJedisCluster;

    private JedisPool idToHidJedisCluster;

    private JedisPool hidToAllJedisCluster;

    private JedisPool openIdToHidJedisCluster;

    @Autowired
    private UniqueSsoidImeiConfig uniqueSsoidImeiConfig;

    @Autowired
    private IdToHidRedisConfig idToHidRedisConfig;

    @Autowired
    private OpenIdToHidRedisConfig openIdToHidRedisConfig;

    //日志输出设置
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("start init idmapping redis cluster");
        uniqueSsoidImeiJedisCluster = initJedisCluster(uniqueSsoidImeiConfig);
        logger.info("===============id mapping redis:{} init success============", uniqueSsoidImeiJedisCluster);

        hidToAllJedisCluster = initJedisCluster(idToHidRedisConfig);
        logger.info("===============hidToAll redis:{} init success============", hidToAllJedisCluster);

        idToHidJedisCluster = initJedisCluster(idToHidRedisConfig);
        logger.info("===============idToHid redis:{} init success============", idToHidJedisCluster);

        openIdToHidJedisCluster = initJedisCluster(openIdToHidRedisConfig);
        logger.info("===============openIdToHid redis:{} init success============", openIdToHidJedisCluster);
    }

    //设置连接池
    private JedisPool initJedisCluster(RedisConfig redisConfig) {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisConfig.getMaxIdle());//最大连接数
        genericObjectPoolConfig.setMinIdle(redisConfig.getMinIdle());//最小连接数
        genericObjectPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());//最大等待时间
        return new JedisPool(genericObjectPoolConfig, redisConfig.getNode(), redisConfig.getPort(), redisConfig.getConncetionTimeout(), redisConfig.getPassowrd());

    }

    public Jedis getUniqueSsoidImeiJedisCluster() {
        return uniqueSsoidImeiJedisCluster.getResource();
    }

    public Jedis getIdToHidJedisCluster() {
        return idToHidJedisCluster.getResource();
    }

    public Jedis getHidToAllJedisCluster() {
        return hidToAllJedisCluster.getResource();
    }

    public Jedis getOpenIdToHidJedisCluster() {
        return openIdToHidJedisCluster.getResource();
    }
}
