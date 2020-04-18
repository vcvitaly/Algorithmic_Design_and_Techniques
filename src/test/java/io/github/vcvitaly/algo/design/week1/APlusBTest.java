package io.github.vcvitaly.algo.design.week1;

import io.github.vcvitaly.algo.Helper;
import java.io.OutputStream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class APlusBTest {

    @Test
    void testMain() {
        Helper.setIn(9, 7);

        OutputStream os = Helper.setOut();

        APlusB.main(null);

        assertThat(Integer.parseInt(os.toString().trim())).isEqualTo(16);
    }
}