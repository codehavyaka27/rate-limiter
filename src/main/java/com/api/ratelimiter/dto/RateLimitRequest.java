package com.api.ratelimiter.dto;

import jakarta.validation.constraints.NotBlank;

public class RateLimitRequest {
    @NotBlank(message = "userId cannot be blank")
    private String userId;

    public RateLimitRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}