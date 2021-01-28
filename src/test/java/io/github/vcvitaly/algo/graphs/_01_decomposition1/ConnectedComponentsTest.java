package io.github.vcvitaly.algo.graphs._01_decomposition1;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectedComponentsTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsConnectedComponents(Param param) {
        System.out.println(param);

        assertThat(
                ConnectedComponents.numberOfComponents(
                        GraphTransformationHelper.edgesToAdj(param.countOfVerticesN, param.edges)
                )
        ).isEqualTo(param.countOfConnectedComponents);
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
                        1
                ),
                Param.of(
                        4,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {3, 2}
                        },
                        2
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private int countOfConnectedComponents;
    }
}