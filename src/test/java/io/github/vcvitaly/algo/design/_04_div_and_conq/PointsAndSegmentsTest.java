package io.github.vcvitaly.algo.design._04_div_and_conq;

import io.github.vcvitaly.algo.Helper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PointsAndSegmentsTest {

    @ParameterizedTest
    @MethodSource("params")
    void verifiesCountOfSegmentsContainingX(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(
                PointsAndSegments.fastCountSegments(param.starts, param.ends, param.points)
        ).containsExactly(param.kSegmentsContainingX);
    }

    @Test
    void stressTest() {
        int n = 10_000;
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[n*3];
        int[] kSegmentsContainingX = new int[n*3];

        int lowerBound = -100_000_000;
        int upperBound = 100_000_000;
        int step = 20_000;
        for (int i = 0, j = lowerBound; i < n && j < upperBound; i++, j += step) {
            starts[i] = j;
            ends[i] = j + 10;
            points[i] = j + 3;
            points[i + n] = j + 5;
            kSegmentsContainingX[i] = 1;
            kSegmentsContainingX[i + n] = 1;
        }
        
        for (int i = n*2; i < n*3; i++) {
            points[i] = 33;
            kSegmentsContainingX[i] = 0;
        }

        assertThat(
                PointsAndSegments.fastCountSegments(starts, ends, points)
        ).containsExactly(kSegmentsContainingX);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        new int[] {0, 7},
                        new int[] {5, 10},
                        new int[] {1, 6, 11},
                        new int[] {1, 0, 0}
                ),
                Param.of(
                        new int[] {-10},
                        new int[] {10},
                        new int[] {-100, 100, 0},
                        new int[] {0, 0 ,1}
                ),
                Param.of(
                        new int[] {0, -3, 7},
                        new int[] {5, 2, 10},
                        new int[] {1, 6},
                        new int[] {2, 0}
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] starts;
        private int[] ends;
        private int[] points;
        private int[] kSegmentsContainingX;
    }

}