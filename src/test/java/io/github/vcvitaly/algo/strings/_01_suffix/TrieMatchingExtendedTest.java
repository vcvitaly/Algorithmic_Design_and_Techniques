package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrieMatchingExtendedTest {

    private TrieMatchingExtended trieMatching = new TrieMatchingExtended();

    @ParameterizedTest
    @MethodSource("params")
    void constructsATrie(Param param) {
        System.out.println(Helper.shortToString(param));

        // Act & Assert
        assertThat(
                trieMatching.solve(param.text, param.patterns)
        ).containsExactlyElementsOf(param.postions);
    }


    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        "AAA",
                        new String[] {"AA"},
                        Arrays.asList(0, 1)
                ),
                Param.of(
                "ACATA",
                new String[] {"AT", "A", "AG"},
                Arrays.asList(0, 2, 4)
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private String text;
        private String[] patterns;
        private List<Integer> postions;
    }
}