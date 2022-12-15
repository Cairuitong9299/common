package com.bdp.idmapping.jedis;

import redis.clients.jedis.HostAndPort;
import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/5 - 11 - 05 - 16:49
 * @Description: com.bdp.idmapping.jedis
 * @version: 1.0
 */
public interface RedisConfig {

    Set<HostAndPort> getNodes();

    void setNodes(Set<HostAndPort> var1);

    String getNode();

    String getPassowrd();

    int getConncetionTimeout();

    int getSotimeOut();

    int getMaxAttempts();

    int getMaxTotal();

    int getMaxIdle();

    int getMinIdle();

    int getMaxWaitMillis();

    int getPort();
}
