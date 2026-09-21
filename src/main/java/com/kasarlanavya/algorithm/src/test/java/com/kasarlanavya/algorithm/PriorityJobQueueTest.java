package com.kasarlanavya.algorithm;

import com.kasarlanavya.model.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityJobQueueTest {

    @Test
    void shouldProcessHighestPriorityJobFirst() {
        PriorityJobQueue queue = new PriorityJobQueue();

        queue.add(new Job(1, "Low priority", 2));
        queue.add(new Job(2, "Critical priority", 10));
        queue.add(new Job(3, "Medium priority", 6));

        Job first = queue.processNext().orElseThrow();
        Job second = queue.processNext().orElseThrow();
        Job third = queue.processNext().orElseThrow();

        assertEquals(10, first.getPriority());
        assertEquals(6, second.getPriority());
        assertEquals(2, third.getPriority());
    }

    @Test
    void shouldPeekWithoutRemovingJob() {
        PriorityJobQueue queue = new PriorityJobQueue();

        queue.add(new Job(1, "Important job", 9));

        Job job = queue.peekNext().orElseThrow();

        assertEquals(9, job.getPriority());
        assertEquals(1, queue.size());
    }

    @Test
    void shouldRemoveJobWhenProcessed() {
        PriorityJobQueue queue = new PriorityJobQueue();

        queue.add(new Job(1, "Process data", 7));

        assertEquals(1, queue.size());

        queue.processNext();

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenQueueHasNoJobs() {
        PriorityJobQueue queue = new PriorityJobQueue();

        assertTrue(queue.peekNext().isEmpty());
        assertTrue(queue.processNext().isEmpty());
    }

    @Test
    void shouldRejectNullJob() {
        PriorityJobQueue queue = new PriorityJobQueue();

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> queue.add(null)
                );

        assertEquals("Job cannot be null", exception.getMessage());
    }
}
