# Session 7

Date

2026-08-20

Duration

Sprint 7 – Automated Testing

---

## Objective

Introduce automated testing for the rate limiter and verify service and controller behavior without relying entirely on manual Postman testing.

---

## Completed

### Service Unit Testing

Created:

- RateLimiterServiceTest

Implemented:

- JUnit 5
- Mockito
- Mock RedisRepository
- `@Mock`
- `@BeforeEach`
- Mockito `when().thenReturn()`
- Mockito `verify()`
- Mockito `never()`

Tested:

- First request is allowed
- TTL is set for the first request
- Request at the limit is allowed
- Request beyond the limit is rejected
- Subsequent requests do not reset TTL

---

### Controller Testing

Created:

- RateLimiterControllerTest

Implemented:

- MockMvc
- Mock RateLimiterService
- Standalone controller testing
- Bean Validation support
- GlobalExceptionHandler integration

Tested:

- HTTP 200 for allowed requests
- `allowed: true` response
- HTTP 429 for rejected requests
- `allowed: false` response
- HTTP 400 for invalid `userId`
- Validation error response

---

## Engineering Decisions

- Service tests should isolate business logic from Redis infrastructure.
- RedisRepository is mocked during service unit tests.
- Controller tests should isolate HTTP behavior from service business logic.
- MockMvc is used to test controller endpoints without starting the full application.
- Existing business logic should not be retested unnecessarily at the controller layer.
- Focus on meaningful behavior instead of testing every implementation detail.

---

## Verification Results

### Service Tests

✅ First request allowed

✅ First request TTL verified

✅ Request at limit allowed

✅ Request beyond limit rejected

✅ Subsequent request does not reset TTL

### Controller Tests

✅ HTTP 200 verified

✅ HTTP 429 verified

✅ HTTP 400 verified

✅ JSON response bodies verified

---

## Test Suite

Total automated tests

8

Passed

8

Failed

0

Status

🟢 All tests passing

---

# Current Architecture After Session 7

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