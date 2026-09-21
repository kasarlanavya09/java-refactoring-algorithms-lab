# Java Refactoring & Algorithms Lab

A Java project demonstrating practical software engineering skills including feature implementation, bug prevention, codebase refactoring, data structures, algorithms, unit testing, and performance optimization.

## Project Overview

This project implements a priority-based job processing system.

It was designed to demonstrate how an initial implementation can be tested, analyzed, refactored, and optimized while preserving expected behavior.

The project focuses on:

- Java 17
- Algorithms and data structures
- Feature implementation
- Input validation
- Bug prevention
- Codebase refactoring
- Performance optimization
- Unit testing with JUnit 5
- Priority-based processing
- Maintainable software design

## Architecture

The project separates domain models, business logic, algorithms, tests, and performance experiments.

```text
Job Model
    |
    v
JobService
    |
    +----> HashMap ID Lookup
    |
    +----> Job Collection

PriorityJobQueue
    |
    v
PriorityQueue / Heap
