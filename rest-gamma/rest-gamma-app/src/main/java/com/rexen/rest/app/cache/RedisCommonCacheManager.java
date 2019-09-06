package com.rexen.rest.app.cache;

import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: GavinHacker
 * @description:
 * @date: Created in 下午3:23 2019/4/19
 * @modifiedBy:
 */
@Component
public class RedisCommonCacheManager<K, V> {

    private static final String CACHE_PREFIX = "rest-common-cache:";

    private String cacheKey;

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    private long globExpire = 30;

    @SuppressWarnings("rawtypes")
    public RedisCommonCacheManager() {
        this.cacheKey = CACHE_PREFIX;
    }

    public V get(K key) throws CacheException {
        redisTemplate.boundValueOps(getCacheKey(key)).expire(globExpire, TimeUnit.MINUTES);
        return redisTemplate.boundValueOps(getCacheKey(key)).get();
    }

    public V put(K key, V value) throws CacheException {
        V old = get(key);
        redisTemplate.boundValueOps(getCacheKey(key)).set(value);
        return old;
    }

    public V remove(K key) throws CacheException {
        V old = get(key);
        redisTemplate.delete(getCacheKey(key));
        return old;
    }

    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    public int size() {
        return keys().size();
    }

    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }
}
