package io.github.vcvitaly.algo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Helper {

    public static void setIn(Object... inputs) {
        InputStream is = new ByteArrayInputStream(
                Arrays.stream(inputs).map(String::valueOf).collect(Collectors.joining("\n"))
                        .getBytes()
        );
        System.setIn(is);
    }

    public static OutputStream setOut() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        System.setOut(out);
        return baos;
    }
}
