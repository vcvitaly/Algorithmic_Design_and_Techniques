package io.github.vcvitaly.algo.design._06_dynamic2;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnapsackTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsEditDistance(Param param) {
        System.out.println(param);

        assertThat(Knapsack.optimalWeightNaive(param.maxWeight, param.barWeights)).isEqualTo(param.maxWeightThatFits);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(10, new int[] {1, 4, 8}, 9)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int maxWeight;
        private int[] barWeights;
        private int maxWeightThatFits;
    }

}