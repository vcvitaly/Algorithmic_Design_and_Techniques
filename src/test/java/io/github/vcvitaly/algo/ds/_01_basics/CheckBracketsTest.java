package io.github.vcvitaly.algo.ds._01_basics;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class CheckBracketsTest {

    private static final String SUCCESS = "Success";

    @ParameterizedTest
    @MethodSource("params")
    void checksForBracketsCorrectness(Param param) {
        System.out.println(param);
        assertThat(CheckBrackets.checkBrackets(param.input)).isEqualTo(param.output);
    }

    static Stream<Param> params() {
        return Stream.of(
                // If the code in S uses brackets correctly, output “Success" (without the quotes).
                Param.of("[]", SUCCESS),
                Param.of("{}[]", SUCCESS),
                Param.of("[()]", SUCCESS),
                Param.of("(())", SUCCESS),
                Param.of("{[]}()", SUCCESS),
                Param.of("foo(bar)", SUCCESS),
                // Otherwise, output the 1-based index of the first unmatched closing bracket
                Param.of("}", "1"),
                Param.of("{[}", "3"),
                Param.of("foo(bar[i)", "10"),
                // if there are no unmatched closing output the 1-based index of the first unmatched opening bracket
                Param.of("{", "1"),
                Param.of("(([]", "1")
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private String input;
        private String output;
    }
}