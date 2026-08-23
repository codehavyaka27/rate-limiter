package com.api.ratelimiter.service;

import com.api.ratelimiter.repository.RedisRepository;
import com.api.ratelimiter.util.RedisKeyBuilder;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Service
public class RateLimiterService {

    private final RedisRepository redisRepository;
    private final long maxRequests;
    private final Duration window;

    private final Counter allowedRequests;
    private final Counter rejectedRequests;

    private static final Logger log =
            LoggerFactory.getLogger(RateLimiterService.class);

    public RateLimiterService(
            RedisRepository redisRepository,
            @Value("${rate-limiter.max-requests}") long maxRequests,
            @Value("${rate-limiter.window}") Duration window,
            MeterRegistry meterRegistry) {

        this.redisRepository = redisRepository;
        this.maxRequests = maxRequests;
        this.window = window;

        this.allowedRequests = Counter.builder("rate_limiter_requests_total")
                .description("Total number of requests allowed by the rate limiter")
                .tag("result", "allowed")
                .register(meterRegistry);

        this.rejectedRequests = Counter.builder("rate_limiter_requests_total")
                .description("Total number of requests rejected by the rate limiter")
                .tag("result", "rejected")
                .register(meterRegistry);
    }

    public boolean allowRequest(String userId) {

        String key = RedisKeyBuilder.userRateLimitKey(userId);

        Long count = redisRepository.increment(key);

        if (count == 1) {
            redisRepository.expire(key, window);
        }

        if (count > maxRequests) {
            rejectedRequests.increment();

            log.warn("Rate limit exceeded for user: {}", userId);
            return false;
        }

        allowedRequests.increment();
        return true;
    }
}