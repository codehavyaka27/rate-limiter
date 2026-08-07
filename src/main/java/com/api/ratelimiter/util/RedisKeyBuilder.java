package com.api.ratelimiter.util;

public final class RedisKeyBuilder {

    private static final String RATE_USER_PREFIX = "rate:user:";

    private RedisKeyBuilder() {
    }

    public static String userRateLimitKey(String userId) {
        return RATE_USER_PREFIX + userId;
    }
}