package io.github.vcvitaly.algo.design.warmup;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciSumLastDigitTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsLastDigitOfFibonacciSum(Param param) {
        System.out.println(param);
        assertThat(
                Timeouts.getWithTimeout(1_500, TimeUnit.MILLISECONDS, () -> FibonacciSumLastDigit.getFibonacciSumFast(param.n))

        ).isEqualTo(param.s);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(0, 0),
                Param.of(2, 2),
                Param.of(3, 4),
                Param.of(10, 3),
                Param.of(100, 5),
                Param.of(1234, 1),
                Param.of(10_000, 5),
                Param.of(614_162_383_528L, 9)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private long n;
        private int s;
    }
}