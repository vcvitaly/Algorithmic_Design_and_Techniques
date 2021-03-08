package io.github.vcvitaly.algo.graphs._04_weighted_paths;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NegativeCycleTest {

    @ParameterizedTest
    @MethodSource("params")
    void checksForACycleOfANegativeWeight(Param param) {
        System.out.println(param);

        Assertions.assertThat(
                NegativeCycle.hasNegativeCycle(
                        GraphTransformationHelper.weightedEdgesToAdj(param.countOfVerticesN, param.edges, true)
                )
        ).isEqualTo(param.hasNegativeCycle);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        4,
                        new int[][] {
                                new int[] {1, 2, -5},
                                new int[] {4, 1, 2},
                                new int[] {2, 3, 2},
                                new int[] {3, 1, 1}
                        },
                        true
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private boolean hasNegativeCycle;
    }
}