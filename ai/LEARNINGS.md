# Engineering Learnings

---

# Purpose

This document records what I learn during each sprint.

It is a personal engineering journal, not project documentation.

The goal is to track how my engineering thinking evolves over time.

---

# Sprint 1

## Learned

- Good software starts with design.
- Documentation reduces future confusion.
- Architecture should be planned before coding.
- Small sprints improve focus.

---

# Sprint 2

## Learned

- Docker runs applications inside containers.
- Redis is an in-memory database optimized for speed.
- Spring Boot connects to Redis using RedisTemplate.
- Configuration belongs in application.properties.
- Redis integration should be completed before business logic.

---

# Sprint 3

## Learned

- Repository owns infrastructure logic.
- Service owns business logic.
- Utility classes should not be injected.
- Atomic operations prevent race conditions.
- TTL automatically removes expired keys.
- Business validation and infrastructure validation are different.
- Redis key naming is an architectural decision.
- KISS and YAGNI improve software quality.

---

# Sprint 4 – Phase 1

## Learned

- Verification should precede integration.
- CommandLineRunner is useful for temporary startup verification.
- Spring automatically executes all CommandLineRunner beans.
- Thread.sleep() is acceptable in temporary verification utilities but should be avoided in production request handling.
- Good console output makes debugging significantly easier.
- Designing expected behavior before running the application simplifies debugging.
- Verified implementations inspire more confidence than assumed correctness.
# Sprint 4 – REST API

## Learned

- Controllers expose HTTP endpoints.
- Controllers should delegate all business logic to the Service layer.
- DTOs define the API contract.
- @RequestBody converts JSON into Java objects.
- @RestController automatically serializes Java objects into JSON.
- ResponseEntity enables control over HTTP status codes.
- HTTP 429 should be used for rate limit violations.
- API design should evolve only when requirements justify it.