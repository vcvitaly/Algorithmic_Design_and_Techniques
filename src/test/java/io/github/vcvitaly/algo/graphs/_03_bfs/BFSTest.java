package io.github.vcvitaly.algo.graphs._03_bfs;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BFSTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsMinDistanceBetweenUAndV(Param param) {
        System.out.println(param);

        assertThat(
                BFS.distance(
                        GraphTransformationHelper.undirectedEdgesToAdj(param.countOfVerticesN, param.edges), param.u - 1, param.v - 1
                )
        ).isEqualTo(param.minDistance);
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
                        2, 4, 2
                ),
                Param.of(
                        5,
                        new int[][] {
                                new int[] {5, 2},
                                new int[] {1, 3},
                                new int[] {3, 4},
                                new int[] {1, 4}
                        },
                        3, 5, BFS.NONE
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
        private int minDistance;
    }
}