package io.github.vcvitaly.algo.graphs._05_mst;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ConnectingPointsTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsMinimalTotalLengthOfSegments(Param param) {
        System.out.println(param);

        double answer = ConnectingPoints.minimumDistance(
                param.x, param.y
        );

        Assertions.assertThat(
                BigDecimal.valueOf(answer).setScale(7, RoundingMode.HALF_UP).doubleValue()
        ).isEqualTo(
                BigDecimal.valueOf(param.minDistance).setScale(7, RoundingMode.HALF_UP).doubleValue()
        );
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
                ),
                Param.of(
                        new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8},
                        new int[] {0, 1, -3, 6, -4, 8, -7, 12, -11},
                        28.019247933
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