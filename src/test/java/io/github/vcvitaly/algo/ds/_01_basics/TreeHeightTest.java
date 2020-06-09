package io.github.vcvitaly.algo.ds._01_basics;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

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
                Timeouts.getWithTimeout(
                        1_500, TimeUnit.MILLISECONDS,
                        () -> new TreeHeight.Tree(param.nodeParents.length, param.nodeParents).computeHeightFast()
                )
        ).isEqualTo(param.maxTreeHeight);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[] {-1, 0}, 2),
                Param.of(new int[] {4, -1, 4, 1, 1}, 3),
                Param.of(new int[] {-1, 0 , 4, 0, 3}, 4)
        );
    }

    @Test
    void unbalancedTreePerformanceTest() {
        Param param = Param.of(
                IntStream.concat(
                        IntStream.concat(
                                IntStream.of(-1),
                                IntStream.rangeClosed(0, 990_996)
                        ),
                        IntStream.of(0)
                ).toArray(), 990_998
        );

        assertThat(
                new TreeHeight.Tree(param.nodeParents.length, param.nodeParents).computeHeightFast()
        ).isEqualTo(param.maxTreeHeight);
    }

    @Test
    void flatTreePerformanceTest() {
        Param param = Param.of(
                IntStream.concat(
                        IntStream.of(-1),
                        IntStream.rangeClosed(0, 990_996).map(i -> 0)
                        ).toArray(), 2
        );

        assertThat(
                new TreeHeight.Tree(param.nodeParents.length, param.nodeParents).computeHeightFast()
        ).isEqualTo(param.maxTreeHeight);
    }

    @Test
    void randomTest() {
        List<Integer> ints = IntStream.rangeClosed(0, 100_000).boxed().collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] nodeParents;
        private int maxTreeHeight;
    }

}