package io.github.vcvitaly.algo.design._04_div_and_conq;

import io.github.vcvitaly.algo.Helper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ClosestTest {

    @ParameterizedTest
    @MethodSource("params")
    void findMinimumDistance(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(
                Closest.minimalDistance(param.x, param.y)
        ).isEqualTo(param.minDist);
    }


    static Stream<Param> params() {

        int[] a = IntStream.range(0, 10_000).map(i -> 123_456_789).toArray();
        return Stream.of(
                Param.of(
                        new int[] {0, 3},
                        new int[] {0, 4},
                        5.0
                ),
                Param.of(
                        new int[] {7, 1, 4, 7},
                        new int[] {7, 100, 8, 7},
                        0.0
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] x;
        private int[] y;
        double minDist;
    }
}