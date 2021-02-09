package io.github.vcvitaly.algo.graphs._02_decomposition2;

import io.github.vcvitaly.algo.graphs.GraphTransformationHelper;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ToposortTest {

    @Test
    void findsOneOfThePossibleOrderingsSample1() {
        int[][] edges = {
                new int[] {1, 2},
                new int[] {4, 1},
                new int[] {3, 1}
        };
        int countOfVerticesN = 4;

        List<Integer> ordering = Toposort.toposort(
                GraphTransformationHelper.directedEdgesToAdj(countOfVerticesN, edges)
        );

        System.out.println(ordering);

        String orderdingAsString = getAsString(ordering);

        assertThat(
                orderdingAsString.contains("412") && orderdingAsString.contains("3") ||
                        orderdingAsString.contains("312") && orderdingAsString.contains("4")
        );
    }

    @NotNull
    private String getAsString(List<Integer> list) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(""));
    }

    @Test
    void findsOneOfThePossibleOrderingsSample2() {
        int[][] edges = {
                new int[]{3, 1}
        };
        int countOfVerticesN = 4;

        List<Integer> ordering = Toposort.toposort(
                GraphTransformationHelper.directedEdgesToAdj(countOfVerticesN, edges)
        );

        System.out.println(ordering);

        assertThat(ordering)
                .containsSequence(3, 1).contains(2, 4);
    }

    @Test
    void findsOneOfThePossibleOrderingsSample3() {
        int[][] edges = {
                new int[] {1, 2},
                new int[] {2, 3},
                new int[] {1, 3},
                new int[] {3, 4},
                new int[] {1, 4},
                new int[] {2, 5},
                new int[] {3, 5}
        };
        int countOfVerticesN = 5;

        List<Integer> ordering = Toposort.toposort(
                GraphTransformationHelper.directedEdgesToAdj(countOfVerticesN, edges)
        );

        System.out.println(ordering);

        assertThat(ordering)
                .containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }

}
