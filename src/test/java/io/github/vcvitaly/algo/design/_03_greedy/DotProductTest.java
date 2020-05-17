package io.github.vcvitaly.algo.design._03_greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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

class DotProductTest {

    @ParameterizedTest
    @MethodSource("params")
    void findsMaxRevenue(Param param) {
        System.out.println(param);

        assertThat(
                Timeouts.getWithTimeout(500, TimeUnit.MILLISECONDS,
                        () -> DotProduct.maxDotProduct(param.a, param.b)
                )
        ).isEqualTo(param.maxRevenue);
    }

    @Test
    void stressTest() {
        Random random = new Random(123456789L);
        for (int i = 0; i < 100; i++) {
            runTestCase(random);
        }
    }

    private void runTestCase(Random random) {
        int countOfElements = random.nextInt(5) + 2;
        int[] a = random.ints(countOfElements, -100_000, 100_000).toArray();
        int[] b = random.ints(countOfElements, -100_000, 100_000).toArray();
        System.out.println(String.format("a: %s\nb: %s", Arrays.toString(a), Arrays.toString(b)));

        List<List<Integer>> permutations = permutations(Arrays.stream(b).boxed().collect(Collectors.toList()));
        List<Long> sums = permutations.stream()
                .map(p -> {
                    long[] c = new long[a.length];
                    for (int i = 0; i < a.length; i++) {
                        c[i] = (long) a[i] * p.get(i);
                    }
                    return Arrays.stream(c).sum();
                }).collect(Collectors.toList());
        Long maxSum = Collections.max(sums);

        try {
            assertThat(DotProduct.maxDotProduct(a, b))
                    .isGreaterThanOrEqualTo(maxSum);
        } catch (AssertionError e) {
            System.err.println(String.format("Failed on:\na: %s\nb: %s", Arrays.toString(a), Arrays.toString(b)));
            throw new RuntimeException(e);
        }
    }

    static Stream<Param> params() {
        return Stream.of(
                Param.of(new int[] {23}, new int[] {39}, 897),
                Param.of(new int[] {1, 3, -5}, new int[] {-2, 4, 1}, 23)
        );
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    private static class Param {
        private int[] a;
        private int[] b;
        private long maxRevenue;
    }

    private static List<List<Integer>> permutations(List<Integer> a) {
        if (a.size() == 1) {
            return List.of(new LinkedList<>(a));
        }
        if (a.size() == 2) {
            return List.of(
                    new LinkedList<>(a),
                    new LinkedList<>(Arrays.asList(a.get(1), a.get(0)))
            );
        }

        List<List<Integer>> permutations = new LinkedList<>();
        for (int i = 0; i < a.size(); i++) {
            Integer aI = a.get(i);
            List<List<Integer>> permutationsWithoutI = permutations(
                    a.stream()
                            .filter(
                                    elem -> {
                                        return !elem.equals(aI);
                                    }
                            ).collect(Collectors.toList())
            );
            permutationsWithoutI.forEach(list -> list.add(0, aI));
            permutations.addAll(permutationsWithoutI);
        }

        return permutations;
    }

    @Test
    void testPermutations() {
        assertThat(permutations(List.of(1, 2, 3,4)))
                .containsExactlyInAnyOrder(
                        List.of(1, 2, 3),
                        List.of(1, 3, 2),
                        List.of(2, 1, 3),
                        List.of(2, 3, 1),
                        List.of(3, 1, 2),
                        List.of(3, 2, 1)
                );
    }
}