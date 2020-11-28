package io.github.vcvitaly.algo.ds._04_binary_trees;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.github.vcvitaly.algo.ds._04_binary_trees.TreeOrders.Tree;
import static org.assertj.core.api.Assertions.assertThat;

class TreeOrdersTest {

    private TreeOrders treeOrders = new TreeOrders();

    @ParameterizedTest
    @MethodSource("params")
    void performsInOrderTraversalCorrectly(Param param) {
        System.out.println(param);

        Tree tree = new Tree(param.key, param.left, param.right);

        assertThat(treeOrders.inOrder(tree))
                .isEqualTo(param.inOrderList);
    }

    @ParameterizedTest
    @MethodSource("params")
    void performsPreOrderTraversalCorrectly(Param param) {
        System.out.println(param);

        Tree tree = new Tree(param.key, param.left, param.right);

        assertThat(treeOrders.preOrder(tree))
                .isEqualTo(param.preOrderList);
    }

    @ParameterizedTest
    @MethodSource("params")
    void performsPostOrderTraversalCorrectly(Param param) {
        System.out.println(param);

        Tree tree = new Tree(param.key, param.left, param.right);

        assertThat(treeOrders.postOrder(tree))
                .isEqualTo(param.postOrderList);
    }

    @Test
    void stressTest() {
        int n = 100_000;
        int[] key = IntStream.range(0, n).toArray();
        int[] left = IntStream.range(0, n).map(i -> -1).toArray();
        int[] right = IntStream.concat(IntStream.range(1, n), IntStream.of(-1)).toArray();

        List<Integer> inOrder = IntStream.range(0, n).boxed().collect(Collectors.toList());

        assertThat(
                treeOrders.inOrder(new Tree(key, left, right))
        ).isEqualTo(inOrder);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[0], new int[0], new int[0], Collections.emptyList(), Collections.emptyList(), Collections.emptyList()),
                Param.of(
                        new int[] {4, 2, 5, 1, 3},
                        new int[] {1, 3, -1, -1, -1},
                        new int[] {2, 4, -1, -1, -1},
                        Arrays.asList(1, 2, 3, 4, 5),
                        Arrays.asList(4, 2, 1, 3, 5),
                        Arrays.asList(1, 3, 2, 5, 4)
                ),
                Param.of(
                        new int[] {0, 10, 20, 30, 40, 50, 60, 70, 80, 90},
                        new int[] {7, -1, -1, 8, 3, -1, 1, 5, -1, -1},
                        new int[] {2, -1, 6, 9, -1, -1, -1, 4, -1, -1},
                        Arrays.asList(50, 70, 80, 30, 90, 40, 0, 20, 10, 60),
                        Arrays.asList(0, 70, 50, 40, 30, 80, 90, 20, 60, 10),
                        Arrays.asList(50, 80, 90, 30, 40, 70, 10, 60, 20, 0)
                )

        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] key;
        private int[] left;
        private int[] right;
        private List<Integer> inOrderList;
        private List<Integer> preOrderList;
        private List<Integer> postOrderList;
    }
}