package com.bdp.idmapping.jedis;


import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/5 - 11 - 05 - 17:02
 * @Description: com.bdp.idmapping.jedis
 * @version: 1.0
 */
@Component
public class UniqueSsoidImeiConfig implements RedisConfig {

    private String node = "120.76.216.20";

    private int port = 6379;

    private String password = "123456";
    private int connectionTimeout = 2000;
    private int soTimeout = 2000;
    private int maxAttempts = 5;
    private int maxTotal = 5;
    private int maxIdle = 5;
    private int minIdle = 5;
    private int maxWaitMillis = 1000;

    private Set<HostAndPort> nodes = new HashSet<>();

    @Override
    public String getNode() {
        return node;
    }

    @Override
    public String getPassowrd() {
        return password;
    }

    @Override
    public int getConncetionTimeout() {
        return connectionTimeout;
    }

    @Override
    public int getSotimeOut() {
        return soTimeout;
    }

    @Override
    public int getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public int getMaxTotal() {
        return maxTotal;
    }

    @Override
    public int getMaxIdle() {
        return maxIdle;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    @Override
    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    @Override
    public Set<HostAndPort> getNodes() {
        return nodes;
    }

    @Override
    public void setNodes(Set<HostAndPort> var1) {
    }
    @Override
    public int getPort() {
        return port;
    }
}
