package com.bdp.idmapping.manager;

import com.bdp.idmapping.config.CoreConfig;
import com.bdp.idmapping.core.IdCodeEnum;
import com.bdp.idmapping.jedis.JedisClusterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/8 - 11 - 08 - 23:14
 * @Description: com.bdp.idmapping.manager
 * @version: 1.0
 */
@Component
public class IdRelationRedisManager {

    //获取一个日志管理器
    private static final Logger logger = LoggerFactory.getLogger(IdRelationRedisManager.class);

    //集群
    @Autowired
    private JedisClusterUtil jedisClusterUtil;

    public String flow = "_QPS";

    @Autowired
    private CoreConfig coreConfig;


    //通过type和id拼接 来获取到hid
    public String getHidById(String type, String id) {
        //方法是将type和id进行拼接
        String key = getIdToHidKey(type, id);
        String hid = null;
        if (IdCodeEnum.OAID.getCode().equals(type)
                || IdCodeEnum.VAID.getCode().equals(type)
                || IdCodeEnum.UDID.getCode().equals(type)
                || IdCodeEnum.IMEI.getCode().equals(type)) {
            hid = jedisClusterUtil.getOpenIdToHidJedisCluster().get(key);
        } else {
            hid = jedisClusterUtil.getIdToHidJedisCluster().get(key);
        }
        return hid;
    }


    //通过buuid来获取到hid
    public Set<String> getHidsByBuuid(String buuid, boolean isMultipledId) {
        String key = getIdToHidKey(IdCodeEnum.BUUID.getCode(), buuid);
        return getSortSet(key, isMultipledId, jedisClusterUtil.getIdToHidJedisCluster(), coreConfig.getMaxSetSize());
    }

    //通过hid开获取buudid
    public Set<String> getBuuidByHid(String hid, boolean isMultipledId) {
        String key = getBuuidToHidKey(IdCodeEnum.HID.getCode(), hid);
        return getSortSet(key, isMultipledId, jedisClusterUtil.getIdToHidJedisCluster(), coreConfig.getMaxSetSize());
    }

    //通过hid来获取id
    public String getIdByHid(String field, String hid) {
        try {
            return jedisClusterUtil.getHidToAllJedisCluster().hget(getHidToAllKey(hid), field);
        } catch (Exception e) {
            logger.error("getIdByHid", e);
            return null;
        }
    }

    //通过hid来获取Ssoid
    public Set<String> getSsoidsByHid(String hid, boolean isMultipleId) {
        String key = this.getHidToSsoidKey(hid);
        return this.getSortSet(key, isMultipleId, jedisClusterUtil.getIdToHidJedisCluster(), coreConfig.getMaxSsoidSetSize());
    }

    //最大流量判断
    public boolean isOverLimit(String bizName, int maxFlow) {
        return getBizNameQPS(bizName) >= maxFlow;
    }

    //获取当前业务的服务流量
    public long getBizNameQPS(String bizName) {
        Jedis jedis = null;
        try {
            jedis = jedisClusterUtil.getHidToAllJedisCluster();
            jedis.set(bizName + flow, "0", "nx", "ex", 1);
            return jedis.incr(bizName + flow);
        } catch (Exception e) {
            logger.error("getBizNameQPS,e");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 9999;
    }

    //
    private Set<String> getSortSet(String key, boolean isMultipleId, Jedis jedisCluster, long maxSetSize) {
        if (isMultipleId) {
            jedisCluster.zrevrange(key, 0L, maxSetSize);
        }
        return jedisCluster.zrevrange(key, 0L, 0L);
    }

    private String getHidToSsoidKey(String id) {
        return "H2S_" + id;
    }

    private String getHidToAllKey(String hid) {
        return "H_" + hid;
    }

    private String getBuuidToHidKey(String prefix, String id) {
        return prefix + "_" + id + "_bid";
    }

    private String getIdToHidKey(String prefix, String id) {
        return prefix + "_" + id;
    }
}
