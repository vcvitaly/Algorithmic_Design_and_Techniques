package io.github.vcvitaly.algo.design._03_greedy;

import io.github.vcvitaly.algo.design._03_greedy.CoveringSegments.Segment;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class CoveringSegmentsTest {

    @ParameterizedTest
    @MethodSource("params")
    void returnsOptimalValue(Param param) {
        System.out.println(param);

        int[] optimalPoints = CoveringSegments.optimalPoints(param.segments);

        assertThat(optimalPoints)
                .containsExactly(param.pointCoordinates)
                .hasSize(param.mPoints);
    }

    static Stream<Param> params() {
        return Stream.of(
                new Param(new Segment[]{new Segment(1, 3), new Segment(2, 5), new Segment(3, 6)}, 1, new int[] {3}),
                new Param(new Segment[]{new Segment(4, 7), new Segment(1, 3), new Segment(2, 5), new Segment(5, 6)}, 2, new int[] {3, 6}),
                new Param(new Segment[]{new Segment(1, 2)}, 1, new int[]{2})
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private Segment[] segments;
        private int mPoints;
        private int[] pointCoordinates;

        @Override
        public String toString() {
            return "Param{" +
                    "segments=" + Arrays.toString(Arrays.stream(segments).map(s -> new int[]{s.start, s.end}).map(Arrays::toString).toArray()) +
                    ", mPoints=" + mPoints +
                    ", pointCoordinates=" + Arrays.toString(pointCoordinates) +
                    '}';
        }
    }
}