package io.github.vcvitaly.algo.design._05_dynamic1;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class LCS2Test {

    @ParameterizedTest
    @MethodSource("params")
    void findsLongestCommonSubsequenceOfTwoSequences(Param param) {
        System.out.println(param);

        assertThat(LCS2.lcs2(param.a, param.b))
                .isEqualTo(param.lcsLength);
    }

    static Stream<Param> params() {
        return Stream.of(
                // my cases
                Param.of(new int[] {}, new int[] {}, 0),
                Param.of(new int[] {0}, new int[] {1}, 0),
                Param.of(new int[] {1}, new int[] {1}, 1),
                // cases provided with a task
                Param.of(new int[] {2, 7, 5}, new int[] {2, 5}, 2),
                Param.of(new int[] {7}, new int[] {1, 2, 3, 4}, 0),
                Param.of(new int[] {2, 7, 8, 3}, new int[] {5, 2, 8, 7}, 2)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int[] b;
        private int lcsLength;
    }
}