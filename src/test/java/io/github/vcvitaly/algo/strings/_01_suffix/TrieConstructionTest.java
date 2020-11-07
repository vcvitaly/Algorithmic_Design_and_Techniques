package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.Helper;
import io.github.vcvitaly.algo.strings._01_suffix.common.Edge;
import io.github.vcvitaly.algo.strings._01_suffix.common.PatternTrieBuilder;
import io.github.vcvitaly.algo.strings._01_suffix.common.TrieNode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrieConstructionTest {

    private TrieConstruction trieConstruction = new TrieConstruction(new PatternTrieBuilder());

    @ParameterizedTest
    @MethodSource("params")
    void constructsATrie(Param param) {
        System.out.println(Helper.shortToString(param));

        // Act
        TrieNode trie = trieConstruction.buildTrie(param.patterns);
        List<Edge> edges = trieConstruction.edges(trie);

        // Assert
        assertThat(edges).containsExactlyInAnyOrderElementsOf(param.edges);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        new String[]{"ATA"},
                        Arrays.asList(
                                new Edge(0, 1, 'A'),
                                new Edge(1, 2, 'T'),
                                new Edge(2, 3, 'A')
                        )
                ),
                Param.of(
                        new String[]{"AT", "AG", "AC"},
                        Arrays.asList(
                                new Edge(0, 1, 'A'),
                                new Edge(1, 2, 'T'),
                                new Edge(1, 3, 'G'),
                                new Edge(1, 4, 'C')
                        )
                ),
                Param.of(
                        new String[]{"ATAGA", "ATC", "GAT"},
                        Arrays.asList(
                                new Edge(0, 1, 'A'),
                                new Edge(1, 2, 'T'),
                                new Edge(2, 3, 'A'),
                                new Edge(2, 6, 'C'),
                                new Edge(3, 4, 'G'),
                                new Edge(4, 5, 'A'),
                                new Edge(0, 7, 'G'),
                                new Edge(7, 8, 'A'),
                                new Edge(8, 9, 'T')
                        )
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private String[] patterns;
        private List<Edge> edges;
    }

}