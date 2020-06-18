package io.github.vcvitaly.algo.ds._03_priority_q;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.github.vcvitaly.algo.ds._03_priority_q.BuildHeap.Swap;
import static org.assertj.core.api.Assertions.assertThat;

class BuildHeapTest {

    private BuildHeap buildHeap = new BuildHeap();

    @ParameterizedTest
    @MethodSource("params")
    void buildsHeap(Param param) {
        System.out.println(Helper.shortToString(param));

        // Act
        List<Swap> swaps = buildHeap.generateSwapsNaive(param.a);
        System.out.println(Arrays.toString(param.a));

        // Assert
        assertThat(param.a).containsExactly(param.heap);
        assertThat(swaps)
                .containsExactlyElementsOf(param.swaps);
    }

    @Test
    void performanceTest() {
        Random r = new Random();
        int[] a = IntStream.range(0, 100_000).map(i -> r.nextInt(1_000_000_000)).distinct().toArray();
        int[] aSorted = Arrays.copyOf(a, a.length);
        Arrays.sort(aSorted);

        List<Swap> swaps = buildHeap.generateSwapsNaive(a);

        assertThat(a).containsExactly(aSorted);
        assertThat(swaps).isNotEmpty();
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