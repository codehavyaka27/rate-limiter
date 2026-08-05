# Rate Limiting Algorithms

---

# Purpose

This document describes the different rate-limiting algorithms that can be used by the Rate Limiting Microservice.

Although Version 1 implements the Fixed Window algorithm, the architecture has been intentionally designed to support additional algorithms without major code changes.

---

# Algorithm Comparison

| Algorithm | Accuracy | Complexity | Memory Usage | Version |
|------------|----------|------------|--------------|---------|
| Fixed Window | Medium | Low | Low | Version 1 |
| Sliding Window Log | High | High | High | Future |
| Sliding Window Counter | High | Medium | Medium | Future |
| Token Bucket | Very High | Medium | Medium | Future |
| Leaky Bucket | High | Medium | Medium | Future |

---

# 1. Fixed Window Counter

## Description

The Fixed Window algorithm counts the number of requests received during a fixed time interval.

Example

Limit:

10 requests per minute

Window:

12:00:00 → 12:00:59

The counter resets when the next window begins.

---

## Example

```
12:00:05

Request 1
```

Counter = 1

---

```
12:00:20

Request 7
```

Counter = 7

---

```
12:00:50

Request 11
```

Rejected

---

```
12:01:00
```

Counter resets.

---

## Advantages

- Very simple implementation
- Excellent Redis support
- Fast execution
- Low memory consumption
- Easy to understand

---

## Disadvantages

Boundary problem.

Example:

10 requests at

12:00:59

+

10 requests at

12:01:01

=

20 requests in 2 seconds.

---

# 2. Sliding Window Log

## Description

Every request timestamp is stored.

Old timestamps are continuously removed.

The request is allowed only if the number of timestamps inside the window remains below the configured limit.

---

## Advantages

- Very accurate
- Eliminates boundary problems

---

## Disadvantages

- Higher memory usage
- More Redis operations
- More complex implementation

---

# 3. Sliding Window Counter

## Description

Combines the simplicity of Fixed Window with the accuracy of Sliding Window.

Instead of storing every request, it stores counters for adjacent windows and calculates a weighted request count.

---

## Advantages

- More accurate than Fixed Window
- Lower memory usage than Sliding Window Log
- Good production choice

---

## Disadvantages

- Slightly more complex calculations

---

# 4. Token Bucket

## Description

Clients receive tokens at a fixed rate.

Each request consumes one token.

Requests are rejected only when no tokens remain.

---

## Advantages

- Supports burst traffic
- Widely used in production
- Excellent user experience

---

## Disadvantages

- More complicated implementation

---

# 5. Leaky Bucket

## Description

Incoming requests enter a queue.

Requests leave the queue at a constant rate.

Excess requests are dropped.

---

## Advantages

- Smooth traffic
- Predictable throughput

---

## Disadvantages

- Queue management required

---

# Why Version 1 Uses Fixed Window

The Fixed Window algorithm was selected because:

- It satisfies the current project requirements.
- It is easy to explain during interviews.
- It demonstrates Redis fundamentals.
- It is simple to test.
- It provides a strong foundation before implementing more advanced algorithms.

The application architecture uses the Strategy Pattern so future algorithms can be added without modifying existing business logic.

---

# Future Roadmap

Version 1

- Fixed Window

Version 2

- Sliding Window Counter

Version 3

- Token Bucket

Version 4

- Dynamic Algorithm Selection

---

# Summary

The project begins with the Fixed Window algorithm due to its simplicity and educational value. The architecture remains open for future algorithms, ensuring the service can evolve without major structural changes.