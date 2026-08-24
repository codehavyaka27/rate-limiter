# PROJECT STATE

---

# Current Sprint

## Sprint 15 – Distributed Consistency Verification

Status

🟢 Completed

---

# Current Phase

Sprint 15 – Distributed Consistency Verification Completed

Preparing for Sprint 16 – Production Hardening & Final Review

---

# Completed Tasks

## Sprint 1 – Project Foundation

- [x] Project initialization
- [x] Documentation
- [x] Architecture design

---

## Sprint 2 – Redis & Docker Foundation

- [x] Docker setup
- [x] Redis container
- [x] Spring Boot ↔ Redis integration
- [x] Redis configuration

---

## Sprint 3 – Rate Limiter Core

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

---

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

---

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
- [x] Created multi-stage Docker build
- [x] Verified Java 21 runtime image
- [x] Changed Docker Compose to build the application image automatically

---

## Sprint 11 – CI/CD

- [x] Created GitHub Actions workflow
- [x] Configured CI for the `develop` branch
- [x] Configured Java 21
- [x] Configured Maven dependency caching
- [x] Added Redis service container
- [x] Automated unit tests
- [x] Automated integration tests
- [x] Verified tests passing in CI
- [x] Added Maven package step
- [x] Added Docker image build step
- [x] Verified Docker image builds successfully in CI
- [x] Verified GitHub Actions workflow is GREEN

---

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

---

## Sprint 13 – Load Testing & Performance Benchmarking

Status

🟢 Completed

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

# Current Algorithm

## Fixed Window Counter

Status

🟢 Verified

Configuration


Maximum Requests: 5
Window: 1 minute
Implementation

For each user:

Request
   |
   v
Generate Redis key
   |
   v
Atomic Redis INCR
   |
   +---- count == 1 ----> Set Redis TTL
   |
   v
Compare count with limit
   |
   +---- count <= limit ----> Allow request
   |
   +---- count > limit -----> Reject with HTTP 429

Redis stores the rate-limit counter rather than application memory.

Sprint 14 – Observability & Production Readiness

Status

🟢 Completed

Actuator
 Added Spring Boot Actuator
 Exposed /actuator/health
 Exposed /actuator/info
 Exposed /actuator/prometheus
 Verified Actuator endpoints
 Verified Redis health through Actuator
 Configured health endpoint with show-details=never
Available Endpoints
/actuator/health
/actuator/info
/actuator/prometheus
Application Information
 Added application name
 Added application description
 Added application version
 Enabled application information through Actuator
 Verified /actuator/info

Current metadata:

Name: Distributed API Rate Limiter
Description: Redis-backed API rate limiting microservice
Version: 1.0.0
Micrometer & Prometheus
 Added Micrometer Prometheus registry
 Exposed Prometheus metrics
 Verified /actuator/prometheus
 Verified JVM metrics
 Verified HTTP metrics
 Verified Redis/Lettuce metrics
 Verified process metrics
Custom Rate Limiter Metrics
 Added allowed request counter
 Added rejected request counter
 Added result metric label
 Incremented allowed counter for successful requests
 Incremented rejected counter for rate-limited requests
 Verified metrics through /actuator/prometheus
 Avoided user IDs as metric labels to prevent high-cardinality metrics

Current custom metrics:

rate_limiter_requests_total{result="allowed"}
rate_limiter_requests_total{result="rejected"}
Test Updates

Adding MeterRegistry to RateLimiterService changed its constructor.

 Updated RateLimiterServiceTest
 Added SimpleMeterRegistry to the test
 Restored test compilation
 Verified Maven tests pass
Environment-Based Configuration
 Verified Redis host configuration
 Verified Redis port configuration
 Verified server port configuration
 Verified maximum request configuration
 Verified rate-limit window configuration
 Verified local defaults
 Verified Docker environment variables

Supported environment variables:

REDIS_HOST
REDIS_PORT
SERVER_PORT
RATE_LIMIT_MAX_REQUESTS
RATE_LIMIT_WINDOW

Default values:

REDIS_HOST=localhost
REDIS_PORT=6379
SERVER_PORT=8080
RATE_LIMIT_MAX_REQUESTS=5
RATE_LIMIT_WINDOW=1m
Docker Compose Improvements
 Changed application service to use Docker Compose build
 Removed dependency on manually pre-built rate-limiter:1.0 image
 Verified fresh Docker Compose build
 Verified Redis healthcheck
 Verified application waits for Redis health
 Verified application starts successfully after Redis becomes healthy
 Verified API after rebuild
 Verified Actuator after rebuild

Sprint 15 – Distributed Consistency Verification

Status

🟢 Completed

Objective

Verify that multiple Spring Boot application instances connected to the same Redis instance maintain the same rate-limit state.

Distributed Test Environment

Created:

Docker/docker-compose-distributed.yml

The test environment contains:

                    Redis
              Shared Rate State
                  redis:6379
                 /          \
                /            \
               v              v
        Rate Limiter #1   Rate Limiter #2
          localhost:8080   localhost:8081

Both application instances use:

REDIS_HOST=redis
REDIS_PORT=6379

Both instances therefore connect to the same Redis container.

Distributed Consistency Test 1

Test:

App #1 → 5 requests
App #2 → 6th request

Result:

App #1 Request 1 → allowed
App #1 Request 2 → allowed
App #1 Request 3 → allowed
App #1 Request 4 → allowed
App #1 Request 5 → allowed
App #2 Request 6 → HTTP 429

Result:

🟢 Passed

This proves App #2 observed the request counter created by App #1.

Distributed Consistency Test 2

Test:

App #2 → 5 requests
App #1 → 6th request

Result:

App #2 Request 1 → allowed
App #2 Request 2 → allowed
App #2 Request 3 → allowed
App #2 Request 4 → allowed
App #2 Request 5 → allowed
App #1 Request 6 → HTTP 429

Result:

🟢 Passed

This proves the shared-state behavior works in the reverse direction as well.

Redis State Verification

Verified the actual Redis key format from:

src/main/java/com/api/ratelimiter/util/RedisKeyBuilder.java

Current key format:

rate:user:<userId>

A fresh Redis key was created and inspected directly.

Observed:

Key:
rate:user:redis-check-224246

Value:
1

TTL:
30 seconds

Result:

🟢 Passed

This directly verifies:

Redis contains the rate-limit state
The request counter is stored in Redis
Redis TTL is applied
Redis automatically removes expired rate-limit keys

The earlier empty KEYS result was caused by the 1-minute rate-limit window expiring before Redis was inspected.

Distributed Architecture Verification

Verified architecture:

                         Redis
                    Shared Rate State
                         |
             +-----------+-----------+
             |                       |
             v                       v
        Application             Application
        Instance #1             Instance #2
        Port 8080               Port 8081

Verified behavior:

App #1
   |
   +----> Shared Redis Counter
                    ^
                    |
App #2 -------------+

Both application instances share the same per-user rate-limit counter.

Current Architecture
                    Client
                      |
                      v
              Spring Boot REST API
                      |
                      v
             RateLimiterController
                      |
                      v
              RateLimiterService
                      |
                      v
               RedisRepository
                      |
                      v
                    Redis

Distributed deployment:

                         Redis
                    Shared State
                         |
             +-----------+-----------+
             |                       |
             v                       v
        Application             Application
        Instance #1             Instance #2
        Port 8080               Port 8081

Observability:

Spring Boot Application
          |
          v
   Spring Actuator
          |
    +-----+-----+
    |     |     |
 Health  Info  Prometheus
                  |
                  v
              Micrometer
Current Project Status
Completed
 Core Fixed Window rate limiter
 Redis integration
 REST API
 Request validation
 Error handling
 Logging
 Unit testing
 Integration testing
 Swagger/OpenAPI
 Docker
 Docker Compose
 CI/CD
 Production configuration
 Render deployment
 JMeter load testing
 Performance benchmarking
 Spring Boot Actuator
 Micrometer/Prometheus
 Custom rate limiter metrics
 Environment-based configuration
 Docker Compose self-build
 README documentation
 Multiple application instance verification
 Shared Redis state verification
 Distributed rate-limit consistency verification
Verified Distributed Properties

The following properties have now been explicitly tested:

 Multiple Spring Boot instances can connect to the same Redis
 Both instances share the same per-user rate-limit counter
 App #1 can create the counter and App #2 can enforce the limit
 App #2 can create the counter and App #1 can enforce the limit
 Redis stores the rate-limit counter
 Redis TTL is applied to rate-limit keys
 Redis automatically removes expired rate-limit keys
 HTTP 429 enforcement remains consistent across application instances
Pending Features
High Priority
 Production hardening review
 Review graceful shutdown behavior
 Review configuration validation
 Review Redis failure behavior
 Review rate-limit response semantics
Medium Priority
 Add remaining-request information
 Add reset-time information
 Consider standard rate-limit response headers
 Improve API response metadata
 Review security of exposed Actuator endpoints
Future / Optional
 Token Bucket algorithm
 Leaky Bucket algorithm
 Rate limiting strategy abstraction
 Dynamic per-user limits
 API-key based rate limiting
 Prometheus + Grafana dashboard
 Advanced distributed load testing
Algorithm Scope
Implemented
Fixed Window Counter

Status:

🟢 Verified

Not Implemented
Token Bucket
Leaky Bucket

These remain future possibilities and must not be represented as implemented features.

Engineering Principles
Keep the implementation simple and explainable
Prefer measurable improvements over unnecessary features
Do not claim unverified capabilities
Keep configuration externalized
Keep Redis as the shared rate-limit state
Avoid high-cardinality Prometheus labels
Keep tests passing after every architectural change
Verify Docker builds after infrastructure changes
Verify deployed behavior after production configuration changes
Document measured performance rather than theoretical performance
Prefer production-relevant engineering improvements over resume-driven complexity
Avoid introducing Kubernetes, Kafka, or unrelated infrastructure without a concrete requirement
Next Milestone
Sprint 16 – Production Hardening & Final Review

Status

🟡 Planned

Objective

Perform a final engineering review of the rate limiter before considering the project feature-complete.

Planned Tasks
 Review API response design
 Review rate-limit headers
 Review graceful shutdown
 Review configuration validation
 Review Redis failure behavior
 Review logging and error handling
 Review Actuator exposure
 Review Docker configuration
 Run final automated test suite
 Run final Docker verification
 Run final JMeter benchmark
 Review Render deployment
 Review README
 Finalize architecture documentation