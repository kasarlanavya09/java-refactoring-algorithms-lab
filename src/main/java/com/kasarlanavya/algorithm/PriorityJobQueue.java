package com.kasarlanavya.algorithm;

import com.kasarlanavya.model.Job;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class PriorityJobQueue {

    private final PriorityQueue<Job> queue;

    public PriorityJobQueue() {
        this.queue = new PriorityQueue<>(
                Comparator.comparingInt(Job::getPriority)
                        .reversed()
                        .thenComparing(Job::getCreatedAt)
        );
    }

    public void add(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null");
        }

        queue.offer(job);
    }

    public Optional<Job> peekNext() {
        return Optional.ofNullable(queue.peek());
    }

    public Optional<Job> processNext() {
        return Optional.ofNullable(queue.poll());
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
