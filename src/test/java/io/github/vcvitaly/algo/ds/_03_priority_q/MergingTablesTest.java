package io.github.vcvitaly.algo.ds._03_priority_q;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.github.vcvitaly.algo.ds._03_priority_q.MergingTables.Merge;
import static io.github.vcvitaly.algo.ds._03_priority_q.MergingTables.Table;
import static org.assertj.core.api.Assertions.assertThat;

class MergingTablesTest {

    @ParameterizedTest
    @MethodSource("params")
    void appliesMergesAndReturnsCorrectMaxRowsAtEachSnapshot(Param param) {
        System.out.println(Helper.shortToString(param));

        assertThat(
                new MergingTables(param.tables).applyMergesAndReturnMaxSizes(param.merges)
        ).containsExactlyElementsOf(param.maxSizes);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        Arrays.stream(new int[]{1, 1, 1, 1, 1}).mapToObj(Table::new).toArray(Table[]::new),
                        new Merge[]{
                                new Merge(3, 5),
                                new Merge(2, 4),
                                new Merge(1, 4),
                                new Merge(5, 4),
                                new Merge(5, 3)
                        },
                        Arrays.asList(2, 2, 3, 5, 5)
                ),
                Param.of(
                        Arrays.stream(new int[]{10, 0, 5, 0, 3, 3}).mapToObj(Table::new).toArray(Table[]::new),
                        new Merge[]{
                                new Merge(6, 6),
                                new Merge(6, 5),
                                new Merge(5, 4),
                                new Merge(4, 3)
                        },
                        Arrays.asList(10, 10, 10, 11)
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private Table[] tables;
        private Merge[] merges;
        private List<Integer> maxSizes;
    }
}