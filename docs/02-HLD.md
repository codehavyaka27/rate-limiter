# High-Level Design (HLD)

---

# Project

Distributed Rate Limiting Microservice

---

# 1. Introduction

The Distributed Rate Limiting Microservice is a standalone backend service responsible for determining whether an incoming request should be allowed or rejected based on configurable request limits.

The service is independent of any specific application and can be integrated with multiple backend services.

---

# 2. System Goals

The system should:

- Protect backend services from excessive traffic.
- Minimize response latency.
- Be horizontally scalable.
- Be reusable across multiple applications.
- Be easy to extend with additional algorithms.
- Maintain clean separation of responsibilities.

---

# 3. Functional Requirements

The system must:

- Accept rate-limit verification requests.
- Identify the client (Version 1: User ID).
- Track request counts.
- Compare request count against configured limits.
- Allow or reject requests.
- Return remaining request count.
- Automatically reset counters after the configured window.

---

# 4. Non-Functional Requirements

## Performance

- Average response time should remain below 5 ms (excluding network latency).

## Scalability

- Multiple service instances should work together.

## Availability

- Failure of one service instance should not stop the system.

## Reliability

- Handle Redis failures gracefully.

## Maintainability

- Components should follow the Single Responsibility Principle.

## Extensibility

- Support additional rate-limiting algorithms without redesigning the system.

---

# 5. High-Level Architecture

```
                 Client
                    │
                    ▼
         Rate Limiter REST API
                    │
                    ▼
         Rate Limiter Service
                    │
                    ▼
                Redis Server
                    │
             Request Counters
```

---

# 6. System Components

## Client

Sends HTTP requests requesting permission to continue.

Examples:

- Mobile Application
- Web Application
- Backend Service

---

## Controller

Responsibilities:

- Accept HTTP requests.
- Validate incoming data.
- Forward requests to the service layer.
- Return HTTP responses.

---

## Service Layer

Responsibilities:

- Execute rate-limiting logic.
- Build Redis keys.
- Compare request counts.
- Decide Allow or Reject.

---

## Redis

Responsibilities:

- Store request counters.
- Store expiration time.
- Provide low-latency read/write operations.

---

# 7. Request Flow

1. Client sends a rate-limit request.
2. Controller validates the request.
3. Service generates a Redis key.
4. Redis returns the current request count.
5. Service compares count with configured limit.
6. If limit is exceeded:
    - Reject request (HTTP 429)
7. Otherwise:
    - Increment counter
    - Return Allow response

---

# 8. Data Storage

Redis stores request counters.

Key:

rate:user:{userId}

Example:

rate:user:101

Value:

7

TTL:

60 seconds

---

# 9. Deployment View

```
Client
    │
    ▼
Spring Boot Application
    │
    ▼
Redis
```

Future deployment:

```
Load Balancer
      │
 ┌────┴────┐
 │         │
Service 1  Service 2
 │         │
 └────┬────┘
      │
      ▼
 Redis
```

---

# 10. Scalability

The application is stateless.

Because request counters are stored in Redis, any service instance can process any request.

Scaling is achieved by adding more Spring Boot instances.

---

# 11. Failure Scenarios

## Redis unavailable

Return HTTP 503.

Log the failure.

Do not crash the application.

---

## Invalid Request

Return HTTP 400.

---

## Request Limit Exceeded

Return HTTP 429.

---

# 12. Future Enhancements

- Sliding Window Counter
- Sliding Window Log
- Token Bucket
- Leaky Bucket
- API Gateway Integration
- Prometheus Metrics
- Grafana Dashboards
- Kubernetes Deployment

---

# Summary

The HLD defines the major components of the Rate Limiting Microservice, their responsibilities, and the overall interaction between clients, the application, and Redis. The design emphasizes scalability, modularity, and maintainability while keeping the implementation independent of specific algorithms.