# Low-Level Design (LLD)

---

# Project

Distributed Rate Limiting Microservice

---

# 1. Purpose

This document defines the internal software structure of the Rate Limiting Microservice.

It describes how the application is organized into packages, how responsibilities are divided, and how requests travel through the system.

---

# 2. Package Structure

```
src/main/java/com/sumanth/ratelimiter

├── algorithm
├── config
├── constants
├── controller
├── dto
├── exception
├── model
├── repository
├── service
├── util
└── RateLimiterApplication
```

Each package has a single responsibility.

---

# 3. Package Responsibilities

## controller

Responsible for:

- Receiving HTTP requests
- Validating input
- Calling the service layer
- Returning HTTP responses

Business logic must never exist here.

---

## service

Responsible for:

- Rate limiting logic
- Request validation
- Calling Redis repository
- Building responses

This contains the business logic.

---

## repository

Responsible for communicating with Redis.

It should never contain business decisions.

Responsibilities:

- Read counter
- Increment counter
- Set expiration
- Read remaining TTL

---

## dto

Contains request and response objects exchanged with clients.

Example:

- RateLimitRequest
- RateLimitResponse

---

## algorithm

Contains all rate limiting algorithms.

Version 1:

- FixedWindowAlgorithm

Future:

- SlidingWindowAlgorithm
- TokenBucketAlgorithm
- LeakyBucketAlgorithm

---

## config

Contains Spring configuration.

Examples:

- RedisConfig
- ApplicationConfig

---

## util

Contains helper classes.

Example:

RedisKeyGenerator

---

## constants

Stores application constants.

Examples:

- Redis key prefixes
- Default limits
- Window duration

---

## exception

Contains custom exceptions and global exception handling.

---

## model

Reserved for future domain models.

---

# 4. Request Lifecycle

```
Client

↓

Controller

↓

Service

↓

Repository

↓

Redis

↓

Repository

↓

Service

↓

Controller

↓

Client
```

Every request follows this flow.

---

# 5. DTO Design

## RateLimitRequest

Fields:

- userId

Purpose:

Represents an incoming rate-limit request.

---

## RateLimitResponse

Fields:

- allowed
- remainingRequests
- retryAfterSeconds

Purpose:

Represents the response returned to clients.

---

# 6. Repository Design

Repository Responsibilities:

- Generate Redis operations.
- Hide Redis implementation.
- Return simple data to services.

The service layer should never know Redis commands.

---

# 7. Redis Key Strategy

Key format:

```
rate:user:{userId}
```

Example:

```
rate:user:101
```

Value:

Current request count.

TTL:

60 seconds.

---

# 8. Design Patterns

## Layered Architecture

Controller

↓

Service

↓

Repository

---

## Strategy Pattern

RateLimitingAlgorithm

↓

FixedWindowAlgorithm

↓

SlidingWindowAlgorithm

↓

TokenBucketAlgorithm

The service depends on the interface instead of a specific implementation.

This follows the Open/Closed Principle.

---

# 9. Error Handling

400

Bad Request

Missing userId.

---

429

Too Many Requests

Request limit exceeded.

---

503

Service Unavailable

Redis unavailable.

---

500

Internal Server Error

Unexpected exception.

---

# 10. Logging Strategy

INFO

Application startup

Redis connection

Allowed requests

---

WARN

Rate limit exceeded

---

ERROR

Redis failures

Unexpected exceptions

---

# 11. Testing Strategy

Unit Tests

- Fixed Window Algorithm
- Service Layer

Integration Tests

- Redis Repository

Future

Load Tests

Using k6.

---

# 12. Extension Points

Future improvements:

- Sliding Window Counter
- Sliding Window Log
- Token Bucket
- API Gateway
- Dynamic configuration
- Premium plans
- Prometheus metrics
- Grafana dashboards

---

# Summary

The Low-Level Design organizes the application into clear modules with single responsibilities. Every layer has a well-defined purpose, making the application easier to maintain, test, and extend.