package io.github.vcvitaly.algo.graphs._04_weighted_paths;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import io.github.vcvitaly.algo.graphs._03_bfs.Bipartite;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsMinimumCostOfAPath(Param param) {
        System.out.println(param);

        Assertions.assertThat(
                Dijkstra.distance(
                        GraphTransformationHelper.weightedEdgesToAdj(param.countOfVerticesN, param.edges, true), param.u - 1, param.v - 1
                )
        ).isEqualTo(param.minPathWeight);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        4,
                        new int[][] {
                                new int[] {1, 2, 1},
                                new int[] {4, 1, 2},
                                new int[] {2, 3, 2},
                                new int[] {1, 3, 5}
                        },
                        1, 3, 3
                ),
                Param.of(
                        5,
                        new int[][] {
                                new int[] {1, 2, 4},
                                new int[] {1, 3, 2},
                                new int[] {2, 3, 2},
                                new int[] {3, 2, 1},
                                new int[] {2, 4, 2},
                                new int[] {3, 5, 4},
                                new int[] {5, 4, 1},
                                new int[] {2, 5, 3},
                                new int[] {3, 4, 4}
                        },
                        1, 5, 6
                ),
                Param.of(
                        3,
                        new int[][] {
                                new int[] {1, 2, 7},
                                new int[] {1, 3, 5},
                                new int[] {2, 3, 2},
                        },
                        3, 2, -1
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private int u;
        private int v;
        private int minPathWeight;
    }
}