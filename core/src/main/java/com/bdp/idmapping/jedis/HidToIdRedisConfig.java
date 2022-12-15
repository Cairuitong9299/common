package com.bdp.idmapping.jedis;


import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/8 - 11 - 08 - 23:56
 * @Description: com.bdp.idmapping.jedis
 * @version: 1.0
 */
@Component
//接口传输到redis中的配置

public class HidToIdRedisConfig implements RedisConfig{

    //IP地址
    private String node = "120.76.216.20";

    //端口号
    private int port = 6379;

    //数据库密码
    private String password="123456";

    //超时时间
    private int conncetionTimeout = 2000;


    private int soTimeout = 2000;

    //最多尝试次数
    private int maxAttempts = 5;

    //最大连接总数
    private int maxTotal = 20;

    //最大空闲连接
    private int maxIdle = 20;
    //最小空闲连接
    private int minIdle = 20;

    //请求连接最大等待时
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
        return conncetionTimeout;
    }

    @Override
    public int getSotimeOut() {
        return 0;
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
