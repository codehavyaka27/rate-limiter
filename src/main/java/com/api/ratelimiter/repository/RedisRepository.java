package com.api.ratelimiter.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public Long increment(String key) {

        Long count = redisTemplate
                .opsForValue()
                .increment(key);

        if (count == null) {
            throw new IllegalStateException(
                    "Redis failed to increment key: " + key);
        }

        return count;
    }
    public Boolean expire(String key, Duration ttl) {

        Boolean result = redisTemplate.expire(key, ttl);

        if (result == null) {
            throw new IllegalStateException(
                    "Failed to set expiration for key: " + key);
        }

        return result;
    }

}
