# Engineering Questions

---

# Purpose

This document records important engineering questions asked during development.

These questions often lead to architectural improvements and deeper understanding.

---

# Sprint 3

## Why Repository Pattern?

Answered

Repository separates business logic from infrastructure.

---

## Why not use RedisTemplate directly?

Answered

RedisTemplate is an infrastructure concern.

---

## Why Atomic Operations instead of GET + SET?

Answered

Atomic operations eliminate race conditions.

---

## Why does TTL exist?

Answered

TTL automatically resets the rate limiting window.

---

## Why should Service build Redis Keys?

Answered

Because key generation is part of business logic.

---

## Why isn't RedisKeyBuilder injected?

Answered

It is a stateless utility class.

---

## Should increment() accept delta?

Decision

No.

Reason

Current requirements only need increment by one.

Applied YAGNI and KISS.

---

## Why should configuration eventually move to application.properties?

Answered

Configuration changes more frequently than business logic.

---