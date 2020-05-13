package io.github.vcvitaly.algo.design._03_greedy;

import io.github.vcvitaly.algo.design._03_greedy.CoveringSegments.Segment;
import java.util.Arrays;
import java.util.stream.Collectors;
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
                // just 1 segment
                Param.of(new int[][]{{1, 2}},
                        1, new int[]{2}
                ),
                // all segments are joint
                Param.of(
                        new int[][] {{1, 3}, {2, 5}, {3, 6}},
                        1,
                        new int[] {3}
                ),
                // some segments are disjoint
                Param.of(
                        new int[][]{{4, 7}, {1, 3}, {2, 5}, {5, 6}},
                        2,
                        new int[] {3, 6}
                ),
                Param.of(
                        new int[][] {{4, 8}, {5, 7}, {8, 11}},
                        2, new int[] {7, 11}
                )
        );
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        private Segment[] segments;
        private int mPoints;
        private int[] pointCoordinates;

        static Param of(int[][] segmentCoordinates, int mPoints, int[] pointCoordinates) {
            Segment[] segments = Arrays.stream(segmentCoordinates)
                    .map(a -> new Segment(a[0], a[1]))
                    .collect(Collectors.toList())
                    .toArray(new Segment[segmentCoordinates.length]);
            return new Param(segments, mPoints, pointCoordinates);
        }

        @Override
        public String toString() {
            String s = "Param{" +
                    "segments=" + Arrays.toString(Arrays.stream(segments).map(segment -> new int[]{segment.start, segment.end}).map(Arrays::toString).toArray()) +
                    ", mPoints=" + mPoints +
                    ", pointCoordinates=" + Arrays.toString(pointCoordinates) +
                    '}';
            return s.substring(
                    0, Math.min(s.length(), 100)
            );
        }
    }
}