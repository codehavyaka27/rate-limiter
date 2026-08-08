# Backend Engineering Playbook

---

# Purpose

This document is my personal backend engineering handbook.

It captures engineering concepts learned while building this project.

The focus is not on the project itself, but on the engineering principles behind it.

Every concept should answer:

- What problem does it solve?
- Why does it exist?
- How does it work?
- What are its trade-offs?
- Where is it used in this project?
- How would I explain it in an interview?

---

# Index

## Architecture

- Repository Pattern
- Layered Architecture
- Separation of Concerns
- Dependency Injection
- Single Responsibility Principle

---

## Redis

- Redis Overview
- Atomic Operations
- Time To Live (TTL)
- Redis Key Naming Strategy

---

## Design Principles

- KISS
- YAGNI
- DRY
- SOLID

---

## Concurrency

- Race Conditions
- Atomicity

---

## Spring Boot

If you're using Spring, let Spring manage the lifecycle of Spring-managed objects.

---

## Docker

(To be added)

---

# Template

Every concept should follow:

## Concept Name

### Problem

...

### Why?

...

### Solution

...

### Advantages

...

### Disadvantages

...

### Used In This Project

...

### Interview Questions

...

### Key Takeaways

...
## CommandLineRunner

### Problem

How can custom code execute automatically after Spring Boot has finished creating all beans?

### Solution

Implement the CommandLineRunner interface and register the class as a Spring Bean.

### Lifecycle

Application Starts

↓

Spring Container Initializes

↓

Beans Created

↓

Dependencies Injected

↓

CommandLineRunner Executes

### Used In This Project

Used temporarily to verify the Fixed Window Rate Limiter before exposing it through a REST API.

### Key Takeaways

- Executed automatically by Spring Boot.
- Runs after the application context is fully initialized.
- Useful for startup tasks and development verification.
- Should not contain permanent business functionality.

## REST Controller

### Responsibility

Expose business functionality through HTTP while remaining independent of business logic and infrastructure.

### Responsibilities

- Receive HTTP requests.
- Convert JSON into DTOs.
- Delegate work to the Service layer.
- Convert results into HTTP responses.

### Should Not

- Implement business logic.
- Access repositories.
- Access Redis.
- Implement algorithms.

### Principle

Controllers orchestrate.

Services decide.

Repositories persist.
