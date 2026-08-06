package com.api.ratelimiter.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisConnectionVerifier implements CommandLineRunner {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisConnectionVerifier(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) {

        redisTemplate.opsForValue().set("project", "RateLimiter");

        String value = redisTemplate.opsForValue().get("project");

        System.out.println("Redis Value : " + value);
    }
}