package io.github.vcvitaly.algo.design._03_greedy;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static org.assertj.core.api.Assertions.assertThat;

class DotProductTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsMaxRevenue(Param param) {
        System.out.println(param);

        assertThat(
                Timeouts.getWithTimeout(500, TimeUnit.MILLISECONDS,
                        () -> DotProduct.maxDotProduct(param.a, param.b)
                )
        ).isEqualTo(param.maxRevenue);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[] {23}, new int[] {39}, 897),
                Param.of(new int[] {1, 3, -5}, new int[] {-2, 4, 1}, 23)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int[]  b;
        private long maxRevenue;
    }
}