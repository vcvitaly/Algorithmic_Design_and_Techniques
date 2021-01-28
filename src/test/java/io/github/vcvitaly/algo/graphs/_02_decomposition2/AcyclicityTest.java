package io.github.vcvitaly.algo.graphs._02_decomposition2;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class AcyclicityTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsOptimalKnapsackFill(Param param) {
        System.out.println(param);

        assertThat(
                Acyclicity.acyclic(
                        GraphTransformationHelper.edgesToAdj(param.countOfVerticesN, param.edges)
                )
        ).isEqualTo(param.isAcyclic);
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
                        Acyclicity.YES
                ),
                Param.of(
                        5,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {2, 3},
                                new int[] {1, 3},
                                new int[] {3, 4},
                                new int[] {1, 4},
                                new int[] {2, 5},
                                new int[] {3, 5}
                        },
                        Acyclicity.NO
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private int isAcyclic;
    }
}