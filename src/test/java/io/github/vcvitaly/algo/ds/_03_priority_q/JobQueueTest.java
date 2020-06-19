package io.github.vcvitaly.algo.ds._03_priority_q;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.github.vcvitaly.algo.ds._03_priority_q.JobQueue.Job;
import static io.github.vcvitaly.algo.ds._03_priority_q.JobQueue.ProcessingEvent;
import static org.assertj.core.api.Assertions.assertThat;

class JobQueueTest {

    private JobQueue jobQueue = new JobQueue();

    @ParameterizedTest
    @MethodSource("params")
    void assignsJobs(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(jobQueue.assignJobsNaive(param.numWorkers, param.jobs))
                .containsExactly(param.processingEvents);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        2,
                        Arrays.stream(new int[]{1, 2, 3, 5, 4})
                                .mapToObj(Job::new)
                                .toArray(Job[]::new),
                        new ProcessingEvent[]{
                                new ProcessingEvent(0, 0),
                                new ProcessingEvent(1, 0),
                                new ProcessingEvent(0, 1),
                                new ProcessingEvent(1, 2),
                                new ProcessingEvent(0, 4)
                        }
                ),
                Param.of(
                        4,
                        IntStream.rangeClosed(1, 20)
                                .map(i -> 1)
                                .mapToObj(Job::new)
                                .toArray(Job[]::new),
                        new ProcessingEvent[]{
                                new ProcessingEvent(0, 0),
                                new ProcessingEvent(1, 0),
                                new ProcessingEvent(2, 0),
                                new ProcessingEvent(3, 0),
                                new ProcessingEvent(0, 1),
                                new ProcessingEvent(1, 1),
                                new ProcessingEvent(2, 1),
                                new ProcessingEvent(3, 1),
                                new ProcessingEvent(0, 2),
                                new ProcessingEvent(1, 2),
                                new ProcessingEvent(2, 2),
                                new ProcessingEvent(3, 2),
                                new ProcessingEvent(0, 3),
                                new ProcessingEvent(1, 3),
                                new ProcessingEvent(2, 3),
                                new ProcessingEvent(3, 3),
                                new ProcessingEvent(0, 4),
                                new ProcessingEvent(1, 4),
                                new ProcessingEvent(2, 4),
                                new ProcessingEvent(3, 4),
                        }
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int numWorkers;
        private Job[] jobs;
        private ProcessingEvent[] processingEvents;
    }

}