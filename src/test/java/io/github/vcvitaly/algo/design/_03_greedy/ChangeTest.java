package io.github.vcvitaly.algo.design._03_greedy;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeTest {

    @ParameterizedTest
    @MethodSource("params")
    void returnsChange(Param param) {
        assertThat(Change.minNumOfCoins(param.m)).isEqualTo(param.minNumOfCoins);
    }

    static Stream<Param> params() {
        return Stream.of(
                new Param(0, 0),
                new Param(1, 1),
                new Param(2, 2),
                new Param(28, 6)
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private int m;
        private int minNumOfCoins;
    }
}