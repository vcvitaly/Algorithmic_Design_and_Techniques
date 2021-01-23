package io.github.vcvitaly.algo.graphs._01_decomposition1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReachabilityTest {

    @ParameterizedTest
    @MethodSource("params")
    void checksIfThereIsAPathBetweenTwoNodes(Param param) {
        System.out.println(param);

        assertThat(
                Reachability.reach(
                        toAdj(param.countOfVerticesN, param.edges), param.x, param.y
                )
        ).isEqualTo(param.hasPathFromXtoY);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        4,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {3, 2},
                                new int[] {4, 3},
                                new int[] {1, 4}
                        },
                        1, 4, Reachability.YES
                ),
                Param.of(
                        4,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {3, 2}
                        },
                        1, 4, Reachability.NO
                )
        );
    }

    static List<Integer>[] toAdj(int n, int[][] edges) {
        List<Integer>[] adj = (List<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int x, y;
            x = edges[i][0];
            y = edges[i][1];

            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }

        return adj;
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private int x, y;
        private int hasPathFromXtoY;
    }
}