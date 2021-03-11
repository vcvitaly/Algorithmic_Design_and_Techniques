package io.github.vcvitaly.algo.graphs._05_mst;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ConnectingPointsTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsMinimalTotalLengthOfSegments(Param param) {
        System.out.println(param);

        Assertions.assertThat(
                ConnectingPoints.minimumDistance(
                        param.x, param.y
                )
        ).isEqualTo(param.minDistance);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        new int[] {0, 0, 1, 1},
                        new int[] {0, 1, 0, 1},
                        3.0000000d
                ),
                Param.of(
                        new int[] {0, 0, 1, 3, 3},
                        new int[] {0, 2, 1, 0, 2},
                        7.064495102
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        public int[] x;
        public int[] y;
        public double minDistance;
    }
}