package io.github.vcvitaly.algo.design._03_greedy;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class FractionalKnapsackTest {

    @ParameterizedTest
    @MethodSource("params")
    void returnsOptimalValue(Param param) {
        assertThat(
                FractionalKnapsack.getOptimalValue(param.capacityW, param.values, param.weights)
        ).isEqualTo(param.maxValue);
    }

    static Stream<Param> params() {
        return Stream.of(
                new Param(3, 50, new int[] {60, 100, 120}, new int[] {20, 50, 30}, 180.0000),
                new Param(1, 10, new int[] {500}, new int[] {30}, 166.6667),
                new Param(1, 10, new int[] {100}, new int[] {10}, 100)
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private int itemsN;
        private int capacityW;
        private int[] values;
        private int[] weights;
        private double maxValue;
    }
}