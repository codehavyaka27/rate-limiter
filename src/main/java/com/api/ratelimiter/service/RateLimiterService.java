package com.api.ratelimiter.service;

import com.api.ratelimiter.repository.RedisRepository;
import com.api.ratelimiter.util.RedisKeyBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RateLimiterService {

    private final RedisRepository redisRepository;
    private final long maxRequests;
    private final Duration window;
    private static final Logger log =
            LoggerFactory.getLogger(RateLimiterService.class);


    public RateLimiterService(
            RedisRepository redisRepository,
            @Value("${rate-limiter.max-requests}") long maxRequests,
            @Value("${rate-limiter.window}") Duration window) {

        this.redisRepository = redisRepository;
        this.maxRequests = maxRequests;
        this.window = window;
    }

    public boolean allowRequest(String userId) {

        String key = RedisKeyBuilder.userRateLimitKey(userId);

        Long count = redisRepository.increment(key);

        if (count == 1) {
            redisRepository.expire(key, window);
        }


        if (count > maxRequests) {
            log.warn("Rate limit exceeded for user: {}", userId);
            return false;
        }
        return true;
    }
}