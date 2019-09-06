package com.rexen.rest.app.security;

import javax.annotation.Resource;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: GavinHacker
 * @description:
 * @date: Created in 下午4:02 2019/4/18
 * @modifiedBy:
 */
public class RedisCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        //return new ShiroCache<K, V>(name, redisTemplate);
        Cache cache = caches.get(name);

        if (cache == null) {
            cache = new ShiroCache<K, V>(name, redisTemplate);
            caches.put(name, cache);
        }
        return cache;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
