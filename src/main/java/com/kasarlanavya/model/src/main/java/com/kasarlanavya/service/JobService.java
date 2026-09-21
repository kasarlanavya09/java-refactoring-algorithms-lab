package com.kasarlanavya.service;

import com.kasarlanavya.model.Job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JobService {

    private final List<Job> jobs = new ArrayList<>();
    private final Map<Integer, Job> jobsById = new HashMap<>();
    private int nextId = 1;

    public Job addJob(String name, int priority) {
        Job job = new Job(nextId++, name, priority);

        jobs.add(job);
        jobsById.put(job.getId(), job);

        return job;
    }

    public Optional<Job> findById(int id) {
        return Optional.ofNullable(jobsById.get(id));
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
        Job job = jobsById.remove(id);

        if (job == null) {
            return false;
        }

        jobs.remove(job);
        return true;
    }

    public int size() {
        return jobs.size();
    }
}
