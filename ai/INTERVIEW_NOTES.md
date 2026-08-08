# Interview Notes

---

# Purpose

This document stores interview questions naturally discovered while building this project.

Instead of memorizing answers, each answer is based on decisions made during development.

---

# Repository Layer

## Q1

Why Repository Pattern?

Answer

Repository abstracts infrastructure from business logic, making the application easier to maintain, test, and extend.

---

## Q2

Why not use RedisTemplate directly inside the Service?

Answer

Because RedisTemplate is an infrastructure concern.

The Service should only contain business logic.

---

## Q3

Explain Atomic Operations.

(To be completed)

---

## Q4

Why Redis for Rate Limiting?

(To be completed)

---

## Q5

Difference between Business Logic and Infrastructure Logic.

(To be completed)

---

# Sprint 4

Q: What is CommandLineRunner?

Q: When is CommandLineRunner executed?

Q: Why verify the service layer before creating REST APIs?

Q: Why should verification utilities be temporary?

Q: Why is Thread.sleep() acceptable here but not inside a Service?


# Sprint 4

Q: Difference between @Controller and @RestController?

Q: Why use DTOs?

Q: Why use @RequestBody?

Q: Why use ResponseEntity?

Q: Why return HTTP 429 instead of HTTP 200?

Q: Why shouldn't the Controller access Redis directly?

Q: What responsibilities belong in a Controller?