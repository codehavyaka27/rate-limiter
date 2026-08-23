# Distributed API Rate Limiter

A Redis-backed API rate limiting microservice built with Spring Boot, designed to control request traffic using a configurable Fixed Window algorithm.

The service provides a REST API that determines whether a request should be allowed or rejected based on a user's request count within a configured time window.

---

## Features

- Redis-backed Fixed Window rate limiting
- Atomic Redis `INCR` operations for request counting
- Automatic key expiration using Redis TTL
- Configurable request limits and time windows
- HTTP `429 Too Many Requests` enforcement
- Request validation and centralized exception handling
- Swagger/OpenAPI API documentation
- Spring Boot Actuator health monitoring
- Prometheus/Micrometer metrics
- Custom allowed/rejected request counters
- Docker and Docker Compose support
- Redis healthcheck and service dependency management
- GitHub Actions CI
- Apache JMeter load testing
- Deployed on Render

---

## Architecture

```text
                    Client
                      |
                      v
              REST API / Controller
                      |
                      v
              RateLimiterService
                      |
             +--------+--------+
             |                 |
             v                 v
          Redis             Metrics
             |             (Micrometer)
             |
      INCR + expiration
```

The application follows a layered architecture:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
Redis
```

### Components

**Controller**
Exposes the REST API and handles request validation and HTTP responses.

**Service**
Contains the rate limiting logic and determines whether a request is allowed.

**Repository**
Abstracts Redis operations such as incrementing counters and setting expiration.

**Redis**
Stores per-user request counters with automatic expiration.

---

## How Rate Limiting Works

The current implementation uses a **Fixed Window** algorithm.

For each user, the service creates a Redis key representing that user's rate-limit window.

Example:

rate-limit:user123


When a request arrives:

```text
1. Generate Redis key
        |
        v
2. Atomic Redis INCR
        |
        v
3. If first request:
   Set key expiration
        |
        v
4. Compare count with configured limit
        |
      +---+---+
      |       |
      v       v
   Allowed  Rejected
    200       429
```

With the default configuration:

- **Maximum requests** = 5
- **Window** = 1 minute

The first five requests are allowed. Requests beyond the configured limit receive HTTP `429 Too Many Requests`.

---

## API

### Check Rate Limit

POST /api/v1/rate-limit
Content-Type: application/json


**Request:**

```json
{
  "userId": "user123"
}
```

**Allowed response:**

```json
{
  "allowed": true
}
```

HTTP status: `200 OK`

**Rejected response:**

```json
{
  "allowed": false
}
```

HTTP status: `429 Too Many Requests`

### Example Request

Using PowerShell:

```powershell
Invoke-RestMethod `
  -Uri "http://localhost:8080/api/v1/rate-limit" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"userId":"user123"}'
```

---

## Configuration

The application supports environment-variable based configuration.

| Environment Variable      | Default     | Description                  |
|----------------------------|-------------|-------------------------------|
| `REDIS_HOST`                | `localhost` | Redis hostname                |
| `REDIS_PORT`                | `6379`      | Redis port                    |
| `SERVER_PORT`               | `8080`      | Application port              |
| `RATE_LIMIT_MAX_REQUESTS`   | `5`         | Maximum requests per window   |
| `RATE_LIMIT_WINDOW`         | `1m`        | Rate-limit window              |

Example:

```env
REDIS_HOST=redis
REDIS_PORT=6379
SERVER_PORT=8080
RATE_LIMIT_MAX_REQUESTS=5
RATE_LIMIT_WINDOW=1m
```

---

## Running Locally

### Prerequisites

- Java 21+
- Maven
- Docker

### Run with Maven

Start Redis separately and run:

```bash
.\mvnw.cmd spring-boot:run
```

### Run with Docker Compose

From the project root:

```bash
docker compose -f .\Docker\docker-compose.yml up -d --build
```

Check services:

```bash
docker compose -f .\Docker\docker-compose.yml ps
```

Expected services:

- `rate-limiter-app`
- `rate-limiter-redis`

Stop the services:

```bash
docker compose -f .\Docker\docker-compose.yml down
```

---

## Docker

The application uses a multi-stage Docker build:

```text
Maven Build Image
       |
       v
Spring Boot JAR
       |
       v
Java 21 Runtime Image
```

The Docker Compose setup starts:

- Spring Boot application
- Redis 7

Redis includes a healthcheck, and the application waits for Redis to become healthy before starting.

The application image is built automatically by Docker Compose, so a fresh clone does not require a pre-built local image.

---

## Monitoring & Observability

Spring Boot Actuator provides:

- `/actuator/health`
- `/actuator/info`
- `/actuator/prometheus`

### Health

GET /actuator/health


Example:

```json
{
  "status": "UP"
}
```

The health endpoint also verifies Redis connectivity internally.

### Application Information

GET /actuator/info


Example:

```json
{
  "app": {
    "name": "Distributed API Rate Limiter",
    "description": "Redis-backed API rate limiting microservice",
    "version": "1.0.0"
  }
}
```

### Prometheus Metrics

GET /actuator/prometheus


The application exposes standard JVM, HTTP, process, and Redis metrics through Micrometer.

It also exposes custom rate limiter metrics:

rate_limiter_requests_total{result="allowed"}
rate_limiter_requests_total{result="rejected"}


These metrics allow the number of allowed and rejected requests to be monitored independently.

---

## Performance Testing

Apache JMeter 5.6.3 was used to benchmark the service locally using Docker.

### Results

| Requests | Concurrent Users | Throughput      | Avg Latency | Error Rate |
|----------|-------------------|------------------|--------------|------------|
| 1,000    | 50                 | ~201 req/s       | 3 ms         | 0%         |
| 5,000    | 100                | ~499 req/s       | 7 ms         | 0%         |
| 10,000   | 200                | ~1.7K–2.1K req/s | 61–76 ms     | 0%         |

The 10,000-request benchmark produced approximately 1.7K–2.1K requests/sec across repeated runs with 200 concurrent users and 0% errors.

> These results represent observed performance in the local Docker/JMeter environment and should not be interpreted as production capacity.

---

## Testing

The project includes automated tests covering the rate limiter service and integration behavior.

Run the complete test suite:

```bash
.\mvnw.cmd clean test
```

---

## CI/CD

GitHub Actions automatically runs for pushes and pull requests targeting the `develop` branch.

The CI pipeline:

```text
Checkout Repository
        |
        v
Setup Java 21
        |
        v
Start Redis Service
        |
        v
Run Maven Tests
        |
        v
Build Spring Boot Application
        |
        v
Build Docker Image
```

The CI workflow is located at:

.github/workflows/ci.yml


---

## Deployment

The application is containerized and deployed as a web service on **Render**.

Redis configuration is provided through environment variables, allowing the same application image to run across different environments.

---

## Tech Stack

- Java 21
- Spring Boot 3.5
- Spring Data Redis
- Redis 7
- REST API
- Maven
- Docker
- Docker Compose
- Micrometer
- Prometheus
- Spring Boot Actuator
- JUnit
- Apache JMeter
- GitHub Actions
- Render

---

## Project Structure

ratelimiter/
│
├── .github/
│ └── workflows/
│ └── ci.yml
│
├── Docker/
│ └── docker-compose.yml
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/api/ratelimiter/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── repository/
│ │ │ ├── dto/
│ │ │ ├── exception/
│ │ │ └── util/
│ │ │
│ │ └── resources/
│ │ └── application.properties
│ │
│ └── test/
│
├── ai/
├── docs/
├── Dockerfile
├── pom.xml
└── README.md


---

## Future Improvements

Potential future versions can explore:

- Token Bucket algorithm
- Leaky Bucket algorithm
- Distributed rate limiting across multiple application instances
- Dynamic per-user limits
- API-key based rate limiting
- Prometheus + Grafana dashboards
- Additional rate-limit policies
- Advanced concurrency and load testing
- Rate-limit headers such as `X-RateLimit-Limit` and `X-RateLimit-Remaining`

---

## Current Status

- **Algorithm:** Fixed Window
- **Backend:** Spring Boot + Redis
- **Containerization:** Docker + Docker Compose
- **Monitoring:** Spring Boot Actuator + Micrometer + Prometheus
- **Load Testing:** Apache JMeter
- **CI:** GitHub Actions
- **Deployment:** Render
- **Performance:** Benchmarked up to 10,000 requests with 200 concurrent users
- **CI Status:** Passing