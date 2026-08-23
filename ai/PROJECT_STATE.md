# PROJECT STATE

---
# Current Sprint

Sprint 12 – Production Deployment

Status

🟢 Completed

---

# Current Phase

Sprint 12 – Production Deployment Completed

Preparing for Sprint 13 – Load Testing & Performance Benchmarking
---
# Completed Tasks

## Sprint 1

- [x] Project initialization
- [x] Documentation
- [x] Architecture design

---

## Sprint 2

- [x] Docker setup
- [x] Redis container
- [x] Spring Boot ↔ Redis integration
- [x] Redis configuration

---

## Sprint 3

- [x] RedisKeyBuilder
- [x] RedisRepository
- [x] RateLimiterService
- [x] Fixed Window implementation
- [x] Redis request counter
- [x] Redis TTL handling

---

## Sprint 4 – REST API

- [x] Created temporary RateLimiterVerifier
- [x] Verified request counting
- [x] Verified request rejection
- [x] Verified TTL expiration
- [x] Verified Redis integration
- [x] Created REST API
- [x] Added Request DTO
- [x] Added Response DTO
- [x] Added RateLimiterController
- [x] Returned HTTP 200 for allowed requests
- [x] Returned HTTP 429 for rejected requests
- [x] Verified API using Postman
- [x] Removed temporary verification classes

---

## Sprint 5 – Validation & Configuration

- [x] Added Bean Validation dependency
- [x] Added `@Valid` request validation
- [x] Added `@NotBlank` validation for `userId`
- [x] Verified validation occurs before controller execution
- [x] Created `ErrorResponse` DTO
- [x] Created `GlobalExceptionHandler`
- [x] Added `@RestControllerAdvice`
- [x] Added `@ExceptionHandler`
- [x] Added validation error response
- [x] Returned HTTP 400 for validation failures
- [x] Verified validation using Postman
- [x] Externalized rate limiter configuration
- [x] Moved maximum request limit to `application.properties`
- [x] Moved rate limit window to `application.properties`
- [x] Used Spring `@Value` for configuration injection
- [x] Used constructor injection for configuration values
- [x] Used `Duration` for rate limit window configuration

---

## Sprint 6 – Error Handling & Logging

- [x] Introduced SLF4J logging
- [x] Replaced temporary `System.out.println()` debugging
- [x] Added class-specific logger to `RateLimiterService`
- [x] Added WARN logging for rate limit violations
- [x] Added class-specific logger to `GlobalExceptionHandler`
- [x] Added WARN logging for validation failures
- [x] Added catch-all exception handler
- [x] Added generic HTTP 500 response for unexpected exceptions
- [x] Prevented internal exception details from being returned to clients
- [x] Added ERROR logging with exception details and stack trace
- [x] Verified HTTP 500 handling with a temporary test exception
- [x] Removed temporary test endpoint
- [x] Verified logging behavior

---

## Sprint 7 – Automated Testing

### Service Unit Testing

- [x] Created `RateLimiterServiceTest`
- [x] Added JUnit 5 testing
- [x] Added Mockito mocking
- [x] Mocked `RedisRepository`
- [x] Tested first request is allowed
- [x] Tested TTL is set for the first request
- [x] Tested request at the limit is allowed
- [x] Tested request beyond the limit is rejected
- [x] Tested subsequent requests do not reset TTL

### Controller Testing

- [x] Created `RateLimiterControllerTest`
- [x] Added MockMvc
- [x] Tested HTTP 200 for allowed requests
- [x] Tested `allowed: true` response
- [x] Tested HTTP 429 for rejected requests
- [x] Tested `allowed: false` response
- [x] Tested HTTP 400 for invalid `userId`
- [x] Tested validation error response

### Test Verification

- [x] Service tests passing
- [x] Controller tests passing
- [x] Full test suite passing
- [x] 8 automated tests passing

## Sprint 8 – Integration Testing

- [x] Created `RateLimiterIntegrationTest`
- [x] Verified Spring application context loads
- [x] Configured MockMvc with real Spring context
- [x] Tested real Controller → Service → Repository → Redis flow
- [x] Tested allowed request using real Redis
- [x] Tested request rejection after rate limit is exceeded
- [x] Tested Redis TTL expiration
- [x] Verified counter resets after the window expires
- [x] Added test-specific rate limiter configuration
- [x] Verified integration tests with Docker Redis

---

# Current Algorithm

Fixed Window Counter

Status

🟢 Verified

Configuration


Maximum Requests: 5
Window: 1 minute

## Sprint 9 – Swagger/OpenAPI

- [x] Added Springdoc OpenAPI dependency
- [x] Configured Swagger UI
- [x] Added OpenAPI API metadata
- [x] Documented rate limiter endpoint
- [x] Documented request schema
- [x] Documented response schemas
- [x] Documented HTTP 200 response
- [x] Documented HTTP 400 response
- [x] Documented HTTP 429 response
- [x] Documented HTTP 500 response
- [x] Verified Swagger UI
- [x] Tested API through Swagger UI

# Next Milestone

# Pending Features

- Containerize Spring Boot application
- CI/CD
- Load Testing
- Production configuration
- Deployment
- Monitoring/observability improvements
Containerized Application

## Sprint 10 – Containerization

- [x] Created application Dockerfile
- [x] Built Spring Boot production JAR
- [x] Built Docker image
- [x] Added Spring Boot service to Docker Compose
- [x] Configured Spring Boot container to connect to Redis
- [x] Added Redis healthcheck
- [x] Added Redis health-based startup dependency
- [x] Verified container-to-container communication
- [x] Verified API through Docker
- [x] Verified rate limiting inside Docker
- [x] Verified HTTP 429 inside Docker

# Pending Features

- CI/CD
- Load Testing
- Production configuration
- Deployment
- Monitoring/observability improvements


# Next Milestone

CI/CD Pipeline

---

## Sprint 11 – CI/CD

- [x] Created GitHub Actions workflow
- [x] Configured CI for the `develop` branch
- [x] Configured Java 21
- [x] Configured Maven dependency caching
- [x] Added Redis service container
- [x] Automated unit tests
- [x] Automated integration tests
- [x] Verified 12 tests passing in CI
- [x] Added Maven package step
- [x] Added Docker image build step
- [x] Verified Docker image builds successfully in CI

## Sprint 12 – Production Configuration & Deployment

- [x] Externalized Redis configuration
- [x] Externalized server configuration
- [x] Externalized rate limiter configuration
- [x] Added environment-based configuration for Docker
- [x] Created multi-stage production Dockerfile
- [x] Verified Docker image builds without requiring local `target/`
- [x] Created Render Key Value datastore
- [x] Deployed Spring Boot application to Render
- [x] Connected deployed application to remote Redis
- [x] Verified live REST API
- [x] Verified live rate limiting
- [x] Verified HTTP 429 behavior

## Sprint 13 – Load Testing & Performance Benchmarking

Status: 🟢 Completed

### Load Testing

- [x] Installed Apache JMeter 5.6.3
- [x] Created JMeter load-test plan
- [x] Tested rate-limit enforcement with concurrent requests
- [x] Added per-request UUID generation for unique rate-limit identities
- [x] Benchmarked 1,000 requests
- [x] Benchmarked 5,000 requests
- [x] Benchmarked 10,000 requests
- [x] Tested up to 200 concurrent users
- [x] Verified 0% errors in clean performance benchmarks
- [x] Measured throughput and latency under increasing load

### Measured Results

| Requests | Concurrent Users | Throughput | Avg Latency | Error Rate |
|---:|---:|---:|---:|---:|
| 1,000 | 50 | ~201 req/s | 3 ms | 0% |
| 5,000 | 100 | ~499 req/s | 7 ms | 0% |
| 10,000 | 200 | ~1,734–2,101 req/s | 61–76 ms | 0% |

### Benchmark Environment

- Apache JMeter 5.6.3
- Spring Boot application
- Redis 7
- Docker Compose
- Local development environment
- HTTP POST rate-limit endpoint

### Benchmark Notes

The 10,000-request benchmark produced approximately 1.7K–2.1K requests/sec across repeated runs with 0% errors under 200 concurrent users.

These results represent observed performance in the local Docker/JMeter environment and are not treated as the production capacity of the service.

---

## Next Milestone

Sprint 14 – Observability & Production Readiness




# Pending Features


- Production Health Endpoint
- Monitoring/Observability improvements
- Advanced Rate Limiting Strategies

# Next Milestone

Production Deployment