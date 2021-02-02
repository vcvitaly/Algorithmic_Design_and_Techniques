package io.github.vcvitaly.algo.graphs._02_decomposition2;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class ToposortTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsTopologicalOrdering(Param param) {
        System.out.println(param);

        assertOrdering(param);
    }

    private void assertOrdering(Param param) {
        assertThat(
                Toposort.toposort(
                            GraphTransformationHelper.directedEdgesToAdj(param.countOfVerticesN, param.edges)
                        )
        ).containsExactlyElementsOf(param.ordering);
    }

    @Test
    void findsOneOfThePossibleOrderings() {
        int[][] edges = {
                new int[]{3, 1}
        };
        int countOfVerticesN = 4;

        ArrayList<Integer> ordering = Toposort.toposort(
                GraphTransformationHelper.directedEdgesToAdj(countOfVerticesN, edges)
        );

        assertThat(ordering)
                .containsSubsequence(3, 1).contains(2, 4);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(4,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {4, 1},
                                new int[] {3, 1}
                        },
                        Arrays.asList(4, 3, 1, 2)
                ),
                Param.of(4,
                        new int[][] {
                                new int[] {1, 2},
                                new int[] {2, 3},
                                new int[] {1, 3},
                                new int[] {3, 4},
                                new int[] {1, 4},
                                new int[] {2, 5},
                                new int[] {3, 5}
                        },
                        Arrays.asList(5, 4, 3, 2, 1)
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        int countOfVerticesN;
        private int[][] edges;
        private List<Integer> ordering;
    }
}
