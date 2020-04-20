package io.github.vcvitaly.algo.design.challange;

import io.github.vcvitaly.algo.Helper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class MaxPairwiseProductTest {

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void findsMaxPairwiseProduct(long product, int n, String numbers) throws IOException {
        Helper.setIn(n, numbers);
        OutputStream os = Helper.setOut();

        MaxPairwiseProduct.main(null);

        assertThat(Long.parseLong(os.toString().trim())).isEqualTo(product);
    }

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.arguments(0, 2, "0 1"),
                Arguments.arguments(1, 2, "1 1"),
                Arguments.arguments(2, 2, "1 2"),
                Arguments.arguments(2, 2, "2 1"),
                Arguments.arguments(6, 3, "1 2 3"),
                Arguments.arguments(81, 5, "2 9 3 1 9"),
                Arguments.arguments(9000000000L, 2, "100000 90000"),
                Arguments.arguments(9702, 100,
                        "66 89 82 12 70 84 23 76 69 57 59 93 39 84 74 98 34 56 25 25 66 1 58 50 49 96 55 54 90 41 73 30 6 93 76 86 65" +
                                " 55 37 84 85 41 20 50 33 46 93 88 41 99 63 10 97 60 50 42 35 32 35 18 40 33 64 54 76 21 24 92 6 86" +
                                " 86 88 91 40 68 58 11 39 45 80 42 41 4 5 84 83 33 12 28 86 3 93 86 45 21 47 65 76 28 75"
                        )
                );
    }
}