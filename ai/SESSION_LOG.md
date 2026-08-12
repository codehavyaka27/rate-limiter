# Session Log

---

# Purpose

This document records every development session for the project.

Each session captures:

* Work completed
* Decisions made
* Problems encountered
* Lessons learned
* Next objectives

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

* Created Spring Boot project
* Removed unnecessary boilerplate
* Created clean package structure
* Created documentation folder
* Created AI folder
* Created Docker folder

---

### Documentation

Completed:

* Problem Statement
* High-Level Design
* Low-Level Design
* API Contract
* Redis Data Model
* Algorithms
* Development Roadmap
* Test Plan

---

### AI Engineering Files

Completed

* PROJECT_CONTEXT.md
* PROJECT_STATE.md
* DECISIONS.md
* SESSION_LOG.md
* CODING_GUIDELINES.md
* DEVELOPMENT_RULES.md
* PROMPTS.md

---

## Decisions

* Redis selected as primary datastore.
* Fixed Window selected for Version 1.
* Layered Architecture adopted.
* Strategy Pattern planned for algorithms.

---

## Lessons Learned

* Design should come before implementation.
* Documentation reduces future confusion.
* A clear architecture simplifies coding.
* Small, well-defined sprints are easier to manage.

---

## Issues Encountered

None.

---

## Next Session

Sprint 2

Objective

Redis Integration

Tasks

* Verify Docker installation
* Create docker-compose.yml
* Start Redis container
* Configure Spring Boot Redis
* Verify Redis connectivity

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

* Verified Docker Desktop installation.
* Learned Docker fundamentals.
* Created docker-compose.yml.
* Started Redis container successfully.
* Verified Redis is running on port 6379.

---

### Spring Boot Configuration

* Added Redis configuration in application.properties.
* Configured application name.
* Configured server port.
* Learned Spring Boot externalized configuration.

---

### Spring Boot Concepts

Learned and understood:

* Spring Container
* Dependency Injection (DI)
* Inversion of Control (IoC)
* Auto Configuration
* RedisTemplate
* CommandLineRunner
* Infrastructure verification approach

---

### Redis Verification

Created:

* RedisConnectionVerifier

Verified:

* RedisTemplate injection
* Redis SET operation
* Redis GET operation
* Successful communication between Spring Boot and Redis

Console Output

Redis Value : RateLimiter

Infrastructure verified successfully.

---

## Decisions

* Use Spring Boot Auto Configuration instead of creating a custom RedisConfig class.
* Postpone custom Logback configuration until the logging sprint.
* Keep infrastructure verification separate from business logic.
* Repository layer will be the only layer interacting directly with RedisTemplate.

---

## Lessons Learned

* Docker containers expose services through mapped ports.
* Spring Boot automatically creates RedisTemplate.
* Dependency Injection provides infrastructure objects instead of manually creating them.
* application.properties externalizes environment-specific configuration.
* Infrastructure should always be validated before implementing business logic.
* Empty configuration files such as logback-spring.xml can prevent application startup.

---

## Issues Encountered

* IntelliJ project import confusion.
* Incorrect Java package declarations.
* Maven dependency configuration issues.
* Empty logback-spring.xml caused application startup failure.
* Spring Boot test configuration issue resolved during setup.

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

* Verified Redis integration.
* Verified Spring Boot ↔ Redis connectivity.

---

### Implementation

Completed:

* RedisKeyBuilder
* RedisRepository
* RateLimiterService

---

### Engineering Decisions

* Introduced RedisKeyBuilder as a utility class.
* Repository owns infrastructure logic.
* Service owns business logic.
* Atomic Redis INCR selected instead of GET + SET.
* TTL applied only for the first request in a window.
* Constructor Injection adopted.
* Redis keys centralized.
* Followed KISS and YAGNI principles.

---

## Lessons Learned

* Atomic operations eliminate race conditions.
* Business logic and infrastructure logic should remain separate.
* Small APIs are easier to maintain.
* Key naming is an architectural decision.
* Design discussions reduce implementation complexity.

---

## Issues Encountered

* Incorrect package structure (folders instead of packages).
* Maven dependency issues caused by Spring Boot 4 starter selection.
* IntelliJ project structure issues.
* Resolved all issues successfully.

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

* Created RateLimiterVerifier.
* Simulated multiple requests.
* Verified request counting.
* Verified request rejection.
* Verified TTL expiration.
* Verified Redis key reset.
* Verified end-to-end algorithm.

---

## Engineering Decisions

* Verification should happen before REST API development.
* Used CommandLineRunner as a temporary verification tool.
* Verification logic should remain separate from production code.

---

## Lessons Learned

* CommandLineRunner executes after Spring Boot finishes initialization.
* Spring executes every CommandLineRunner bean automatically.
* Verification code should never become production code.
* Good console output improves debugging.
* Always verify business logic before integrating additional layers.

---

## Issues Encountered

* Minor improvements in console output formatting.
* Initial verification code printed request numbers incorrectly.
* TTL sleep duration corrected during verification.

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

* Designed REST endpoint.
* Selected POST method.
* Introduced API versioning.
* Designed request and response DTOs.

---

### Implementation

Completed:

* RateLimitRequest
* RateLimitResponse
* RateLimiterController
* HTTP status handling using ResponseEntity

---

### Testing

* Verified endpoint using Postman.
* Verified HTTP 200 responses.
* Verified HTTP 429 responses.
* Confirmed complete request lifecycle.

---

### Cleanup

* Removed RateLimiterVerifier.
* Removed RedisConnectionVerifier.

---

## Engineering Decisions

* Controller owns HTTP concerns only.
* Service remains independent of HTTP.
* Introduced ResponseEntity after HTTP status requirements emerged.
* DTOs introduced to isolate API contracts from business logic.

---

## Lessons Learned

* REST Controllers should orchestrate, not calculate.
* ResponseEntity provides complete control over HTTP responses.
* DTOs protect API contracts.
* HTTP status codes should communicate API outcomes.
* Controllers should never contain business logic.

---

## Issues Encountered

* ResponseEntity return type mismatch.
* Resolved by updating controller method signature.

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

* Added Spring Bean Validation dependency.
* Added `@Valid` to the controller request body.
* Added `@NotBlank` validation to `userId`.
* Added a meaningful validation message.
* Verified that validation happens before the controller executes.
* Verified invalid requests using Postman.

---

### Error Response

Created:

* ErrorResponse

The API returns a simple JSON response:

```json
{
    "message": "userId cannot be blank"
}
```

---

### Global Exception Handling

Created:

* GlobalExceptionHandler

Implemented:

* `@RestControllerAdvice`
* `@ExceptionHandler(MethodArgumentNotValidException.class)`
* HTTP 400 response for validation failures.
* Centralized validation exception handling.

---

### Externalized Configuration

Moved rate limiter configuration from Java constants into `application.properties`.

Configuration:

```properties
rate-limiter.max-requests=5
rate-limiter.window=1m
```

Implemented configuration injection using Spring's `@Value`.

Used constructor injection so configuration values remain immutable after bean creation.

---

## Engineering Decisions

* Validation should happen before controller execution.
* Validation failures should be handled centrally instead of using try/catch in every controller.
* `GlobalExceptionHandler` belongs in the `exception` package.
* Error responses should use DTOs.
* Version 1 error responses should remain simple.
* Configuration values should not be hardcoded in business logic.
* `@Value` is sufficient for the current small number of configuration properties.
* Constructor injection is preferred for configuration consistency with the rest of the project.
* Configuration changes should not require Java code changes or recompilation.

---

## Lessons Learned

* `@Valid` triggers Bean Validation before controller execution.
* `@NotBlank` validates that a string is not null, empty, or only whitespace.
* Spring throws `MethodArgumentNotValidException` when request validation fails.
* `@RestControllerAdvice` allows exception handling across controllers.
* `@ExceptionHandler` maps an exception type to a handler method.
* DTOs provide consistent API response structures.
* Jackson and DTO construction are different concerns depending on whether data is being serialized or deserialized.
* Configuration is a runtime dependency and can be externalized from application code.
* Spring can convert configuration values such as `1m` directly into `Duration`.

---

## Issues Encountered

* Initially considered an unnecessary no-argument constructor for ErrorResponse.
* Learned that ErrorResponse does not require a no-argument constructor because the application creates it directly and sends it to the client.
* Incorrect `@Value` import was initially used from Lettuce instead of Spring.
* Duplicate constructor and duplicate repository field were accidentally introduced while modifying RateLimiterService.
* Resolved all issues successfully.

---

## Verification Results

✅ Valid request continues through controller

✅ Empty `userId` rejected before controller execution

✅ HTTP 400 returned for validation failure

✅ Correct validation JSON response returned

✅ Rate limiter behavior remained functional

✅ Configuration successfully moved to application.properties

---

# Session 6

Date

2026-08-12

Duration

Sprint 6 – Logging & Unexpected Error Handling

---

## Objective

Introduce production-oriented logging and centralized handling of unexpected server-side exceptions.

---

## Completed

### Logging

Introduced SLF4J logging.

Added class-specific loggers to:

* RateLimiterService
* GlobalExceptionHandler

---

### Rate Limiter Logging

Added WARN logging when the rate limit is exceeded.

Example:

```text
WARN ... RateLimiterService : Rate limit exceeded for user: user1
```

Normal successful requests are not logged to avoid unnecessary log noise.

---

### Validation Logging

Added WARN logging to GlobalExceptionHandler for validation failures.

Example:

```text
WARN ... GlobalExceptionHandler : Validation failed: userId cannot be blank
```

---

### Unexpected Exception Handling

Added a catch-all exception handler using:

```java
@ExceptionHandler(Exception.class)
```

Unexpected exceptions now:

* Are logged at ERROR level.
* Include the exception object for stack trace information.
* Return HTTP 500.
* Return a generic message to the client.

Client response:

```json
{
    "message": "Internal server error"
}
```

Internal exception details remain on the server side for debugging.

---

### Verification

Created a temporary test endpoint to deliberately throw a RuntimeException.

Verified:

* HTTP 500 response.
* Generic client-facing error message.
* Server-side ERROR logging.
* Exception stack trace logging.

Removed the temporary test endpoint after verification.

---

## Engineering Decisions

* Avoid unnecessary `System.out.println()` statements in production code.
* Use SLF4J for application logging.
* Use class-specific loggers so log messages can be associated with their source class.
* Use WARN for rate limit violations because they are expected application events but may require attention.
* Avoid logging every successful request.
* Use ERROR for unexpected server-side exceptions.
* Never expose internal infrastructure or exception details to API clients.
* Log the actual exception server-side for debugging.
* Keep specific exception handlers alongside a general fallback handler.
* Do not modify RedisRepository merely to translate infrastructure exceptions into HTTP responses; HTTP concerns remain outside the repository.

---

## Lessons Learned

* Logging is different from `System.out.println()`.
* Logging frameworks provide levels such as DEBUG, INFO, WARN, and ERROR.
* Logs can include class information and timestamps automatically.
* `{}` placeholders are preferred for SLF4J parameterized logging.
* Logging every normal request can create unnecessary noise.
* Expected client-side errors and unexpected server-side failures should be treated differently.
* A global `Exception.class` handler acts as a fallback for unexpected exceptions.
* Clients should receive safe generic messages while server logs retain debugging information.
* Exception objects should be passed to `log.error()` when stack trace information is needed.

---

## Issues Encountered

* Initially mistook Spring's `ExceptionHandlerExceptionResolver` log output for the application's own validation log.
* Verified that the custom `GlobalExceptionHandler` logger was correctly configured and functioning.
* No functional issues remained after verification.

---

## Verification Results

✅ Rate limit WARN logging verified

✅ Validation WARN logging verified

✅ Unexpected exception handling verified

✅ HTTP 500 returned correctly

✅ Generic error response returned to client

✅ Server-side exception details retained in logs

---

# Current Architecture After Session 6

```text
Client
   ↓
Bean Validation
   ↓
RateLimiterController
   ↓
RateLimiterService
   ↓
RedisRepository
   ↓
Redis
```

Error handling:

```text
Validation Failure
   ↓
GlobalExceptionHandler
   ↓
HTTP 400

Unexpected Exception
   ↓
GlobalExceptionHandler
   ↓
HTTP 500
```

Rate limit exceeded:

```text
RateLimiterService
   ↓
HTTP 429
   ↓
WARN log
```

---

# Current Project Status

## Completed

* Project setup
* Documentation
* Docker infrastructure
* Redis integration
* Fixed Window rate limiter
* REST API
* Request/Response DTOs
* Request validation
* Global exception handling
* Externalized configuration
* Logging
* Manual API verification

---

## Next Session

Sprint 7

Objective

Automated Testing

Tasks

* Introduce JUnit 5
* Introduce Mockito
* Unit test RateLimiterService
* Mock RedisRepository
* Test allowed requests
* Test rejected requests
* Test rate limit boundary
* Test TTL behavior
* Test validation handling
* Test exception handling

---

## Status

🟢 Sprint 6 Completed

---

## Git Information

Branch

develop

Commit

Pending

Reason

Documentation and Sprint 5/6 changes are ready to be reviewed and committed.
