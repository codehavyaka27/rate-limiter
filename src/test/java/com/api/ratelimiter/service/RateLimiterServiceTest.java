package com.api.ratelimiter.service;

import com.api.ratelimiter.repository.RedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RateLimiterServiceTest {

    private static final String USER_ID = "user1";
    private static final String KEY = "rate:user:user1";

    @Mock
    private RedisRepository redisRepository;

    private RateLimiterService rateLimiterService;

    @BeforeEach
    void setUp() {
        rateLimiterService =
                new RateLimiterService(
                        redisRepository,
                        5,
                        Duration.ofMinutes(1)
                );
    }

    @Test
    void shouldAllowFirstRequest() {
        when(redisRepository.increment(KEY))
                .thenReturn(1L);

        boolean result = rateLimiterService.allowRequest(USER_ID);

        assertTrue(result);
    }

    @Test
    void shouldSetExpiryForFirstRequest() {
        when(redisRepository.increment(KEY))
                .thenReturn(1L);

        rateLimiterService.allowRequest(USER_ID);

        verify(redisRepository).expire(
                KEY,
                Duration.ofMinutes(1)
        );
    }

    @Test
    void shouldAllowRequestAtLimit() {
        when(redisRepository.increment(KEY))
                .thenReturn(5L);

        boolean result = rateLimiterService.allowRequest(USER_ID);

        assertTrue(result);
    }

    @Test
    void shouldRejectRequestBeyondLimit() {
        when(redisRepository.increment(KEY))
                .thenReturn(6L);

        boolean result = rateLimiterService.allowRequest(USER_ID);

        assertFalse(result);
    }

    @Test
    void shouldNotResetExpiryForSubsequentRequests() {
        when(redisRepository.increment(KEY))
                .thenReturn(2L);

        rateLimiterService.allowRequest(USER_ID);

        verify(redisRepository, never()).expire(
                KEY,
                Duration.ofMinutes(1)
        );
    }
}