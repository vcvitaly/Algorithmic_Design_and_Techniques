package io.github.vcvitaly.algo.design._05_dynamic1;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class EditDistanceTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsEditDistance(Param param) {
        System.out.println(param);

        assertThat(EditDistance.editDistance(param.s, param.t)).isEqualTo(param.distance);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of("", "ab", 2),
                Param.of("ab", "ab", 0),
                Param.of("short", "ports", 3),
                Param.of("editing", "distance", 5)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private String s;
        private String t;
        private int distance;
    }
}