# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java concurrency (并发) demonstration repository containing educational examples covering various aspects of multi-threading and concurrent programming in Java. The code is organized by topic in the `src/` directory.

## Running Code

This project uses plain Java with no build system (Maven/Gradle). To compile and run:

```bash
# Compile a single file
javac -d bin src/create/ThreadPoolTest.java

# Run the compiled class
java -cp bin create.ThreadPoolTest
```

Alternatively, use your IDE (IntelliJ IDEA, Eclipse, VS Code) to open and run individual Java files directly.

## Code Organization

The `src/` directory is organized by concurrency topics:

- **create/** - Thread creation methods: extending Thread, implementing Runnable, lambda expressions, thread pools, Timer
- **deadlock/** - Deadlock examples and fixes: dining philosophers, transfer money between accounts, deadlock detection with ThreadMXBean, tryLock solutions
- **jmm/** - Java Memory Model: field visibility, out-of-order execution
- **livelock/** - Livelock scenarios and solutions
- **object/** - Object-level concurrency: wait/notify, producer-consumer pattern, printing odd/even numbers
- **properties/** - Thread properties: ID, name
- **singleton/** - Singleton pattern implementations (8 variants) with different thread-safety approaches
- **state/** - Thread states: NEW, RUNNABLE, TERMINATED, BLOCKED, WAITING, TIMED_WAITING
- **stop/** - Stopping threads: interrupt mechanisms, volatile flags (correct and incorrect usage)
- **thread/** - Thread operations: join, sleep behavior with locks/monitors
- **threadexception/** - Uncaught exception handlers
- **threadlocal/** - ThreadLocal usage including thread-safe SimpleDateFormat usage
- **threadpool/** - ThreadPoolExecutor configuration examples
- **threadsafe/** - Thread safety issues and debugging
- **volatile_/** - Volatile keyword usage and atomicity limitations

## Common Patterns

**Main method entry points**: Most demo files have a `public static void main(String[] args)` method and can be run directly.

**Inner classes**: Many demos use static inner classes for related components (e.g., `Producer`, `Consumer`, `Philosopher`).

**Chinese comments**: The codebase contains Chinese comments explaining concepts.
