package io.github.vcvitaly.algo.design._04_div_and_conq;

import io.github.vcvitaly.algo.Helper;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class InversionsTest {

    @ParameterizedTest
    @MethodSource("params")
    void verifiesArrayIsSorted(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(
                Inversions.getNumberOfInversions(param.a, null, 0, param.a.length - 1)
        ).isEqualTo(param.nOfInversions);
    }

    static Stream<Param> params() {

        int end = 10_000;
        return Stream.of(
                Param.of(new int[] {7}, 0),
                Param.of(new int[] {2, 3, 9, 2, 2}, 2),
                Param.of(new int[] {3, 2, 1}, 3),
                Param.of(
                        IntStream.rangeClosed(1, end).map(i -> end + 1 - i).toArray(), end * (end - 1) / 2
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int nOfInversions;
    }
}