package io.github.vcvitaly.algo.graphs._04_weighted_paths;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static io.github.vcvitaly.algo.graphs._04_weighted_paths.ShortestPaths.NO_PATH_AT_ALL;
import static io.github.vcvitaly.algo.graphs._04_weighted_paths.ShortestPaths.NO_SHORTEST_PATH;

class ShortestPathsTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsShortestPathsBetweenSAndU(Param param) {
        System.out.println(param);

        Assertions.assertThat(
                ShortestPaths.shortestPaths(
                        GraphTransformationHelper.edgesToAdj(param.countOfVerticesN, param.edges, true), param.costs, param.u
                )
        ).isEqualTo(param.hasNegativeCycle);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        4,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {2, 3},
                                new int[] {1, 3},
                                new int[] {3, 5},
                                new int[] {5, 4},
                                new int[] {4, 3},
                                new int[] {6, 1}
                        },
                        new ArrayList<Integer>(Arrays.asList(10, 5, 100, 7, 10, -18, -1)),
                        1,
                        Arrays.asList("0", "10", NO_SHORTEST_PATH, NO_SHORTEST_PATH, NO_SHORTEST_PATH, NO_PATH_AT_ALL)
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private ArrayList<Integer> costs;
        private int u;
        private List<String> answers;
    }
}