# Redis Data Model

---

# Purpose

This document defines how request counters are stored inside Redis for Version 1 of the Rate Limiting Microservice.

The objective is to keep the data model simple, efficient and easy to extend.

---

# Data Structure Selection

Version 1 uses the Redis **String** data type.

Reason:

- Simple
- Fast
- Minimal memory usage
- Native support for atomic increment operations

Future versions may migrate to Redis Hashes if additional metadata needs to be stored.

---

# Redis Key Format

```
rate:user:{userId}
```

Example

```
rate:user:101
```

Using prefixes prevents collisions with other Redis data.

---

# Redis Value

The value represents the current request count.

Example

```
7
```

Meaning

The user has made seven requests within the current time window.

---

# TTL (Time To Live)

Every Redis key has a TTL.

Default:

```
60 seconds
```

After expiration, Redis automatically removes the key.

No manual cleanup is required.

---

# Example Lifecycle

Request 1

```
rate:user:101

Value = 1

TTL = 60
```

Request 2

```
Value = 2

TTL = 59
```

Request 8

```
Value = 8

TTL = 53
```

After one minute

Redis automatically deletes

```
rate:user:101
```

The next request starts a new counter.

---

# Redis Commands

Version 1 uses:

GET

Read current counter.

---

INCR

Increment request counter.

---

EXPIRE

Assign TTL to new counters.

---

TTL

Retrieve remaining time before counter reset.

---

# Atomic Operations

Redis executes commands atomically.

This ensures multiple simultaneous requests cannot corrupt the request counter.

No additional locking mechanism is required.

---

# Future Improvements

Future versions may store:

- User Plan
- Endpoint
- API Key
- Burst Limits
- Remaining Tokens

At that stage Redis Hashes may become a better storage model.

---

# Summary

The Version 1 Redis model intentionally remains simple by storing one counter per user using Redis Strings and automatic expiration. This design provides excellent performance while keeping the implementation straightforward.