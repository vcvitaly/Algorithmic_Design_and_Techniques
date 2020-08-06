package io.github.vcvitaly.algo.ds._04_binary_trees;

import io.github.vcvitaly.algo.Helper;
import io.github.vcvitaly.algo.ds._04_binary_trees.IsCorrectBst.BinaryTree.Node;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;


class IsCorrectBstTest {

    @ParameterizedTest
    @MethodSource("params")
    void verifiesBstCorrectness(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(new IsCorrectBst.BinaryTree(param.nodes).isBinarySearchTree())
                .isEqualTo(param.isCorrectBst);
    }


    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        new Node[] {new Node(2, 1, 2), new Node(1, -1, -1), new Node(3, -1, -1)},
                        true
                ),
                Param.of(
                        new Node[] {new Node(1, 1, 2), new Node(2, -1, -1), new Node(3, -1, -1)},
                        false
                ),
                Param.of(new Node[] {}, true),
                Param.of(
                        new Node[]{
                                new Node(1, -1, 1),
                                new Node(2, -1, 2),
                                new Node(3, -1, 3),
                                new Node(4, -1, 4),
                                new Node(5, -1, -1)
                        }, true),
                Param.of(
                        new Node[] {
                                new Node(4, 1, 2),
                                new Node(2, 3, 4),
                                new Node(6, 5, 6),
                                new Node(1, -1, -1),
                                new Node(3, -1, -1),
                                new Node(5, -1, -1),
                                new Node(7, -1, -1)
                        }, true),
                Param.of(
                        new Node[]{
                                new Node(4, 1, -1),
                                new Node(2, 2, 3),
                                new Node(1, -1, -1),
                                new Node(5, -1, -1)
                        }, false)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private Node[] nodes;
        private boolean isCorrectBst;
    }
}