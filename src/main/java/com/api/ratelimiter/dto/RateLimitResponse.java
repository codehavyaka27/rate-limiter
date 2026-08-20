package com.api.ratelimiter.dto;
import io.swagger.v3.oas.annotations.media.Schema;
public class RateLimitResponse {
    @Schema(
            description = "Whether the request is allowed",
            example = "true"
    )
    private boolean allowed;

    public RateLimitResponse() {
    }

    public RateLimitResponse(boolean allowed) {
        this.allowed = allowed;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
}
