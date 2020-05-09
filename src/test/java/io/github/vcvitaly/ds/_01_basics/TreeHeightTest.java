package io.github.vcvitaly.ds._01_basics;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class TreeHeightTest {

    @ParameterizedTest
    @MethodSource("params")
    void computesHeight(Param param) {
        String s = param.toString();
        System.out.println(
                s.substring(0, Math.min(s.length(), 100))
        );

        assertThat(
                /*Timeouts.getWithTimeout(
                        1_500, TimeUnit.MILLISECONDS,
                        () -> new tree_height.TreeHeight(param.nodeParents.length, param.nodeParents).computeHeightFast()
                )*/
                new tree_height.TreeHeight(param.nodeParents.length, param.nodeParents).computeHeightFast()
        ).isEqualTo(param.treeHeight);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[] {-1, 0}, 2),
                Param.of(new int[] {4, -1, 4, 1, 1}, 3),
                Param.of(new int[] {-1, 0 , 4, 0, 3}, 4)/*,
                Param.of(
                        IntStream.concat(
                                IntStream.concat(
                                        IntStream.of(-1),
                                        IntStream.rangeClosed(0, 99_996)
                                ),
                                IntStream.of(0)
                        ).toArray(), 99_998
                )*/
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] nodeParents;
        private int treeHeight;
    }

}