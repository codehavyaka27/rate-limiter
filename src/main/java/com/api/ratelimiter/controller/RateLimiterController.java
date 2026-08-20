package com.api.ratelimiter.controller;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.api.ratelimiter.dto.ErrorResponse;
@RestController
@RequestMapping("/api/v1")
public class RateLimiterController {

    private final RateLimiterService rateLimiterService;

    public RateLimiterController(RateLimiterService rateLimiterService) {

        this.rateLimiterService = rateLimiterService;
    }

    @Operation(
            summary = "Check rate limit",
            description = "Checks whether the user is allowed to make another request."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request allowed",
                    content = @Content(
                            schema = @Schema(implementation = RateLimitResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "429",
                    description = "Rate limit exceeded",
                    content = @Content(
                            schema = @Schema(implementation = RateLimitResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/rate-limit")
    public ResponseEntity<RateLimitResponse> checkRateLimit(
            @Valid @RequestBody RateLimitRequest request) {

        boolean allowed = rateLimiterService.allowRequest(request.getUserId());

        if (allowed) {
            return ResponseEntity.ok(new RateLimitResponse(true));
        }

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new RateLimitResponse(false));
    }


}