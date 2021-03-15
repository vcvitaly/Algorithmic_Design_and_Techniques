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

class SuffixTreeTest {

    private final SuffixTree suffixTree = new SuffixTree();

    @ParameterizedTest
    @MethodSource("params")
    void constructsSuffixTree(Param param) {
        System.out.println(Helper.shortToString(param));

        // Act & Assert
        assertThat(
                suffixTree.computeSuffixTreeEdges(param.text)
        ).containsExactlyInAnyOrderElementsOf(param.edges);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        "A$",
                        Arrays.asList("$", "A$")
                )/*,
                Param.of(
                        "ACA$",
                        Arrays.asList("$", "$", "A", "CA$", "CA$")
                ),
                Param.of(
                        "ATAAATG$",
                        Arrays.asList("AAATG$", "G$", "T", "ATG$", "TG$", "A", "A", "AAATG$", "G$", "T", "G$", "$")
                )*/
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private String text;
        private List<String> edges;
    }
}