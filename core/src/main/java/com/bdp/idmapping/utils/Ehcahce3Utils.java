package com.bdp.idmapping.utils;

import lombok.Data;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.spi.service.StatisticsService;
import org.ehcache.core.statistics.DefaultStatisticsService;
import org.ehcache.expiry.ExpiryPolicy;


@Data
public class Ehcahce3Utils {
    private Cache<String, String> cache;

    public static Ehcahce3Utils INSTANCE = new Ehcahce3Utils();
    private CacheManager cacheManager;

    private StatisticsService statisticsService;

     public Ehcahce3Utils() {
        init();
    }

    public void init() {

        statisticsService = new DefaultStatisticsService();

        //持久化地址
        cacheManager = CacheManagerBuilder.persistence(System.getProperty("user.dir") + "ehcache3")
                .builder(CacheManagerBuilder.newCacheManagerBuilder().withCache("ehcache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                                .heap(20, MemoryUnit.MB)
                                                .offheap(30, MemoryUnit.MB)
                                                .disk(50, MemoryUnit.MB, true))
                                .withExpiry(ExpiryPolicy.NO_EXPIRY)
                                .build()))
                .using(statisticsService)
                .build(true);
        cache = cacheManager.getCache("ehcache", String.class, String.class);
    }


}
