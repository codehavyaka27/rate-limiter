# API Contract

---

# Service

Distributed Rate Limiting Microservice

Base URL

```
/api/v1
```

---

# Endpoint

## Check Rate Limit

```
POST /api/v1/rate-limit/check
```

Description

Determines whether a client request should be allowed based on the configured rate limit.

---

# Request

Content-Type

```
application/json
```

Request Body

```json
{
  "userId": "101"
}
```

---

# Success Response

HTTP Status

```
200 OK
```

Body

```json
{
  "allowed": true,
  "remainingRequests": 5,
  "retryAfterSeconds": 0
}
```

---

# Rate Limit Exceeded

HTTP Status

```
429 Too Many Requests
```

Body

```json
{
  "allowed": false,
  "remainingRequests": 0,
  "retryAfterSeconds": 18
}
```

---

# Invalid Request

HTTP Status

```
400 Bad Request
```

Example

```json
{
  "message": "userId is required"
}
```

---

# Service Unavailable

HTTP Status

```
503 Service Unavailable
```

Example

```json
{
  "message": "Redis service unavailable"
}
```

---

# Future API Enhancements

Future request body may include:

```json
{
  "userId": "101",
  "endpoint": "/login",
  "plan": "FREE",
  "apiKey": "xxxxx",
  "ipAddress": "192.168.1.10"
}
```

The current API has been intentionally designed to allow these fields to be added without changing the endpoint.

---

# Versioning Strategy

Current Version

```
v1
```

Future versions

```
/api/v2
```

will remain backward compatible whenever possible.

---

# HTTP Status Codes

| Status | Meaning |
|---------|----------|
|200|Request Allowed|
|400|Invalid Request|
|429|Rate Limit Exceeded|
|503|Redis Unavailable|
|500|Unexpected Error|

---

# API Design Principles

- Stateless
- RESTful
- Versioned
- JSON based
- Backward compatible