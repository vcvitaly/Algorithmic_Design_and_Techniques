package io.github.vcvitaly.algo.graphs._03_bfs;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BipartiteTest {

    @ParameterizedTest
    @MethodSource("params")
    void checksForBipartiteness(Param param) {
        System.out.println(param);

        assertThat(
                Bipartite.isBipartite(
                        GraphTransformationHelper.edgesToAdj(param.countOfVerticesN, param.edges, false)
                )
        ).isEqualTo(param.isBipartite);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        4,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {4, 1},
                                new int[] {2, 3},
                                new int[] {3, 1}
                        },
                        false
                ),
                Param.of(
                        5,
                        new int[][] {
                                new int[] {5, 2},
                                new int[] {4, 2},
                                new int[] {3, 4},
                                new int[] {1, 4}
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
        private boolean isBipartite;
    }
}