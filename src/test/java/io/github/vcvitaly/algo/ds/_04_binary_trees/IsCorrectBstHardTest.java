package io.github.vcvitaly.algo.ds._04_binary_trees;

import io.github.vcvitaly.algo.Helper;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class IsCorrectBstHardTest {

    @ParameterizedTest
    @MethodSource("params")
    void verifiesBstCorrectness(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(IsCorrectBstHard.treeOf(param.key, param.left, param.right).isBinarySearchTree())
                .isEqualTo(param.isCorrectBst);
    }


    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[] {}, new int[] {}, new int[] {}, true),
                Param.of(
                        new int[] {Integer.MAX_VALUE},
                        new int[] {-1},
                        new int[] {-1},
                        true
                ),
                Param.of(
                        new int[] {2, 1, 3},
                        new int[] {1, -1, -1},
                        new int[] {2, -1, -1},
                        true
                ),
                Param.of(
                        new int[] {1, 2, 3},
                        new int[] {1, -1, -1},
                        new int[] {2, -1, -1},
                        false
                ),
                Param.of(
                        new int[] {2, 1, 2},
                        new int[] {1, -1, -1},
                        new int[] {2, -1, -1},
                        true
                ),
                Param.of(
                        new int[] {2, 2, 3},
                        new int[] {1, -1, -1},
                        new int[] {2, -1, -1},
                        false
                ),
                Param.of(
                        new int[] {1, 2, 3, 4, 5},
                        new int[] {-1, -1, -1, -1, -1},
                        new int[] {1, 2, 3, 4, -1},
                        true
                ),
                Param.of(
                        new int[] {4, 2, 6, 1, 3, 5, 7},
                        new int[] {1, 3, 5, -1, -1, -1, -1},
                        new int[] {2, 4, 6, -1, -1, -1, -1},
                        true
                ),
                Param.of(
                        new int[] {4, 2, 1, 5},
                        new int[] {1, 2, -1, -1},
                        new int[] {-1, 3, -1, -1},
                        false
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] key;
        private int[] left;
        private int[] right;
        private boolean isCorrectBst;
    }
}