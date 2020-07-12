package io.github.vcvitaly.algo.ds._03_priority_q;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static io.github.vcvitaly.algo.ds._03_priority_q.BuildHeap.Swap;
import static org.assertj.core.api.Assertions.assertThat;

class BuildHeapTest {

    private BuildHeap buildHeap = new BuildHeap();

    @ParameterizedTest
    @MethodSource("params")
    void buildsHeap(Param param) {
        System.out.println(Helper.shortToString(param));

        // Act
        List<Swap> swaps = buildHeap.generateSwapsFast(param.a);
        System.out.println(Arrays.toString(param.a));

        // Assert
        assertThat(swaps).containsExactlyElementsOf(param.swaps);
        assertThat(param.a).isEqualTo(param.heap);
    }

    @Test
    void performanceTest() {
        int[] a = IntStream.range(0, 100_000).map(i -> i * 1_000).toArray();

        Timeouts.doWithTimeout(
                1_500, TimeUnit.SECONDS,
                () -> buildHeap.generateSwapsFast(a)
        );
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        new int[] {5, 4, 3, 2, 1},
                        new int[] {1, 2, 3, 5, 4},
                        Arrays.asList(new Swap(1, 4), new Swap(0, 1), new Swap(1, 3))
                ),
                Param.of(
                        new int[] {1, 2, 3, 4, 5},
                        new int[] {1, 2, 3, 4, 5},
                        Collections.emptyList()
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int[] heap;
        private List<Swap> swaps;
    }
}