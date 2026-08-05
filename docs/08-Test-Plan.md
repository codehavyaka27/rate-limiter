# Test Plan

---

# Purpose

This document defines the testing strategy for the Distributed Rate Limiting Microservice.

The objective is to verify correctness, reliability, performance, and maintainability throughout development.

---

# Testing Strategy

The project follows the Testing Pyramid.


Most tests should be unit tests.

---

# 1. Unit Testing

Purpose

Verify individual classes without external dependencies.

Framework

- JUnit 5
- Mockito

Components

- FixedWindowAlgorithm
- RateLimiterService
- RedisKeyGenerator
- Request validation
- Utility classes

Examples

✔ Counter below limit → Allow request

✔ Counter equals limit → Reject request

✔ Invalid request → Validation failure

---

# 2. Integration Testing

Purpose

Verify interaction between Spring Boot and Redis.

Components

- Redis Repository
- Spring Configuration
- Redis Connection

Examples

✔ Counter stored correctly

✔ TTL assigned correctly

✔ Counter expires automatically

✔ Redis key generated correctly

---

# 3. API Testing

Purpose

Verify REST endpoints.

Endpoint


Test Cases

✔ Valid request

✔ Missing userId

✔ Rate limit exceeded

✔ Invalid JSON

✔ Redis unavailable

---

# 4. Performance Testing

Tool

k6

Metrics

- Average response time
- Requests per second
- Failure rate
- Throughput

Example


Expected Result

Average latency remains below project target under normal load.

---

# 5. Edge Cases

Examples

- First request
- Last allowed request
- First blocked request
- TTL expiration
- Empty userId
- Null request body
- Very long userId
- Concurrent requests from the same user

---

# 6. Error Handling Tests

Verify

400

Bad Request

---

429

Too Many Requests

---

503

Redis unavailable

---

500

Unexpected exception

---

# 7. Regression Testing

Whenever a new algorithm is added:

- Existing algorithms must continue working.
- Existing API contracts must remain unchanged.
- Existing unit tests must continue passing.

---

# Test Coverage Goal

Target

- Unit Test Coverage ≥ 80%
- Critical Business Logic = 100% tested

---

# Continuous Validation

Every completed sprint should satisfy:

- Project builds successfully
- Tests pass
- Documentation updated
- No known critical defects

---

# Summary

Testing is treated as a core part of development rather than an afterthought. Every new feature should include corresponding automated tests to maintain confidence in the system as it evolves.