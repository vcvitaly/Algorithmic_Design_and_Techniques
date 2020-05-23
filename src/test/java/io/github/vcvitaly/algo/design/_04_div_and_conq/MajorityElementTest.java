package io.github.vcvitaly.algo.design._04_div_and_conq;

import io.github.vcvitaly.algo.Helper;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static io.github.vcvitaly.algo.design._04_div_and_conq.MajorityElement.MAJORITY_EXISTS;
import static io.github.vcvitaly.algo.design._04_div_and_conq.MajorityElement.MAJORITY_NOT_EXISTS;
import static org.assertj.core.api.Assertions.assertThat;

class MajorityElementTest {

    private static final int TIMES = 100;
    private static final Random RANDOM = new Random(123_456_789L);

    @ParameterizedTest
    @MethodSource("params")
    void verifiesMajority(Param param) {
        System.out.println(Helper.shortToString(param));

        runTest(param);
    }

    private void runTest(Param param) {
        assertThat(
                Timeouts.getWithTimeout(500, TimeUnit.MILLISECONDS,
                        () -> MajorityElement.getMajorityElementNaive(param.a, 0,param.a.length - 1))
        ).isEqualTo(param.output);
    }

    @Test
    void stressTest() {
        Param param = Param.of(
                IntStream.concat(
                        IntStream.concat(
                                IntStream.range(0, 30_001 * TIMES).map(i -> 987_654_321),
                                RANDOM.ints(49_999 * TIMES, 0, 1_000_000_000)
                        ),
                        IntStream.range(0, 20_000 * TIMES).map(i -> 987_654_321)
                ).toArray(), MAJORITY_EXISTS
        );

        runTest(param);
    }

    static Stream<Param> params() {

        return Stream.of(
                Param.of(new int[] {7}, MAJORITY_EXISTS),
                Param.of(new int[] {2, 3, 9, 2, 2}, MAJORITY_EXISTS),
                Param.of(new int[] {1, 2, 3, 4}, MAJORITY_NOT_EXISTS),
                Param.of(new int[] {1, 2, 3, 1}, MAJORITY_NOT_EXISTS)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int output;
    }
}