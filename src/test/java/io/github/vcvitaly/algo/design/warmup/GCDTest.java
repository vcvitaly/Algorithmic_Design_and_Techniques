package io.github.vcvitaly.algo.design.warmup;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GCDTest {

    @ParameterizedTest
    @MethodSource("params")
    void gcdNaive(Param param) {
        assertThat(GCD.gcdFast(param.a, param.b)).isEqualTo(param.gcd);
    }

    static Stream<Param> params() {
        return Stream.of(
                new Param(1, 1, 1),
                new Param(2, 4, 2),
                new Param(18, 35, 1),
                new Param(28851538, 1183019, 17657),
                new Param(2000000000, 1500500500, 500)
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private int a;
        private int b;
        private int gcd;
    }
}