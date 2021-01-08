package io.github.vcvitaly.algo.design._06_dynamic2;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class Partition3Test {

    @ParameterizedTest
    @MethodSource("params")
    void checksIfCanBePartitionedIntoThreeParts(Param param) {
        System.out.println(param);

        assertThat(
                Partition3.cabBePartitionedInto3(param.souvenirValues)
        ).isEqualTo(param.canBePartitionedIntoThreeParts);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[] {3, 3, 3, 3}, Partition3.NO),
                Param.of(new int[] {30}, Partition3.NO),
                Param.of(new int[] {1, 2, 3, 4, 5, 5, 7, 7, 8, 10, 12, 19, 25}, Partition3.YES)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] souvenirValues;
        private int canBePartitionedIntoThreeParts;
    }
}