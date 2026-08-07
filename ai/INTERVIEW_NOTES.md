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