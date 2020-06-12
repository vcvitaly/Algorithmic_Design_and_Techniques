package io.github.vcvitaly.algo.design._03_greedy;

import com.google.common.base.Stopwatch;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class DifferentSummandsTest {

    @ParameterizedTest
    @MethodSource("params")
    void returnsOptimalValue(Param param) {
        String s = param.toString();
        System.out.println(
                s.substring(
                        0, Math.min(s.length(), 500)
                )
        );

        assertThat(DifferentSummands.optimalSummands(param.nCandies))
                .isIn(param.summands)
                .hasSize(param.kCandiePiles);
    }

    @Test
    void stressTest() throws InterruptedException {
        Random random = new Random(123456789L);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Function<Integer, Callable<Void>> callableFactory = i -> () -> {
            int n = random.nextInt(1_000_000_000);
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<Integer> summands = DifferentSummands.optimalSummands(n);
            stopwatch.stop();
            System.out.println(String.format("i = %d, n = %d, time taken: %dms", i, n, stopwatch.elapsed(TimeUnit.MILLISECONDS)));
            assertThat(
                    summands.stream().mapToInt(Integer::intValue).sum()
            ).isEqualTo(n);

            return null;
        };

        List<Callable<Void>> callables = IntStream.rangeClosed(1, 10).mapToObj(callableFactory::apply).collect(Collectors.toList());

        executorService.invokeAll(callables);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(6, 3,
                        Collections.singletonList(
                                Arrays.asList(1, 2, 3))
                ),
                Param.of(8, 3,
                        Arrays.asList(
                                Arrays.asList(1, 2, 5),
                                Arrays.asList(1, 3, 4)
                        )
                ),
                Param.of(5, 2,
                        Collections.singletonList(
                                Arrays.asList(1, 4))
                ),
                Param.of(2, 1, Collections.singletonList(
                            Collections.singletonList(2))
                ),
                Param.of(IntStream.rangeClosed(1, 30_000).reduce(0, Integer::sum) + 33_333,
                        30_001,
                        Collections.singletonList(
                            IntStream
                                    .concat(IntStream.rangeClosed(1, 30_000), IntStream.of(33_333))
                                    .boxed()
                                    .collect(Collectors.toList())
                        )
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @ToString(exclude = {"summands"})
    private static class Param {
        private int nCandies;
        private int kCandiePiles;
        private List<List<Integer>> summands;
    }
}