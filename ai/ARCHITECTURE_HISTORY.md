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

# Sprint 4 – Phase 1

Changes

- Added RateLimiterVerifier
- Introduced startup verification workflow
- Verified complete Fixed Window algorithm

Reason

Validate business logic independently before exposing external APIs.

# Sprint 4 – REST API

Changes

- Added RateLimiterController
- Added Request DTO
- Added Response DTO
- Introduced ResponseEntity
- Added HTTP status handling
- Removed temporary verification runners

Result

The application now exposes the Fixed Window algorithm through a REST API while preserving clean separation between presentation, business, and infrastructure layers.