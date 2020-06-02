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

    public static final int UPPER_BOUND = 100_000_000;
    public static final int LOWER_BOUND = -UPPER_BOUND;

    @ParameterizedTest
    @MethodSource("params")
    void verifiesCountOfSegmentsContainingX(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(
                PointsAndSegments.fastCountSegments(param.starts, param.ends, param.points)
        ).containsExactly(param.kSegmentsContainingX);
    }

    @Test
    void performanceTest() {
        int n = 10_000;
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[n*2+100];
        int[] kSegmentsContainingX = new int[n*2+100];

        int step = 20_000;
        for (int i = 0, j = LOWER_BOUND; i < n && j < UPPER_BOUND; i++, j += step) {
            starts[i] = j;
            ends[i] = j + 10;
            points[i] = j + 3;
            kSegmentsContainingX[i] = 1;
        }
        
        for (int i = n; i < n*2; i++) {
            points[i] = 33;
            kSegmentsContainingX[i] = 0;
        }

        for (int i = n*2; i < n*2 + 100; i++) {
            points[i] = LOWER_BOUND + 5;
            kSegmentsContainingX[i] = 1;
        }

        for (int i = 0; i < 10; i++) {
            assertThat(
                    PointsAndSegments.fastCountSegments(starts, ends, points)
            ).containsExactly(kSegmentsContainingX);
        }
    }

    @Test
    void stressTest() {


    }

    /*private Param generateDataForStressTest() {
        Random r = new Random(123_456_789);

        int[] starts;
        int[] ends;
        int[] points;
        int[] kSegmentsContainingX;

        for (int i = 0; i < 100; i++) {
            starts[i] = r.nextInt(UPPER_BOUND)
        }
    }*/

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
                        new int[] {0, -3, 7, 2},
                        new int[] {5, 2, 10, 4},
                        new int[] {1, 6, 5},
                        new int[] {2, 0, 1}
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