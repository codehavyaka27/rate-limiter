# Architecture History

---

# Purpose

This document records how the system architecture evolves over time.

Instead of only seeing the final design, it captures every architectural milestone.

---

# Sprint 1

Architecture

Client

↓

Controller

↓

Service

↓

Repository

↓

Redis

Reason

Established layered architecture.

---

# Sprint 2

Changes

- Added Docker
- Added Redis Container
- Added Redis Configuration

Reason

Prepared infrastructure for Redis integration.

---

# Sprint 3

Changes

- Added RedisKeyBuilder
- Added RedisRepository
- Added RateLimiterService

Reason

Separated infrastructure concerns from business logic.

---
# Sprint 3

Architecture

Controller

↓

RateLimiterService

↓

RedisRepository

↓

RedisTemplate

↓

Redis

RedisKeyBuilder

(Utility)

Reason

Separated business logic from infrastructure while centralizing Redis key generation.