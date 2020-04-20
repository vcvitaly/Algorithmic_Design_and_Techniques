package io.github.vcvitaly.algo.design.warmup;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class LCMTest {

    @ParameterizedTest
    @MethodSource("params")
    void gcdNaive(Param param) {
        assertThat(LCM.lcmFast(param.a, param.b)).isEqualTo(param.lcm);
    }

    static Stream<Param> params() {
        return Stream.of(
                new Param(1, 1, 1),
                new Param(3, 5, 15),
                new Param(6, 8, 24),
                new Param(28851538, 1183019, 1933053046),
                new Param(1234567891, 1998765432, 2467611623987943912L)
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private int a;
        private int b;
        private long lcm;
    }
}