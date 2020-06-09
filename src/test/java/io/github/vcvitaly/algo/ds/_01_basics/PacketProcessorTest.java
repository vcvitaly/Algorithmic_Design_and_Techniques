package io.github.vcvitaly.algo.ds._01_basics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rnorth.ducttape.timeouts.Timeouts;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessPacketsTest {

    @ParameterizedTest
    @MethodSource("params")
    void verifiesPacketProcessingTime(Param param) {
        String s = param.toString();
        System.out.println(
                s.substring(0, Math.min(s.length(), 100))
        );

        assertThat(
                Timeouts.getWithTimeout(
                        1_500, TimeUnit.MILLISECONDS,
                        () -> ProcessPackets.processRequests(param.requests, param.buffer)
                )
        ).containsExactlyElementsOf(param.responses);
    }

    @Test
    void performanceTest() {
        int maxBufferSize = 100_000;
        int nPackets = 100_000;
        int maxPacketProcessingTime = 1_000;
        int maxIntervalBetweenPackets = 1_000;

        Random r = new Random(123_456_789);

        Buffer buffer = new Buffer(r.nextInt(maxBufferSize));

        List<Request> requests = Stream.iterate(
                new Request(0, 1),
                req -> new Request(
                        req.arrivalTime + (r.nextBoolean() ? 0 : r.nextInt(maxIntervalBetweenPackets)),
                        r.nextInt(maxPacketProcessingTime)
                )
        ).limit(nPackets)
                .collect(Collectors.toList());

        List<Response> responses = ProcessPackets.processRequests(requests, buffer);

        Map<Boolean, Long> countByStatus = responses.stream().collect(Collectors.groupingBy(response -> response.dropped,
                Collectors.counting()));
        System.out.println(countByStatus);
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(
                        new Buffer(1),
                        Collections.emptyList(),
                        Collections.emptyList()
                ),
                Param.of(
                        new Buffer(1),
                        Collections.singletonList(new Request(0, 0)),
                        Collections.singletonList(new Response(false, 0))
                ),
                Param.of(
                        new Buffer(1),
                        Arrays.asList(
                                new Request(0, 1),
                                new Request(0, 1)
                        ),
                        Arrays.asList(
                                new Response(false, 0),
                                new Response(true, -1)
                        )
                ),
                Param.of(
                        new Buffer(1),
                        Arrays.asList(
                                new Request(0, 1),
                                new Request(1, 1)
                        ),
                        Arrays.asList(
                                new Response(false, 0),
                                new Response(false, 1)
                        )
                )
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private Buffer buffer;
        private List<Request> requests;
        private List<Response> responses;
    }
}