package io.github.vcvitaly.algo.design._05_dynamic1;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeDPTest {

    @ParameterizedTest
    @MethodSource("params")
    void calculatesNumberOfCoins(Param param) {
        System.out.println(param);

        assertThat(new ChangeDP().getChange(param.money))
                .isEqualTo(param.numCoins);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(0, 0),
                Param.of(2, 2),
                Param.of(6, 2),
                Param.of(34, 9)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int money;
        private int numCoins;
    }
}