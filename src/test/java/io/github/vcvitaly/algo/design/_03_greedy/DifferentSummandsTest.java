package io.github.vcvitaly.algo.design._03_greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class DifferentSummandsTest {

    @ParameterizedTest
    @MethodSource("params")
    void returnsOptimalValue(Param param) {
        System.out.println(param);

        assertThat(DifferentSummands.optimalSummands(param.nCandies))
                .isIn(param.summands)
                .hasSize(param.kCandiePiles);
    }

    static Stream<Param> params() {
        return Stream.of(
                new Param(6, 3,
                        Collections.singletonList(
                                Arrays.asList(1, 2, 3))
                ),
                new Param(8, 3,
                        Arrays.asList(
                                Arrays.asList(1, 2, 5),
                                Arrays.asList(1, 3, 4)
                        )
                ),
                new Param(2, 1, Collections.singletonList(
                        Collections.singletonList(2))
                )
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private int nCandies;
        private int kCandiePiles;
        private List<List<Integer>> summands;
    }
}