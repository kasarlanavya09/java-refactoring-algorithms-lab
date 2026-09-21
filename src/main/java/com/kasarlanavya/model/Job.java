package com.kasarlanavya.model;

import java.time.LocalDateTime;

public class Job {

    private final int id;
    private final String name;
    private final int priority;
    private final LocalDateTime createdAt;

    public Job(int id, String name, int priority) {
        if (id <= 0) {
            throw new IllegalArgumentException("Job ID must be positive");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Job name cannot be empty");
        }

        if (priority < 1 || priority > 10) {
            throw new IllegalArgumentException(
                    "Priority must be between 1 and 10"
            );
        }

        this.id = id;
        this.name = name.trim();
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", createdAt=" + createdAt +
                '}';
    }
}
