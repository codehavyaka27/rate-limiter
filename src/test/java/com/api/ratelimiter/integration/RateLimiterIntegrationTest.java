package com.api.ratelimiter.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class RateLimiterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowValidRequest() throws Exception {

        mockMvc.perform(
                        post("/api/v1/rate-limit")
                                .contentType("application/json")
                                .content("""
                            {
                                "userId": "integration-user"
                            }
                            """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allowed").value(true));
    }

    @Test
    void shouldRejectRequestAfterLimitIsExceeded() throws Exception {

        String userId = "integration-limit-user";

        for (int i = 1; i <= 5; i++) {
            mockMvc.perform(
                            post("/api/v1/rate-limit")
                                    .contentType("application/json")
                                    .content("""
                                {
                                    "userId": "%s"
                                }
                                """.formatted(userId))
                    )
                    .andExpect(status().isOk());
        }

        mockMvc.perform(
                        post("/api/v1/rate-limit")
                                .contentType("application/json")
                                .content("""
                            {
                                "userId": "%s"
                            }
                            """.formatted(userId))
                )
                .andExpect(status().isTooManyRequests())
                .andExpect(jsonPath("$.allowed").value(false));
    }

    @Test
    void shouldAllowRequestAgainAfterWindowExpires() throws Exception {

        String userId = "integration-expiry-user";

        for (int i = 1; i <= 5; i++) {
            mockMvc.perform(
                            post("/api/v1/rate-limit")
                                    .contentType("application/json")
                                    .content("""
                                {
                                    "userId": "%s"
                                }
                                """.formatted(userId))
                    )
                    .andExpect(status().isOk());
        }

        mockMvc.perform(
                        post("/api/v1/rate-limit")
                                .contentType("application/json")
                                .content("""
                            {
                                "userId": "%s"
                            }
                            """.formatted(userId))
                )
                .andExpect(status().isTooManyRequests());

        Thread.sleep(2500);

        mockMvc.perform(
                        post("/api/v1/rate-limit")
                                .contentType("application/json")
                                .content("""
                            {
                                "userId": "%s"
                            }
                            """.formatted(userId))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allowed").value(true));
    }
}