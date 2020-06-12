package io.github.vcvitaly.algo.design._04_div_and_conq;

import io.github.vcvitaly.algo.Helper;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;


class SortingTest {

    @ParameterizedTest
    @MethodSource("params")
    void verifiesArrayIsSorted(Param param) {
        System.out.println(Helper.shortToString(param));
        // Act
        Sorting.randomizedQuickSort(param.a, 0, param.a.length - 1);
        // Assert
        assertThat(param.a).isEqualTo(param.aSorted);
    }

    static Stream<Param> params() {

        int[] a = IntStream.range(0, 10_000).map(i -> 123_456_789).toArray();
        return Stream.of(
                Param.of(new int[] {7}, new int[] {7}),
                Param.of(new int[] {2, 3, 9, 2, 2}, new int[] {2, 2, 2, 3, 9}),
                Param.of(a, a)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int[] aSorted;
    }
}