package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrieMatchingExtendedTest {

    private static String[] SYMBOLS = new String[] {"A", "C", "G", "T"};

    private TrieMatchingExtended trieMatching = new TrieMatchingExtended();

    @ParameterizedTest
    @MethodSource("params")
    void findsPatterns(Param param) {
        System.out.println(Helper.shortToString(param));

        // Act & Assert
        assertThat(
                trieMatching.solve(param.text, param.patterns)
        ).containsExactlyElementsOf(param.postions);
    }

    @Test
    void stressTest() {
        Random random = new Random(123456789L);
        int n = 10;

        for (int times = 0; times < 100; times++) {
            String text = IntStream.rangeClosed(1, n)
                    .mapToObj(i -> getGeneSymbol(random))
                    .collect(Collectors.joining(""));

            String[] patterns = IntStream.rangeClosed(1, n / 2)
                    .mapToObj(
                            i -> IntStream.rangeClosed(1, 1 + random.nextInt(4))
                                    .mapToObj(j -> getGeneSymbol(random))
                                    .collect(Collectors.joining(""))
                    ).distinct().toArray(String[]::new);

            assertThat(
                    trieMatching.solve(text, patterns)
            ).as(String.format("Looking for patterns %s in text %s", Arrays.toString(patterns), text))
                    .containsExactlyInAnyOrderElementsOf(trieMatching.solveNaive(text, patterns));
        }
    }

    @NotNull
    private String getGeneSymbol(Random random) {
        return SYMBOLS[random.nextInt(SYMBOLS.length)];
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
                ),
                // finds all matches if one pattern is a prefix of another
                Param.of(
                        "ACT",
                        new String[] {"ACG", "A"},
                        Collections.singletonList(0)
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