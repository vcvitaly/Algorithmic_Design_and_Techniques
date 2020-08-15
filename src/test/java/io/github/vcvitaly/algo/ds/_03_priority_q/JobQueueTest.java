package io.github.vcvitaly.algo.ds._03_priority_q;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.github.vcvitaly.algo.ds._03_priority_q.JobQueue.Heap;
import static io.github.vcvitaly.algo.ds._03_priority_q.JobQueue.Job;
import static io.github.vcvitaly.algo.ds._03_priority_q.JobQueue.ProcessingEvent;
import static io.github.vcvitaly.algo.ds._03_priority_q.JobQueue.ThreadInfo;
import static org.assertj.core.api.Assertions.assertThat;

class JobQueueTest {

    static class JobQueueMainTest {

        private JobQueue jobQueue = new JobQueue(null, null);

        @ParameterizedTest
        @MethodSource("params")
        void assignsJobs(Param param) {
            System.out.println(Helper.shortToString(param));

            assertThat(jobQueue.assignJobsFast(param.numWorkers, param.jobs))
                    .containsExactly(param.processingEvents);
        }

        @Test
        void performanceTest() {
            int taskCount = 100_000; // (int) Math.pow(2, 5);
            int numWorkers = 1_000;
            Param param = new Param(
                    numWorkers,
                    IntStream.range(0, taskCount)
                            .map(i -> 1)
                            .mapToObj(Job::new)
                            .toArray(Job[]::new),
                    IntStream.range(0, taskCount / numWorkers)
                            .mapToObj(i -> IntStream.range(0, numWorkers)
                                    .mapToObj(j -> new ProcessingEvent(j, i))
                                    .collect(Collectors.toList()))
                            .flatMap(List::stream).toArray(ProcessingEvent[]::new)
            );

            assertThat(jobQueue.assignJobsFast(param.numWorkers, param.jobs))
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
                            IntStream.rangeClosed(1, 10)
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

    static class HeapTest {

        Heap<ThreadInfo> heap = new Heap<>();

        @Test
        void insertsOneNode() {
            heap.insert(new ThreadInfo(0, 0));

            assertThat(heap.peekMax()).isEqualTo(new ThreadInfo(0, 0));
        }

        @Test
        void insertsTwoNodes() {
            heap.insert(new ThreadInfo(0, 0));
            heap.insert(new ThreadInfo(1, 0));

            assertThat(heap.peekMax()).isEqualTo(new ThreadInfo(0, 0));
        }

        @Test
        void insertsMultipleNodesForLoop() {
            ThreadInfo expected = new ThreadInfo(0, 0);

            for (int i = 0; i < 4; i++) {
                heap.insert(new ThreadInfo(i, 0));

                assertThat(heap.peekMax()).isEqualTo(expected);
            }
        }

        @Test
        void insertsMultipleNodes() {
            for (int i = 0; i < 4; i++) {
                heap.insert(new ThreadInfo(i, 0));
            }

            int[] expectedThreadIndexes = {1, 2, 3, 0, 1, 2, 3, 0};
            for (int i = 0; i < 8; i++) {
                ThreadInfo threadInfo = heap.extractMax();
                assertThat(heap.peekMax().threadIndex).isEqualTo(expectedThreadIndexes[i]);
                threadInfo.nextFreeTime++;
                heap.insert(threadInfo);
            }
        }

        private void incrementNextTimeAndReinsert(Heap<ThreadInfo> heap) {
            ThreadInfo max = heap.extractMax();
            max.nextFreeTime++;
            heap.insert(max);
        }
    }

    static class ThreadInfoTest {

        @Test
        void comparesThreads_smallerIndexWins() {
            assertThat(new ThreadInfo(0, 0)).isGreaterThan(new ThreadInfo(1, 0));
        }

        @Test
        void comparesThreads_smallerNextFreeTimeWins() {
            assertThat(new ThreadInfo(1, 0)).isGreaterThan(new ThreadInfo(0, 1));
        }
    }
}