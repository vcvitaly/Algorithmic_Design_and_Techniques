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
                new Param(new Segment[]{new Segment(1, 2)}, 1, new int[]{2}),
                // longer case
                new Param(new Segment[]{new Segment(41, 42), new Segment(52, 52), new Segment(63, 63), new Segment(80, 82),
                        new Segment(78, 79), new Segment(35, 35), new Segment(22, 23), new Segment(31, 32), new Segment(44, 45),
                        new Segment(81, 82), new Segment(36, 38), new Segment(10, 12), new Segment(1, 1), new Segment(23, 23),
                        new Segment(32, 33), new Segment(87, 88), new Segment(55, 56), new Segment(69, 71), new Segment(89, 91),
                        new Segment(93, 93), new Segment(38, 40), new Segment(33, 34), new Segment(14, 16), new Segment(57, 59),
                        new Segment(70, 72), new Segment(36, 36), new Segment(29, 29), new Segment(73, 74), new Segment(66, 68),
                        new Segment(36, 38), new Segment(1, 3), new Segment(49, 50), new Segment(68, 70), new Segment(26, 28),
                        new Segment(30, 30), new Segment(1, 2), new Segment(64, 65), new Segment(57, 58), new Segment(58, 58),
                        new Segment(51, 53), new Segment(41, 41), new Segment(17, 18), new Segment(45, 46), new Segment(4, 4),
                        new Segment(0, 1), new Segment(65, 67), new Segment(92, 93), new Segment(84, 85), new Segment(75, 77),
                        new Segment(39, 41), new Segment(15, 15), new Segment(29, 31), new Segment(83, 84), new Segment(12, 14),
                        new Segment(91, 93), new Segment(83, 84), new Segment(81, 81), new Segment(3, 4), new Segment(66, 67),
                        new Segment(8, 8), new Segment(17, 19), new Segment(86, 87), new Segment(44, 44), new Segment(34, 34),
                        new Segment(74, 74), new Segment(94, 95), new Segment(79, 81), new Segment(29, 29), new Segment(60, 61),
                        new Segment(58, 59), new Segment(62, 62), new Segment(54, 56), new Segment(58, 58), new Segment(79, 79),
                        new Segment(89, 91), new Segment(40, 42), new Segment(2, 4), new Segment(12, 14), new Segment(5, 5),
                        new Segment(28, 28), new Segment(35, 36), new Segment(7, 8), new Segment(82, 84), new Segment(49, 51),
                        new Segment(2, 4), new Segment(57, 59), new Segment(25, 27), new Segment(52, 53), new Segment(48, 49),
                        new Segment(9, 9), new Segment(10, 10), new Segment(78, 78), new Segment(26, 26), new Segment(83, 84),
                        new Segment(22, 24), new Segment(86, 87), new Segment(52, 54), new Segment(49, 51), new Segment(63, 64),
                        new Segment(54, 54)},
                        43,
                        new int[]{1, 4, 5, 8, 9, 10, 14, 15, 18, 23, 26, 28, 29, 30, 32, 34, 35, 36, 40, 41, 44, 46, 49, 52, 54, 56, 58,
                                61, 62, 63, 65, 67, 70, 74, 77, 78, 79, 81, 84, 87, 91, 93, 95})
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