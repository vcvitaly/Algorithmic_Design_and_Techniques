package io.github.vcvitaly.algo.graphs._01_decomposition1;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
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
                        GraphTransformationHelper.edgesToAdj(param.countOfVerticesN, param.edges, false), --param.x, --param.y
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

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private int x, y;
        private int hasPathFromXtoY;
    }
}