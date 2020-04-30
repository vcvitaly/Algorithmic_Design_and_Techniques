package io.github.vcvitaly.algo.design._02_warmup;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciPartialSumTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsLastDigitOfFibonacciSum(Param param) {
        System.out.println(param);
        assertThat(
                Timeouts.getWithTimeout(1_500, TimeUnit.MILLISECONDS,
                        () -> FibonacciPartialSum.getFibonacciPartialSumFast(param.m, param.n))

        ).isEqualTo(param.lastDigitofSum);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(0, 3, 4),
                Param.of(3, 7, 1),
                Param.of(10, 10, 5),
                Param.of(10, 200, 2),
                Param.of(33_333, 10_000, 9)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private long m;
        private long n;
        private int lastDigitofSum;
    }
}