package com.bdp.idmapping.service;

import com.bdp.idmapping.core.IdCodeEnum;
import com.bdp.idmapping.manager.IdRelationRedisManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/8 - 11 - 08 - 23:09
 * @Description: com.bdp.idmapping.service
 * @version: 1.0
 */
@Service
public class IdMappingService {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(IdMappingService.class);

    @Autowired
    private IdRelationRedisManager idRelationRedisManager;


    public Set<String> convertId(String sourceType, String sourceValue, String targetType, String bizName, boolean isMultipleId) {
        Set<String> targetValues = new HashSet<>();
        Set<String> hids = null;
        /*判断SSOID的值是否和sourceType相同
        如果是则查询数据库中
         */
        if (IdCodeEnum.SSOID.getCode().equals(sourceType)) {
            hids = idRelationRedisManager.getSsoidsByHid(sourceValue, isMultipleId);
            if (hids == null || hids.isEmpty()) {
                return null;
            }
            return getHidsOrOtherIds(targetType, targetValues, hids);
        } else if (IdCodeEnum.HID.getCode().equals(sourceType)) {
            return convertId(sourceValue, targetType, targetValues, isMultipleId);
        } else if (IdCodeEnum.BUUID.getCode().equals(sourceType)) {
            hids = idRelationRedisManager.getHidsByBuuid(sourceValue, isMultipleId);
            if (hids == null || hids.isEmpty()) {
                return null;
            }
            return getHidsOrOtherIds(targetType, targetValues, hids);
        } else {
            String hid = idRelationRedisManager.getHidById(sourceType, sourceValue);
            if (StringUtils.isBlank(hid)) {
                return null;
            }
            return convertId(hid, targetType, targetValues, isMultipleId);
        }
    }

    private Set<String> convertId(String sourceValue, String targetType, Set<String> targetValues, boolean isMultipleId) {
        if (IdCodeEnum.SSOID.getCode().equals(targetType)) {
            Set<String> ssoids = idRelationRedisManager.getSsoidsByHid(sourceValue, isMultipleId);
            if (ssoids == null || ssoids.isEmpty()) {
                return null;
            }
            targetValues.addAll(ssoids);
            return targetValues;
        } else if (IdCodeEnum.HID.getCode().equals(targetType)) {
            targetValues.add(sourceValue);
            return targetValues;
        } else if (IdCodeEnum.BUUID.getCode().equals(targetType)) {
            Set<String> buuids = idRelationRedisManager.getBuuidByHid(sourceValue, isMultipleId);
            if (buuids == null || buuids.isEmpty()) {
                return null;
            }
            targetValues.addAll(buuids);
            return targetValues;
        } else {
            String targetValue = idRelationRedisManager.getIdByHid(targetType, sourceValue);
            if (StringUtils.isNotBlank(targetValue)) {
                targetValues.add(targetValue);
            }
            return targetValues;
        }
    }

    private Set<String> getHidsOrOtherIds(String targetType, Set<String> targetValues, Set<String> hids) {
        if (IdCodeEnum.HID.getCode().equals(targetType)) {
            targetValues.addAll(hids);
            return targetValues;
        } else {
            hids.forEach((hid) -> {
                String targetValue = idRelationRedisManager.getIdByHid(targetType, hid);
                if (StringUtils.isNotBlank(targetValue)) {
                    targetValues.add(targetValue);
                }
            });
        }
        return targetValues;
    }
}
