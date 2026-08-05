# PROJECT CONTEXT

---

# Project Name

Distributed Rate Limiting Microservice

---

# Purpose

Build a production-inspired backend microservice capable of limiting incoming requests using Redis while following clean architecture and backend engineering best practices.

The project is intended to simulate how infrastructure teams build reusable backend services.

---

# Primary Goals

- Learn backend architecture
- Learn Redis
- Learn distributed system fundamentals
- Learn Spring Boot
- Learn Docker
- Learn software design principles
- Build a portfolio-quality project

---

# Current Status

Current Phase:

Project Documentation

Completed:

✅ Problem Statement

✅ High-Level Design

✅ Low-Level Design

✅ API Contract

✅ Redis Data Model

✅ Algorithms

✅ Development Roadmap

✅ Test Plan

Current Sprint:

Sprint 1

---

# Technology Stack

Language

Java 21

Framework

Spring Boot

Database

Redis

Build Tool

Maven

Containerization

Docker

Documentation

Markdown

Testing

JUnit

Mockito

Load Testing

k6

---

# Current Architecture

```
Client

↓

Controller

↓

Service

↓

Repository

↓

Redis
```

---

# Current Algorithm

Version 1

Fixed Window Counter

Future

- Sliding Window Counter
- Token Bucket
- Leaky Bucket

---

# Design Principles

- SOLID
- Layered Architecture
- Clean Code
- Single Responsibility Principle
- Open/Closed Principle
- Strategy Pattern

---

# Current Folder Structure

```
src/
docs/
ai/
docker/
```

---

# Next Milestone

Sprint 2

Redis Integration

---

# Long-Term Vision

Build a production-ready infrastructure component that demonstrates backend engineering, distributed systems, and scalable architecture suitable for software engineering interviews.

---

# Notes

This file should always represent the latest state of the project.

Whenever a sprint is completed, update this document.

---

# Elevator Pitch

This project is a production-inspired Rate Limiting Microservice built with Spring Boot and Redis. It protects backend services from excessive API traffic by enforcing configurable request limits using Redis-backed counters. The architecture is intentionally modular, allowing multiple rate-limiting algorithms to be implemented using the Strategy Pattern while remaining horizontally scalable and easy to extend.