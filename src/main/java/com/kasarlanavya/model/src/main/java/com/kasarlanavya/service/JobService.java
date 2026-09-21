package com.kasarlanavya.service;

import com.kasarlanavya.model.Job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class JobService {

    private final List<Job> jobs = new ArrayList<>();
    private int nextId = 1;

    public Job addJob(String name, int priority) {
        Job job = new Job(nextId++, name, priority);
        jobs.add(job);
        return job;
    }

    public Optional<Job> findById(int id) {
        return jobs.stream()
                .filter(job -> job.getId() == id)
                .findFirst();
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs);
    }

    public List<Job> getJobsByPriority() {
        List<Job> sortedJobs = new ArrayList<>(jobs);

        sortedJobs.sort(
                Comparator.comparingInt(Job::getPriority)
                        .reversed()
                        .thenComparing(Job::getCreatedAt)
        );

        return sortedJobs;
    }

    public boolean removeJob(int id) {
        return jobs.removeIf(job -> job.getId() == id);
    }

    public int size() {
        return jobs.size();
    }
}
