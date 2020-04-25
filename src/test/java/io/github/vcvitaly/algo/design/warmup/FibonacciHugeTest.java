package io.github.vcvitaly.algo.design.warmup;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciHugeTest {

    static class FibonacciNmodMTest {
        @ParameterizedTest
        @MethodSource("paramsForFibonacciHuge")
        void findsFnModMforHugeNumbers(Param param) {
            System.out.println(param);
            assertThat(
                    Timeouts.getWithTimeout(1_500, TimeUnit.MILLISECONDS, () -> FibonacciHuge.getFibonacciHugeFast(param.n, param.m))
            ).isEqualTo(param.fNmodM);
        }

        static Stream<Param> paramsForFibonacciHuge() {
            return Stream.of(
                    new Param(0, 3, 0),
                    new Param(10, 2, 1),
                    new Param(10, 3, 1),
                    new Param(45, 2, 0),
                    new Param(239, 1000, 161),
                    new Param(2816213588L, 239, 151),
                    new Param(100, 100_000, 15_075)
            );
        }

        @Data
        @AllArgsConstructor
        private static class Param {
            private long n;
            private long m;
            private long fNmodM;
        }
    }

    static class PizanoPeriodTest {

        @ParameterizedTest
        @MethodSource("paramsForPizanoPeriod")
        void findsPisanoPeriod(Param param) {
            System.out.println(param);
            assertThat(
                    Timeouts.getWithTimeout(1_500, TimeUnit.MILLISECONDS, () -> FibonacciHuge.getPizanoPeriod(param.m))
            ).isEqualTo(param.mod);
        }

        static Stream<Param> paramsForPizanoPeriod() {
            return Stream.of(
                    new Param(2,3),
                    new Param(3, 8),
                    new Param(10, 60),
                    new Param(125, 500),
                    new Param(132, 120),
                    new Param(1_000, 1_500),
                    new Param(10_000, 15_000),
                    new Param(100_000, 150_000)
            );
        }

        @Data
        @AllArgsConstructor
        private static class Param {
            private int m;
            private long mod;
        }
    }


}