package com.api.ratelimiter.dto;

public class RateLimitRequest {

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