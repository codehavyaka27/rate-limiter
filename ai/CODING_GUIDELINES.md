# Coding Guidelines

---

# Purpose

This document defines the coding standards and engineering practices followed throughout the project.

The objective is to keep the codebase clean, consistent, maintainable, and easy to understand.

---

# 1. General Principles

Every line of code should be:

- Readable
- Testable
- Maintainable
- Simple
- Consistent

Always optimize for readability before cleverness.

---

# 2. Clean Code Principles

Follow Robert C. Martin's Clean Code principles.

Guidelines

- Small methods
- Small classes
- Descriptive names
- No duplicated logic
- One responsibility per class

---

# 3. SOLID Principles

The project follows:

- Single Responsibility Principle
- Open/Closed Principle
- Liskov Substitution Principle
- Interface Segregation Principle
- Dependency Inversion Principle

---

# 4. Package Responsibilities

controller

Only HTTP handling.

Never business logic.

---

service

Business logic only.

---

repository

Redis communication only.

---

dto

Request and Response objects.

---

algorithm

Rate limiting algorithms only.

---

util

Utility methods.

---

config

Configuration classes.

---

exception

Custom exceptions and global exception handling.

---

# 5. Naming Conventions

Classes

```
RateLimiterService
RedisRepository
FixedWindowAlgorithm
```

Interfaces

```
RateLimitingAlgorithm
```

Methods

```
checkRateLimit()

incrementCounter()

generateRedisKey()
```

Variables

camelCase

```
remainingRequests

requestLimit
```

Constants

UPPER_CASE

```
DEFAULT_LIMIT

WINDOW_DURATION
```

Packages

lowercase only

```
controller

service

repository
```

---

# 6. Method Design

Methods should:

- Perform one task
- Be easy to understand
- Return meaningful values
- Avoid side effects

Avoid methods longer than 30–40 lines unless justified.

---

# 7. Exception Handling

Never swallow exceptions.

Avoid

```java
catch(Exception e){}
```

Instead

- Log the error
- Throw meaningful exceptions
- Return appropriate HTTP status codes

---

# 8. Logging

Use SLF4J.

Log Levels

INFO

- Application startup
- Redis connected
- Important events

WARN

- Rate limit exceeded
- Invalid requests

ERROR

- Redis unavailable
- Unexpected failures

Never log sensitive information.

---

# 9. Validation

Validate all external input.

Examples

- Null values
- Empty userId
- Invalid request body

Prefer Bean Validation where appropriate.

---

# 10. Comments

Write comments only when necessary.

Avoid

```java
// increment counter

counter++;
```

Instead explain

why

not

what.

---

# 11. Testing

Every business feature should have tests.

Priority

- Business logic
- Edge cases
- Error handling

---

# 12. Documentation

Whenever a new feature is implemented:

Update

- SESSION_LOG.md
- PROJECT_STATE.md
- README.md (if applicable)

Documentation is part of the feature.

---

# 13. Git Commits

Use meaningful commit messages.

Examples

```
Add Redis configuration

Implement Fixed Window algorithm

Add unit tests for RateLimiterService
```

Avoid

```
update

changes

final

done
```

---

# 14. Code Reviews

Before considering work complete, verify:

- Builds successfully
- No warnings
- Tests pass
- Documentation updated
- Commit message is meaningful

---

# Guiding Principle

Always write code that another engineer can understand six months later without additional explanation.