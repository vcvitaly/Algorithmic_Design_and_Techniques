package io.github.vcvitaly.algo.design._04_div_and_conq;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsIndex(Param param) throws InterruptedException {
        String s = param.toString();
        System.out.println(
                s.substring(
                        0, Math.min(s.length(), 100)
                )
        );

        runTest(param);
    }

    @Nullable
    private void runTest(Param param) {
        for (int i = 0; i < param.b.length; i++) {
            System.out.println(String.format("Searching for element %d", param.b[i]));
            assertThat(BinarySearch.binarySearch(param.a, param.b[i]))
                    .isEqualTo(param.indexOfBinA[i]);
        }
    }

    @Test
    void stressTest() {
        Param param = Param.of(
                new int[]{1, 2, 333, 111_222_333, 987_654_321},
                new int[]{1, 111_222_333, 987_654_321, 2, 22},
                new int[]{0, 3, 4, 1, -1}
        );

        Timeouts.getWithTimeout(
                1_500, TimeUnit.MILLISECONDS,
                () -> {
                    IntStream.range(0, 10_000)
                            .forEach(i -> runTest(param));
                    return null;
                }
        );
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[] {7}, new int[] {7}, new int[] {0}),
                Param.of(new int[] {3, 5}, new int[] {5, 4}, new int[] {1, -1}),
                Param.of(new int[] {1, 5, 8, 12, 13}, new int[] {8, 1, 23, 1, 11}, new int[] {2, 0, -1, 0, -1})
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int[] b;
        private int[] indexOfBinA;
    }
}