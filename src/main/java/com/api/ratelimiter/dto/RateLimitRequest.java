package com.api.ratelimiter.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
public class RateLimitRequest {
    @Schema(
            description = "Unique identifier of the user making the request",
            example = "user1"
    )
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