# Session Log

---

# Purpose

This document records every development session for the project.

Each session captures:

- Work completed
- Decisions made
- Problems encountered
- Lessons learned
- Next objectives

The goal is to make project progress easy to understand without reading Git history.

---

# Session 1

Date

2026-08-05

Duration

Documentation Session

---

## Objective

Create the initial project structure and engineering documentation.

---

## Completed

### Project Setup

- Created Spring Boot project
- Removed unnecessary boilerplate
- Created clean package structure
- Created documentation folder
- Created AI folder
- Created Docker folder

---

### Documentation

Completed:

- Problem Statement
- High-Level Design
- Low-Level Design
- API Contract
- Redis Data Model
- Algorithms
- Development Roadmap
- Test Plan

---

### AI Engineering Files

Completed

- PROJECT_CONTEXT.md
- PROJECT_STATE.md
- DECISIONS.md
- SESSION_LOG.md
- CODING_GUIDELINES.md
- DEVELOPMENT_RULES.md
- PROMPTS.md

---

## Decisions

- Redis selected as primary datastore.
- Fixed Window selected for Version 1.
- Layered Architecture adopted.
- Strategy Pattern planned for algorithms.

---

## Lessons Learned

- Design should come before implementation.
- Documentation reduces future confusion.
- A clear architecture simplifies coding.
- Small, well-defined sprints are easier to manage.

---

## Issues Encountered

None.

---

## Next Session

Sprint 2

Objective

Redis Integration

Tasks

- Verify Docker installation
- Create docker-compose.yml
- Start Redis container
- Configure Spring Boot Redis
- Verify Redis connectivity

---

## Status

🟢 Completed

---

## Git Information

Repository

GitHub Initialized

Branch

develop

Last Commit

Initialize project structure and documentation

================================================================================

# Session 2

Date

2026-08-06

Duration

Infrastructure Session

---

## Objective

Set up the Redis infrastructure and establish communication between Spring Boot and Redis.

---

## Completed

### Docker

- Verified Docker Desktop installation.
- Learned Docker fundamentals.
- Created docker-compose.yml.
- Started Redis container successfully.
- Verified Redis is running on port 6379.

---

### Spring Boot Configuration

- Added Redis configuration in application.properties.
- Configured application name.
- Configured server port.
- Learned Spring Boot externalized configuration.

---

### Spring Boot Concepts

Learned and understood:

- Spring Container
- Dependency Injection (DI)
- Inversion of Control (IoC)
- Auto Configuration
- RedisTemplate
- CommandLineRunner
- Infrastructure verification approach

---

### Redis Verification

Created:

- RedisConnectionVerifier

Verified:

- RedisTemplate injection
- Redis SET operation
- Redis GET operation
- Successful communication between Spring Boot and Redis

Console Output

Redis Value : RateLimiter

Infrastructure verified successfully.

---

## Decisions

- Use Spring Boot Auto Configuration instead of creating a custom RedisConfig class.
- Postpone custom Logback configuration until the logging sprint.
- Keep infrastructure verification separate from business logic.
- Repository layer will be the only layer interacting directly with RedisTemplate.

---

## Lessons Learned

- Docker containers expose services through mapped ports.
- Spring Boot automatically creates RedisTemplate.
- Dependency Injection provides infrastructure objects instead of manually creating them.
- application.properties externalizes environment-specific configuration.
- Infrastructure should always be validated before implementing business logic.
- Empty configuration files such as logback-spring.xml can prevent application startup.

---

## Issues Encountered

- IntelliJ project import confusion.
- Incorrect Java package declarations.
- Maven dependency configuration issues.
- Empty logback-spring.xml caused application startup failure.
- Spring Boot test configuration issue resolved during setup.

All issues were resolved successfully.

---

# Session 3

Date

2026-08-07

Duration

Sprint 3

---

## Objective

Implement the core Fixed Window Rate Limiter and establish clean architectural boundaries.

---

## Completed

### Infrastructure

- Verified Redis integration.
- Verified Spring Boot ↔ Redis connectivity.

---

### Implementation

Completed:

- RedisKeyBuilder
- RedisRepository
- RateLimiterService

---

### Engineering Decisions

- Introduced RedisKeyBuilder as a utility class.
- Repository owns infrastructure logic.
- Service owns business logic.
- Atomic Redis INCR selected instead of GET + SET.
- TTL applied only for the first request in a window.
- Constructor Injection adopted.
- Redis keys centralized.
- Followed KISS and YAGNI principles.

---

## Lessons Learned

- Atomic operations eliminate race conditions.
- Business logic and infrastructure logic should remain separate.
- Small APIs are easier to maintain.
- Key naming is an architectural decision.
- Design discussions reduce implementation complexity.

---

## Issues Encountered

- Incorrect package structure (folders instead of packages).
- Maven dependency issues caused by Spring Boot 4 starter selection.
- IntelliJ project structure issues.
- Resolved all issues successfully.

---

# Session 4

Date

2026-08-08

Duration

Sprint 4 – Phase 1

---

## Objective

Verify the Fixed Window Rate Limiter implementation before exposing it through a REST API.

---

## Completed

### Verification

- Created RateLimiterVerifier.
- Simulated multiple requests.
- Verified request counting.
- Verified request rejection.
- Verified TTL expiration.
- Verified Redis key reset.
- Verified end-to-end algorithm.

---

## Engineering Decisions

- Verification should happen before REST API development.
- Used CommandLineRunner as a temporary verification tool.
- Verification logic should remain separate from production code.

---

## Lessons Learned

- CommandLineRunner executes after Spring Boot finishes initialization.
- Spring executes every CommandLineRunner bean automatically.
- Verification code should never become production code.
- Good console output improves debugging.
- Always verify business logic before integrating additional layers.

---

## Issues Encountered

- Minor improvements in console output formatting.
- Initial verification code printed request numbers incorrectly.
- TTL sleep duration corrected during verification.

---

## Verification Results

✅ Requests 1–5 allowed

✅ Requests 6–7 rejected

✅ TTL expired successfully

✅ Counter reset correctly

---

# Session 4

Date

2026-08-08

Duration

Sprint 4 – REST API

---

## Objective

Expose the verified Fixed Window algorithm through a REST API.

---

## Completed

### API Design

- Designed REST endpoint.
- Selected POST method.
- Introduced API versioning.
- Designed request and response DTOs.

---

### Implementation

Completed:

- RateLimitRequest
- RateLimitResponse
- RateLimiterController
- HTTP status handling using ResponseEntity

---

### Testing

- Verified endpoint using Postman.
- Verified HTTP 200 responses.
- Verified HTTP 429 responses.
- Confirmed complete request lifecycle.

---

### Cleanup

- Removed RateLimiterVerifier.
- Removed RedisConnectionVerifier.

---

## Engineering Decisions

- Controller owns HTTP concerns only.
- Service remains independent of HTTP.
- Introduced ResponseEntity after HTTP status requirements emerged.
- DTOs introduced to isolate API contracts from business logic.

---

## Lessons Learned

- REST Controllers should orchestrate, not calculate.
- ResponseEntity provides complete control over HTTP responses.
- DTOs protect API contracts.
- HTTP status codes should communicate API outcomes.
- Controllers should never contain business logic.

---

## Issues Encountered

- ResponseEntity return type mismatch.
- Resolved by updating controller method signature.

---

## Verification Results

✅ REST endpoint working

✅ HTTP 200 returned for allowed requests

✅ HTTP 429 returned after rate limit exceeded

✅ End-to-end request flow verified

---

# Session 5

Date

2026-08-12

Duration

Sprint 5 – Validation & Configuration

---

## Objective

Improve API robustness by introducing request validation, centralized exception handling, and externalized rate limiter configuration.

---

## Completed

### Request Validation

- Added Spring Bean Validation dependency.
- Added `@Valid` to the controller request body.
- Added `@NotBlank` validation to `userId`.
- Added a meaningful validation message.
- Verified that validation happens before the controller executes.
- Verified invalid requests using Postman.

---

### Error Response

Created:

- ErrorResponse

The API returns a simple JSON response:

json
{
"message": "userId cannot be blank"
}

---

# Session 9

Date

2026-08-20

Duration

Sprint 9 – Swagger/OpenAPI

---

## Objective

Add interactive API documentation using OpenAPI and Swagger UI.

---

## Completed

### OpenAPI Integration

- Added Springdoc OpenAPI dependency.
- Configured Swagger UI.
- Verified Swagger UI is available.

---

### API Documentation

Created:

- OpenAPI configuration
- API title
- API version
- API description

Documented:

- `POST /api/v1/rate-limit`
- Request body
- `RateLimitRequest`
- `RateLimitResponse`
- `ErrorResponse`

---

### HTTP Responses

Documented:

- HTTP 200 – Request allowed
- HTTP 400 – Invalid request
- HTTP 429 – Rate limit exceeded
- HTTP 500 – Internal server error

---

### Swagger Verification

Verified:

- Swagger UI loads successfully.
- API endpoint appears in Swagger UI.
- Request schema appears correctly.
- Response schemas appear correctly.
- API can be executed directly from Swagger UI.
- Valid requests return HTTP 200.
- Rate-limited requests return HTTP 429.
- Invalid requests return HTTP 400.

---

## Engineering Decisions

- Springdoc OpenAPI selected for API documentation.
- Swagger configuration kept separate from business logic.
- OpenAPI annotations used only where additional documentation was required.
- Meaningful API behavior documented instead of implementation details.
- Swagger UI used as both API documentation and an interactive testing interface.

---

## Issues Encountered

- Initially attempted an incompatible Springdoc 3.x dependency with Spring Boot 3.5.5.
- Springdoc 2.8.13 was selected after identifying the Spring Boot version compatibility.
- Maven wrapper was used because Maven was not available as a global command.
- Build and test suite completed successfully after correcting the dependency.

---

## Verification Results

✅ Swagger UI available

✅ OpenAPI metadata displayed

✅ Request schema displayed

✅ Response schemas displayed

✅ HTTP 200 verified

✅ HTTP 400 verified

✅ HTTP 429 verified

---

## Next Session

Sprint 10

Objective

Containerize the Spring Boot application.

Tasks

- Create application Dockerfile
- Configure application container
- Update Docker Compose
- Connect Spring Boot container to Redis container
- Verify container-to-container communication
- Test API inside Docker environment

---

## Status

🟢 Sprint 9 Completed

---

## Git Information

Branch

develop

Commit

Pending

Reason

Sprint 9 Swagger/OpenAPI implementation and documentation are ready to be committed.


# Session 10

Date

2026-08-21

Duration

Sprint 10 – Containerization

---

## Objective

Containerize the Spring Boot application and run it together with Redis using Docker Compose.

---

## Completed

### Application Container

Created:

- Dockerfile

Configured:

- Java 21 runtime
- Spring Boot JAR
- Application working directory
- Container port 8080

---

### Docker Image

- Built Spring Boot production JAR.
- Created Docker image `rate-limiter:1.0`.
- Verified image build successfully.

---

### Docker Compose

Updated:

- `Docker/docker-compose.yml`

Added:

- Spring Boot application service
- Redis service
- Docker networking
- Environment-based Redis configuration
- Redis healthcheck
- Health-based application startup dependency

---

### Redis Configuration

Local configuration remains:

properties
spring.data.redis.host=localhost
spring.data.redis.port=6379


# Session 11

Date

2026-08-21

Duration

Sprint 11 – CI/CD

---

## Objective

Create an automated CI pipeline that verifies the application and Docker build on every push to the develop branch.

---

## Completed

### GitHub Actions

Created:

- `.github/workflows/ci.yml`

Configured:

- Push trigger for `develop`
- Pull request trigger for `develop`
- Ubuntu runner
- Java 21
- Maven dependency caching

---

### Automated Testing

Configured:

- Maven automated test execution
- Redis service container for integration tests

Verified:

- Unit tests run successfully
- Integration tests run successfully
- 12 tests passing in GitHub Actions

---

### Application Build

Configured:

- Maven package step
- Production JAR generation

---

### Docker Build

Configured:

- Docker image build inside CI
- `rate-limiter:ci` image

Verified:

- Docker image builds successfully in GitHub Actions

---

## Issues Encountered

### Maven Wrapper Permission

GitHub Linux runner initially returned:

text
Permission denied


# Session 12

Date

2026-08-22

Duration

Sprint 12 – Production Configuration & Deployment

---

## Objective

Prepare the rate limiter for deployment by externalizing configuration, creating a production-ready Docker image, and deploying the application with a remote Redis datastore.

---

## Completed

### Production Configuration

Externalized:

- Redis host
- Redis port
- Server port
- Maximum request limit
- Rate limit window

Implemented environment variable configuration with local defaults.

---

### Docker

Replaced the original Dockerfile with a multi-stage build.

Build stage:

- Maven
- Java 21
- Application compilation
- JAR generation

Runtime stage:

- Java 21 JRE
- Spring Boot JAR

Verified the Docker image builds without requiring the `target` directory to be committed to Git.

---

### Render Deployment

Created:

- Render Web Service
- Render Key Value datastore

Configured:

- Spring Boot application
- Remote Redis connection
- Environment variables
- Docker-based deployment

---

### Production Verification

Verified:

- Render deployment completed successfully.
- Spring Boot application started successfully.
- Application connected to remote Redis.
- Live REST API is accessible.
- Rate limiter works against remote Redis.
- HTTP 429 is returned after the configured limit is exceeded.

---

## Issues Encountered

### Root URL

Accessing `/` produced:

text
NoResourceFoundException


# Session 13

Date

2026-08-23

## Objective

Benchmark the Redis-backed rate limiter under concurrent load and obtain real performance measurements.

## Completed

- Installed Apache JMeter 5.6.3.
- Created a JMeter load-test plan.
- Verified rate-limit enforcement with repeated requests.
- Identified intentional HTTP 429 responses during same-user testing.
- Configured per-request UUID generation to isolate performance testing from rate-limit rejection.
- Executed 1,000-request benchmark.
- Executed 5,000-request benchmark.
- Executed 10,000-request benchmark.
- Tested up to 200 concurrent users.
- Achieved 0% errors during clean performance benchmarks.

## Benchmark Results

1,000 requests:

- 50 concurrent users
- ~201 req/s
- 3 ms average latency
- 0% errors

5,000 requests:

- 100 concurrent users
- ~499 req/s
- 7 ms average latency
- 0% errors

10,000 requests:

- 200 concurrent users
- ~1,734–2,101 req/s across repeated runs
- 61–76 ms average latency
- 0% errors

## Key Finding

The system sustained more than 1.7K requests/sec in the local Docker/JMeter environment under 200 concurrent users.

Repeated 10,000-request runs produced different throughput values, so the conservative observed range of approximately 1.7K–2.1K requests/sec is used instead of claiming a fixed maximum capacity.

## Status

🟢 Sprint 13 Completed


# Session 14

Date

2026-08-23

Duration

Sprint 14 – Observability & Production Readiness

---

## Objective

Improve the rate limiter's production readiness by adding observability, application metadata, custom metrics, environment-based configuration, Docker Compose improvements, performance verification, and project documentation.

---

## Completed

### Actuator

- [x] Added Spring Boot Actuator
- [x] Exposed `/actuator/health`
- [x] Exposed `/actuator/info`
- [x] Exposed `/actuator/prometheus`
- [x] Verified Actuator endpoints
- [x] Verified Redis health through Actuator
- [x] Changed health detail exposure from `always` to `never`

---

### Application Information

- [x] Added application name
- [x] Added application description
- [x] Added application version
- [x] Enabled environment-based Actuator information
- [x] Verified `/actuator/info`

Current application metadata:

```text
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

Added custom counters:

rate_limiter_requests_total{result="allowed"}
rate_limiter_requests_total{result="rejected"}

Completed:

 Added allowed request counter
 Added rejected request counter
 Added result label
 Incremented allowed counter for successful requests
 Incremented rejected counter for rate-limited requests
 Verified metrics through /actuator/prometheus
 Avoided user IDs as metric labels to prevent high-cardinality metrics
Rate Limiter Verification After Metrics
 Verified requests within limit return HTTP 200
 Verified requests beyond limit return HTTP 429
 Verified Redis counter continues to work
 Verified rate-limit enforcement remains correct
Test Updates

Adding MeterRegistry to RateLimiterService changed its constructor.

 Updated RateLimiterServiceTest
 Added SimpleMeterRegistry to the test
 Restored test compilation
 Verified Maven tests pass
Environment-Based Configuration

Supported variables:

REDIS_HOST
REDIS_PORT
SERVER_PORT
RATE_LIMIT_MAX_REQUESTS
RATE_LIMIT_WINDOW

Current defaults:

REDIS_HOST=localhost
REDIS_PORT=6379
SERVER_PORT=8080
RATE_LIMIT_MAX_REQUESTS=5
RATE_LIMIT_WINDOW=1m

Completed:

 Redis host externalized
 Redis port externalized
 Server port externalized
 Maximum request limit externalized
 Rate-limit window externalized
 Verified local defaults
 Verified Docker environment variables
 Verified application behavior after configuration changes
Docker Compose Improvement

The original Docker Compose configuration referenced a pre-built image.

Changed the application service to build the image directly from the repository Dockerfile.

Completed:

 Removed dependency on manually pre-built application image
 Configured Docker Compose build
 Verified application image builds automatically
 Verified Redis starts successfully
 Verified Redis healthcheck
 Verified application waits for Redis health
 Verified application starts successfully
 Verified API after rebuild
 Verified Actuator after rebuild

This makes the Docker setup more reproducible for a fresh repository clone.

Docker Verification

Verified:

rate-limiter-app     Up
rate-limiter-redis   Up (healthy)

Verified after the Docker Compose changes:

 /actuator/health
 /actuator/info
 Rate-limit API
 Redis connectivity
 HTTP 429 enforcement
JMeter Verification

Apache JMeter 5.6.3 was used for load testing.

Completed:

 Created JMeter load-test plan
 Configured concurrent users
 Configured request execution
 Configured unique request identities for clean throughput testing
 Verified Summary Report
 Verified View Results Tree
 Investigated JMeter result-file overwrite prompts
 Completed successful load tests
CI/CD Verification

Existing GitHub Actions workflow was inspected and verified.

Current workflow:

Checkout Repository
        |
        v
Setup Java 21
        |
        v
Start Redis Service
        |
        v
Run Maven Tests
        |
        v
Build Spring Boot Application
        |
        v
Build Docker Image

Completed:

 Inspected .github/workflows/ci.yml
 Verified Java 21 setup
 Verified Redis service
 Verified Maven test execution
 Verified application build
 Verified Docker image build
 Verified GitHub Actions CI is GREEN
README

Created a comprehensive root-level README.md.

Documented:

 Project overview
 Features
 Architecture
 Fixed Window algorithm
 API
 Configuration
 Local setup
 Docker setup
 Monitoring
 Prometheus metrics
 Performance benchmarks
 Testing
 CI/CD
 Deployment
 Tech stack
 Project structure
 Future improvements

README was committed and pushed to the develop branch.

Engineering Decisions
Observability

Micrometer counters were used for business-level rate-limit metrics.

User IDs were deliberately not used as metric labels because that could create high-cardinality metrics.

Configuration

Environment variables are used for deployment-specific configuration while retaining local defaults for development.

This allows the same application to run locally, inside Docker, and on Render without changing application source code.

Docker Compose

Docker Compose now builds the application image directly from the repository Dockerfile.

This improves reproducibility because a developer does not need to manually build an application image before starting the stack.

Performance Reporting

Only observed benchmark results are documented.

The project does not claim a fixed production throughput based on local benchmarks.

Algorithm Scope

The implemented algorithm remains:

Fixed Window Counter

Token Bucket and Leaky Bucket are not implemented.

They remain future possibilities rather than current features.

Distributed Architecture

The rate-limit state is stored in Redis rather than local application memory.

Multiple application instances can therefore share the same rate-limit state when connected to the same Redis instance.

Explicit multi-instance verification was performed in Sprint 15.

Problems Encountered
JMeter Result File Overwrite

JMeter repeatedly displayed a prompt asking whether an existing result file should be overwritten.

Investigated the result collector configuration and successfully completed the load tests.

Metrics Constructor Change

Adding MeterRegistry to RateLimiterService caused the existing unit test constructor call to fail.

The test was updated to provide a SimpleMeterRegistry.

Maven tests then passed successfully.

/actuator/info Initially Returned Empty JSON

Initially:

{}

was returned from:

/actuator/info

Added:

management.info.env.enabled=true

After rebuilding the Docker image, /actuator/info returned the expected application metadata.

Docker Compose Image Dependency

The original Docker Compose setup required a manually built application image.

Changed the Compose configuration to use Docker's build functionality.

Verified that:

docker compose ... up -d --build

builds and starts the complete stack successfully.

Session 15

Date

2026-08-24

Duration

Sprint 15 – Distributed Consistency Verification

Objective

Demonstrate that multiple Spring Boot application instances connected to the same Redis instance maintain consistent rate-limit state.

Completed
Distributed Docker Environment

Created a separate test configuration:

Docker/docker-compose-distributed.yml

The distributed test environment contains:

                    Redis
              Shared Rate State
                  redis:6379
                 /          \
                /            \
               v              v
        Rate Limiter #1   Rate Limiter #2
          localhost:8080   localhost:8081

Configured:

 Redis container
 Rate Limiter application instance #1
 Rate Limiter application instance #2
 Shared Redis network
 Shared Redis datastore
 Application #1 exposed on host port 8080
 Application #2 exposed on host port 8081
 Both application instances configured to use REDIS_HOST=redis
 Both application instances configured to use REDIS_PORT=6379
 Redis healthcheck
 Health-based application startup dependency
Distributed Consistency Test 1

Test:

App #1 → 5 requests
App #2 → 6th request

Results:

App #1 Request 1 → allowed
App #1 Request 2 → allowed
App #1 Request 3 → allowed
App #1 Request 4 → allowed
App #1 Request 5 → allowed
App #2 Request 6 → HTTP 429

Result:

🟢 Passed

This proves that App #2 observed the rate-limit counter created by App #1.

If each application had maintained an independent in-memory counter, App #2 would have allowed its first request.

Distributed Consistency Test 2

Test:

App #2 → 5 requests
App #1 → 6th request

Results:

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

The Redis key format was verified from:

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

Redis contains the rate-limit state.
The request counter is stored in Redis.
Redis TTL is applied to rate-limit keys.
Redis automatically removes expired rate-limit keys.

The earlier empty KEYS result occurred because the 1-minute rate-limit window had already expired before Redis was inspected.

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

Lessons Learned
Multiple application instances do not need local rate-limit state.
Redis provides the shared state required for consistent rate limiting across instances.
Port mappings can differ while both application containers continue listening on port 8080 internally.
A separate distributed test Compose file allows distributed behavior to be verified without changing the normal single-instance deployment.
Redis TTL behavior can only be observed while the rate-limit key is still alive.
Direct Redis inspection is useful for validating application-level assumptions.
Distributed architecture should be explicitly tested rather than claimed based only on design.
Engineering Decisions
Separate Distributed Test Compose File

Created:

Docker/docker-compose-distributed.yml

instead of modifying the normal Docker Compose file.

Reason:

Preserve the normal single-instance development/deployment setup.
Avoid introducing unnecessary complexity into the primary Compose configuration.
Provide a reproducible environment specifically for distributed consistency testing.
Shared Redis

Both application instances use:

REDIS_HOST=redis
REDIS_PORT=6379

This ensures both instances operate on the same Redis datastore.

Host Port Mapping

Application #1:

localhost:8080 → container:8080

Application #2:

localhost:8081 → container:8080

Both containers continue using port 8080 internally.

Only the host ports differ.

Verification Results
 Multiple application containers started successfully
 Redis container started successfully
 Redis reported healthy
 Application #1 started successfully
 Application #2 started successfully
 App #1 → App #2 shared counter verified
 App #2 → App #1 shared counter verified
 HTTP 429 enforcement verified across instances
 Redis rate-limit key verified
 Redis counter value verified
 Redis TTL verified
 Redis expiration behavior verified
 Distributed rate-limit consistency verified
Status

🟢 Sprint 15 Completed

Current Project Position

The project currently has:

Core Rate Limiter              ✅
Redis Integration              ✅
REST API                       ✅
Validation                     ✅
Error Handling                 ✅
Logging                        ✅
Unit Testing                   ✅
Integration Testing            ✅
Swagger/OpenAPI                ✅
Docker                         ✅
Docker Compose                 ✅
CI/CD                          ✅
Render Deployment              ✅
JMeter Benchmarking            ✅
Actuator                       ✅
Micrometer/Prometheus          ✅
Custom Metrics                 ✅
Environment Configuration      ✅
README                         ✅
Distributed Verification       ✅
Shared Redis State             ✅
Next Session
Sprint 16 – Production Hardening & Final Review

Status

🟡 Planned

Objective

Perform a final engineering review of the rate limiter before considering the project feature-complete.

Planned Tasks
API Review
 Review API response design
 Review rate-limit response semantics
 Consider remaining-request information
 Consider reset-time information
 Consider standard rate-limit response headers
Production Hardening
 Review graceful shutdown behavior
 Review configuration validation
 Review Redis failure behavior
 Review logging and error handling
 Review Actuator exposure
 Review Docker configuration
Final Verification
 Run final automated test suite
 Run final Docker verification
 Run final JMeter benchmark
 Review Render deployment
 Review README
 Finalize architecture documentation
 Verify Git working tree is clean
Important Constraint

Do not add features simply to increase project size.

The goal is to make the existing rate limiter robust, explainable, testable, and production-oriented.

Avoid introducing Kubernetes, Kafka, or unrelated infrastructure unless a concrete engineering requirement emerges.

Final Project Goal

The target final architecture is:

                         Client
                           |
                           v
                  Spring Boot API
                           |
                           v
                  Rate Limit Service
                           |
                           v
                    Redis State
                           |
              +------------+------------+
              |                         |
              v                         v
        Application #1            Application #2
              |                         |
              +------------+------------+
                           |
                    Shared Redis

The core requirement is:

Multiple application instances
            +
       Shared Redis
            ↓
Consistent distributed rate limiting

Status:

🟢 Core distributed behavior verified.