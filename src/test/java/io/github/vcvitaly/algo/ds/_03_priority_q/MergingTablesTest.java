package io.github.vcvitaly.algo.ds._03_priority_q;

import io.github.vcvitaly.algo.Helper;
import java.util.Arrays;
import java.util.List;
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

import static io.github.vcvitaly.algo.ds._03_priority_q.MergingTables.Merge;
import static io.github.vcvitaly.algo.ds._03_priority_q.MergingTables.Table;
import static org.assertj.core.api.Assertions.assertThat;

class MergingTablesTest {

    @ParameterizedTest
    @MethodSource("params")
    void appliesMergesAndReturnsCorrectMaxRowsAtEachSnapshot(Param param) {
        System.out.println(Helper.shortToString(param));

        List<Integer> maxSizes = new MergingTables(param.tables).applyMergesAndReturnMaxSizes(param.merges);

        System.out.println(maxSizes);
        assertThat(maxSizes).containsExactlyElementsOf(param.maxSizes);
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

    @Test
    void performanceTest() {
        Random r = new Random(123_456_789L);
        int nTables = 100_000;
        int nMerges = 100_000;
        int nRowsBound = 10_000;

        Table[] tables = r.ints(nTables, 0, nRowsBound).mapToObj(Table::new).toArray(Table[]::new);
        Merge[] merges = IntStream.range(0, nMerges)
                .mapToObj(i -> new Merge(r.nextInt(nTables), r.nextInt(nTables)))
                .toArray(Merge[]::new);

        Timeouts.getWithTimeout(
                6_000, TimeUnit.MILLISECONDS,
                () -> new MergingTables(tables).applyMergesAndReturnMaxSizes(merges)
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