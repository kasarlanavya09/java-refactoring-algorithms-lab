package com.kasarlanavya.service;

import com.kasarlanavya.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JobServiceTest {

    private JobService jobService;

    @BeforeEach
    void setUp() {
        jobService = new JobService();
    }

    @Test
    void shouldAddJob() {
        Job job = jobService.addJob("Process payment", 8);

        assertEquals(1, job.getId());
        assertEquals("Process payment", job.getName());
        assertEquals(8, job.getPriority());
        assertEquals(1, jobService.size());
    }

    @Test
    void shouldFindJobById() {
        Job created = jobService.addJob("Generate report", 5);

        Optional<Job> result = jobService.findById(created.getId());

        assertTrue(result.isPresent());
        assertEquals(created.getId(), result.get().getId());
        assertEquals("Generate report", result.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenJobDoesNotExist() {
        Optional<Job> result = jobService.findById(999);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldRemoveJob() {
        Job job = jobService.addJob("Remove old records", 4);

        boolean removed = jobService.removeJob(job.getId());

        assertTrue(removed);
        assertEquals(0, jobService.size());
        assertTrue(jobService.findById(job.getId()).isEmpty());
    }

    @Test
    void shouldReturnJobsInPriorityOrder() {
        jobService.addJob("Low priority", 2);
        jobService.addJob("Critical job", 10);
        jobService.addJob("Medium priority", 6);

        List<Job> jobs = jobService.getJobsByPriority();

        assertEquals(3, jobs.size());
        assertEquals(10, jobs.get(0).getPriority());
        assertEquals(6, jobs.get(1).getPriority());
        assertEquals(2, jobs.get(2).getPriority());
    }

    @Test
    void shouldRejectInvalidPriority() {
        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> jobService.addJob("Invalid job", 15)
                );

        assertEquals(
                "Priority must be between 1 and 10",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectEmptyJobName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> jobService.addJob("   ", 5)
        );
    }

    @Test
    void getAllJobsShouldReturnDefensiveCopy() {
        jobService.addJob("Job A", 5);

        List<Job> jobs = jobService.getAllJobs();
        jobs.clear();

        assertEquals(1, jobService.size());
    }
}
