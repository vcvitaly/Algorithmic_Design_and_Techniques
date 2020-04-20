package io.github.vcvitaly.algo.design.warmup;

import io.github.vcvitaly.algo.Helper;
import io.github.vcvitaly.algo.design.warmup.Fibonacci;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciTest {

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void findsFibonacciNumber(int n, int number) throws IOException {
        Helper.setIn(n);
        OutputStream os = Helper.setOut();

        Fibonacci.main(null);

        assertThat(Long.parseLong(os.toString().trim())).isEqualTo(number);
    }

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.arguments(0, 0),
                Arguments.arguments(1, 1),
                Arguments.arguments(2, 1),
                Arguments.arguments(3, 2),
                Arguments.arguments(7, 13),
                Arguments.arguments(10, 55),
                Arguments.arguments(15, 610),
                Arguments.arguments(45, 1134903170)
        );
    }
}