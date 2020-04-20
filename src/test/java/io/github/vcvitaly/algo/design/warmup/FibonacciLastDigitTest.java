package io.github.vcvitaly.algo.design.warmup;

import io.github.vcvitaly.algo.Helper;
import io.github.vcvitaly.algo.design.warmup.FibonacciLastDigit;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciLastDigitTest {

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void findsLastDigitOfFibonacciNumber(int n, int digit) throws IOException {
        Helper.setIn(n);
        OutputStream os = Helper.setOut();

        FibonacciLastDigit.main(null);

        assertThat(Long.parseLong(os.toString().trim())).isEqualTo(digit);
    }

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.arguments(0, 0),
                Arguments.arguments(1, 1),
                Arguments.arguments(2, 1),
                Arguments.arguments(3, 2),
                Arguments.arguments(7, 3),
                Arguments.arguments(10, 5),
                Arguments.arguments(15, 0),
                Arguments.arguments(45, 0),
                Arguments.arguments(45, 0),
                Arguments.arguments(331, 9)
        );
    }
}