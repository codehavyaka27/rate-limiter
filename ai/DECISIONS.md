# Architecture Decision Records (ADR)

---

# Purpose

This document records all significant architectural and technical decisions made during the development of the Distributed Rate Limiting Microservice.

Each decision includes the reasoning behind it, the alternatives considered, and the expected consequences.

Future contributors should update this file whenever an important design decision is made.

---

# ADR-001

## Title

Use Redis as the Primary Data Store

### Status

Accepted

### Problem

The service needs to store request counters with very low latency while automatically resetting them after a configurable time window.

### Options Considered

- MySQL
- PostgreSQL
- Java HashMap
- Redis

### Decision

Redis was selected.

### Reason

- In-memory performance
- Native TTL support
- Atomic increment operations
- Shared storage across multiple service instances
- Widely used for rate limiting in production

### Trade-offs

Advantages

- Extremely fast
- Supports automatic expiration
- Easy horizontal scaling

Disadvantages

- Data stored in memory
- Additional infrastructure component

### Future Impact

Supports distributed deployments without changing application logic.

---

# ADR-002

## Title

Implement Fixed Window Algorithm First

### Status

Accepted

### Problem

The service requires a rate-limiting algorithm suitable for the first production-ready version.

### Options Considered

- Fixed Window
- Sliding Window Counter
- Sliding Window Log
- Token Bucket
- Leaky Bucket

### Decision

Implement Fixed Window first.

### Reason

- Simple implementation
- Easy to understand
- Easy to test
- Demonstrates Redis fundamentals
- Provides a good foundation for future algorithms

### Trade-offs

Advantages

- Low complexity
- Fast execution
- Low memory usage

Disadvantages

- Boundary burst issue

### Future Impact

Future versions will introduce Sliding Window Counter and Token Bucket while keeping the same architecture.

---

# ADR-003

## Title

Adopt Layered Architecture

### Status

Accepted

### Problem

Business logic, HTTP handling and Redis access should remain independent.

### Decision

Use the following layers:

Controller

↓

Service

↓

Repository

↓

Redis

### Reason

- Clear separation of responsibilities
- Easier testing
- Better maintainability
- Easier onboarding for new developers

### Trade-offs

Advantages

- Modular
- Easy to extend
- Easier debugging

Disadvantages

- Slightly more classes

---

# ADR-004

## Title

Use Strategy Pattern for Rate Limiting Algorithms

### Status

Accepted

### Problem

Multiple rate-limiting algorithms will be supported in future versions.

### Decision

Create a common interface:

RateLimitingAlgorithm

Each algorithm will implement this interface.

### Reason

- Open/Closed Principle
- Easy algorithm replacement
- Independent testing
- Cleaner service layer

### Future Impact

New algorithms can be introduced without modifying existing business logic.

---

# ADR-005

## Title

Use Docker for Local Development

### Status

Accepted

### Problem

Redis installation differs across operating systems.

### Decision

Run Redis inside Docker.

### Reason

- Consistent development environment
- Easy onboarding
- No manual installation
- Production-like setup

---

# ADR-006

## Title

Maintain Comprehensive Documentation

### Status

Accepted

### Decision

Create dedicated documentation before implementation.

### Reason

- Improve maintainability
- Better interview preparation
- Easier collaboration
- Clear project direction

---

# ADR-007

## Title

Centralize Redis Operations Behind a Repository

## Status

Accepted

## Context

The application needs to interact with Redis for rate limiting.

Direct usage of RedisTemplate throughout the application would tightly couple business logic to Redis and make future changes difficult.

## Decision

Introduce a dedicated RedisRepository.

Only RedisRepository is allowed to access RedisTemplate.

All higher layers communicate only with the repository.

## Consequences

### Advantages

- Business logic remains independent of Redis.
- Easier testing.
- Easier maintenance.
- Infrastructure changes are isolated.

### Disadvantages

- One additional abstraction layer.

# ADR 008
Title

Verify Business Logic Before REST Integration

Decision

The core rate limiting algorithm must be verified independently before exposing it through HTTP.

Reason

Separates business logic verification from web layer debugging and simplifies troubleshooting.

Status

Accepted

# ADR 009
Title

Temporary Startup Verification

Decision

Use a CommandLineRunner for temporary verification during development.

Reason

Allows isolated testing of the service layer without introducing controllers or HTTP concerns.

Status

Accepted

# ADR 10    

Title

Use REST Controller for HTTP Layer

Decision

Expose the Rate Limiter through a dedicated REST Controller.

Reason

Separates HTTP responsibilities from business logic.

## ADR 11
Title

Use DTOs for API Contracts

Decision

Introduce request and response DTOs.

Reason

Decouples external API contracts from internal business models.

Status

Accepted

Status

Accepted

## ADR 12
Title

Use HTTP 429 for Rate Limit Violations

Decision

Return HTTP 429 Too Many Requests when the configured limit is exceeded.

Reason

Aligns with the HTTP specification and industry-standard REST API practices.

Status

Accepted
# Future ADRs

Examples

- Swagger integration
- Dynamic configuration
- Kubernetes deployment
- Redis Cluster
- API Gateway integration
- Metrics collection
- Authentication strategy

---

# Rules

Whenever an important architectural decision is made:

1. Create a new ADR.
2. Assign the next sequential number.
3. Describe the problem.
4. Document the alternatives.
5. Record the decision.
6. Explain the reasoning.
7. Record trade-offs.