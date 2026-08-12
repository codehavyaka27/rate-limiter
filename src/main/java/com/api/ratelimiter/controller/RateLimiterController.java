package com.api.ratelimiter.controller;

import com.api.ratelimiter.dto.RateLimitRequest;
import com.api.ratelimiter.dto.RateLimitResponse;
import com.api.ratelimiter.service.RateLimiterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RateLimiterController {

    private final RateLimiterService rateLimiterService;

    public RateLimiterController(RateLimiterService rateLimiterService) {

        this.rateLimiterService = rateLimiterService;
    }

    @PostMapping("/rate-limit")
    public ResponseEntity<RateLimitResponse> checkRateLimit( @Valid @RequestBody RateLimitRequest request) {

        boolean allowed = rateLimiterService.allowRequest(request.getUserId());

        if (allowed) {
            return ResponseEntity.ok(new RateLimitResponse(true));
        }

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new RateLimitResponse(false));
    }


}