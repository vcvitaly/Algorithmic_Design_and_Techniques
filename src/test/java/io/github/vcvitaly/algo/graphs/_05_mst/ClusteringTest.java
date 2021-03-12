package io.github.vcvitaly.algo.graphs._05_mst;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ClusteringTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsLargestPossibleDistanceBetweenClusters(Param param) {
        System.out.println(param);

        Assertions.assertThat(
                Clustering.clustering(
                        param.x, param.y, param.k
                )
        ).isEqualTo(param.minDistanceBetweenClusters);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        new int[] {7, 4, 5, 1, 2, 5, 3, 7, 2, 4, 6, 2},
                        new int[] {6, 3, 1, 7, 7, 7, 3, 8, 8, 4, 7, 6},
                        3, 2.828427124746
                ),
                Param.of(
                        new int[] {0, 0, 1, 3, 3},
                        new int[] {0, 2, 1, 0, 2},
                        4, 5.000000000
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        public int[] x;
        public int[] y;
        private int k;
        public double minDistanceBetweenClusters;
    }
}