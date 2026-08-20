package com.api.ratelimiter.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.api.ratelimiter.exception.GlobalExceptionHandler;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.ratelimiter.service.RateLimiterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class RateLimiterControllerTest {

    @Mock
    private RateLimiterService rateLimiterService;
    private MockMvc mockMvc;

    private RateLimiterController rateLimiterController;

    @BeforeEach
    void setUp() {

        rateLimiterController =
                new RateLimiterController(rateLimiterService);

        LocalValidatorFactoryBean validator =
                new LocalValidatorFactoryBean();

        validator.afterPropertiesSet();

        mockMvc = MockMvcBuilders
                .standaloneSetup(rateLimiterController)
                .setValidator(validator)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturn200WhenRequestIsAllowed() throws Exception {

        when(rateLimiterService.allowRequest("user1"))
                .thenReturn(true);

        mockMvc.perform(
                        post("/api/v1/rate-limit")
                                .contentType("application/json")
                                .content("""
                            {
                                "userId": "user1"
                            }
                            """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allowed").value(true));
    }
    @Test
    void shouldReturn429WhenRequestIsRejected() throws Exception {

        when(rateLimiterService.allowRequest("user1"))
                .thenReturn(false);

        mockMvc.perform(
                        post("/api/v1/rate-limit")
                                .contentType("application/json")
                                .content("""
                            {
                                "userId": "user1"
                            }
                            """)
                )
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.allowed").value(false));
    }

    @Test
    void shouldReturn400WhenUserIdIsBlank() throws Exception {

        mockMvc.perform(
                        post("/api/v1/rate-limit")
                                .contentType("application/json")
                                .content("""
                            {
                                "userId": ""
                            }
                            """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("userId cannot be blank"));
    }

}