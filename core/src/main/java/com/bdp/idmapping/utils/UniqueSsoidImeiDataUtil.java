package com.bdp.idmapping.utils;

import com.bdp.idmapping.core.IdCodeEnum;
import com.bdp.idmapping.domain.IdRelationV2;
import com.bdp.idmapping.jedis.JedisClusterUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/5 - 11 - 05 - 16:14
 * @Description: com.bdp.idmapping.utils
 * @version: 1.0
 */
public class UniqueSsoidImeiDataUtil {
    private static Logger logger = LoggerFactory.getLogger(UniqueSsoidImeiDataUtil.class);
    private static JedisClusterUtil uniqueSsoidImeiJediCluster = (JedisClusterUtil) ApplicationContextHolder.getApplicationContext().getBean(JedisClusterUtil.class);

    public UniqueSsoidImeiDataUtil() {

    }

    public static void setImeiToSsoidMappingData(IdRelationV2 idRelationV2) {
        try {
            String imei = idRelationV2.getImei();
            String ssoid = idRelationV2.getSsoid();
            if (StringUtils.isBlank(imei) || StringUtils.isBlank(ssoid)) {
                logger.info("UniqueSsoidImeiDataUtil setImeiToSsoidMappingData:imei-ssoid 为空[imei={},ssoid={}]", imei, ssoid);
                return;
            }
            uniqueSsoidImeiJediCluster.getUniqueSsoidImeiJedisCluster().set(IdCodeEnum.UNIQUE_SSOID.getCode() + "_" + imei, ssoid);
        } catch (Exception e) {
            logger.error("imei-ssoid-mapping 上报数据失败");
        }
    }

    public static Set<String> getImeiToSsoidRelation(Set<String> res, String sourceValue) {
        try {
            if (StringUtils.isNotBlank(sourceValue)) {
                String result = uniqueSsoidImeiJediCluster.getUniqueSsoidImeiJedisCluster().get(IdCodeEnum.UNIQUE_SSOID.getCode() + "_" + sourceValue);
                if (StringUtils.isNotBlank(result)) {
                    res.add(result);
                }
            }

        } catch (Exception e) {
            logger.error("unique-imei-ssoid-mapping 获取数据失败");
        }
        return res;
    }
}
