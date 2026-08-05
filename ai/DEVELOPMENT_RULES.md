# Development Rules

---

# Purpose

This document defines the development workflow followed throughout the project.

Every feature, bug fix, and enhancement must follow these rules to keep the project clean, maintainable, and production-ready.

---

# Engineering Philosophy

We are not building a college project.

We are building a production-inspired backend service.

Every decision should prioritize:

- Maintainability
- Simplicity
- Readability
- Scalability
- Extensibility

---

# Development Workflow

Every feature follows the same lifecycle.

```
Requirement

↓

Discussion

↓

Design

↓

Documentation

↓

Implementation

↓

Testing

↓

Review

↓

Git Commit

↓

Session Log Update
```

Never skip steps.

---

# Feature Development Checklist

Before writing code:

- Understand the requirement.
- Review related documentation.
- Discuss architecture.
- Identify affected components.
- Identify edge cases.

Only then begin implementation.

---

# Implementation Rules

When implementing a feature:

- Keep controllers thin.
- Keep services focused on business logic.
- Repository only accesses Redis.
- Never duplicate logic.
- Never hardcode configuration.
- Prefer composition over inheritance.
- Follow SOLID principles.

---

# Git Workflow

Main Branch

```
main
```

Development Branch

```
develop
```

Feature Branch

```
feature/<feature-name>
```

Examples

```
feature/redis-connection

feature/fixed-window

feature/swagger
```

Merge only after successful testing.

---

# Commit Rules

Every commit should represent one logical change.

Good

```
Add Redis configuration

Implement Fixed Window algorithm

Add Redis repository

Configure Docker Compose
```

Bad

```
update

changes

done

final
```

---

# Documentation Rules

Whenever a feature is completed:

Update

- PROJECT_STATE.md
- SESSION_LOG.md
- README.md (if needed)

Documentation is considered part of the feature.

---

# Testing Rules

Every feature must be verified before merging.

Minimum checks:

- Application starts.
- Project builds.
- Unit tests pass.
- Manual verification completed.

---

# Error Handling Rules

Never ignore exceptions.

Always:

- Log errors.
- Return meaningful responses.
- Use appropriate HTTP status codes.
- Fail gracefully.

---

# Logging Rules

INFO

Normal application events.

WARN

Recoverable problems.

ERROR

Unexpected failures.

Never log passwords, API keys, or sensitive information.

---

# Code Review Checklist

Before considering a task complete:

- Code follows project structure.
- Naming conventions are respected.
- No duplicated code.
- No unused imports.
- No commented-out code.
- Documentation updated.
- Tests added where appropriate.

---

# Definition of Done

A task is complete only if:

- Code is implemented.
- Tests pass.
- Documentation updated.
- Session log updated.
- Project builds successfully.
- No known critical issues remain.

---

# Sprint Completion Checklist

Every sprint should end with:

- Stable code
- Updated documentation
- Meaningful Git commits
- Working application
- Clear next objective

---

# Learning Rule

Understand before implementing.

Never copy code without understanding:

- Why it exists.
- Why it was designed this way.
- What alternatives were considered.

---

# Team Agreement

Throughout this project:

- We design before coding.
- We discuss before implementing.
- We write clean code.
- We test every feature.
- We document every major decision.
- We continuously improve the architecture.

---

# Final Principle

Write software that another engineer can understand, extend, and maintain without needing the original author to explain it.


---

# Daily Engineering Checklist

At the beginning of every session:

- Read PROJECT_STATE.md
- Read SESSION_LOG.md
- Review the current sprint goal
- Identify today's objective

At the end of every session:

- Verify the application builds
- Update documentation
- Update SESSION_LOG.md
- Update PROJECT_STATE.md
- Commit changes with a meaningful message
- Define the first task for the next session