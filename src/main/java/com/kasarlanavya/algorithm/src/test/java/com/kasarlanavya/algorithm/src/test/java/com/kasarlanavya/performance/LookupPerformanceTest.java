package com.kasarlanavya.performance;

import com.kasarlanavya.model.Job;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LookupPerformanceTest {

    @Test
    void compareListAndHashMapLookup() {

        final int jobCount = 100_000;

        List<Job> jobList = new ArrayList<>();
        Map<Integer, Job> jobMap = new HashMap<>();

        for (int i = 1; i <= jobCount; i++) {
            Job job = new Job(
                    i,
                    "Job " + i,
                    (i % 10) + 1
            );

            jobList.add(job);
            jobMap.put(job.getId(), job);
        }

        int targetId = jobCount;

        long listStart = System.nanoTime();

        Job listResult = null;

        for (Job job : jobList) {
            if (job.getId() == targetId) {
                listResult = job;
                break;
            }
        }

        long listDuration =
                System.nanoTime() - listStart;

        long mapStart = System.nanoTime();

        Job mapResult = jobMap.get(targetId);

        long mapDuration =
                System.nanoTime() - mapStart;

        assertEquals(targetId, listResult.getId());
        assertEquals(targetId, mapResult.getId());

        System.out.println(
                "ArrayList lookup: " +
                listDuration +
                " ns"
        );

        System.out.println(
                "HashMap lookup: " +
                mapDuration +
                " ns"
        );
    }
}
