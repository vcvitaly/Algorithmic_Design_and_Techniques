package io.github.vcvitaly.algo.design._03_greedy;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static org.assertj.core.api.Assertions.assertThat;

class LargestNumberTest {

    @ParameterizedTest
    @MethodSource("params")
    void returnsChange(Param param) {
        System.out.println(param);

        assertThat(
                Timeouts.getWithTimeout(500, TimeUnit.MILLISECONDS,
                        () -> LargestNumber.largestNumber(intArrayToStringArray(param.a)))
        ).isEqualTo(String.valueOf(param.largestNumber));
    }

    private String[] intArrayToStringArray(int[] a) {
        return Arrays.stream(a)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
    }

    static Stream<Param> params() {
        return Stream.of(
                new Param(new int[] {22}, 22),
                new Param(new int[] {9, 4, 6, 1, 9}, 99641),
                new Param(new int[] {2, 247, 294}, 2942472)
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private int[] a;
        private int largestNumber;
    }
}