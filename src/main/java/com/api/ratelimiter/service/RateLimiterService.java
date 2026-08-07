package com.api.ratelimiter.service;

import com.api.ratelimiter.repository.RedisRepository;
import com.api.ratelimiter.util.RedisKeyBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private static final long MAX_REQUESTS = 5;
    private static final Duration WINDOW = Duration.ofMinutes(1);

    private final RedisRepository redisRepository;

    public RateLimiterService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public boolean allowRequest(String userId) {

        String key = RedisKeyBuilder.userRateLimitKey(userId);

        Long count = redisRepository.increment(key);

        if (count == 1) {
            redisRepository.expire(key, WINDOW);
        }

        return count <= MAX_REQUESTS;
    }
}