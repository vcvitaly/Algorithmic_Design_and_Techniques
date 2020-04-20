package io.github.vcvitaly.algo.design.challange;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class MaxPairwiseProductStressTest {

    private Random random = new Random(123456789L);

    private int maxNums = 1_000;
    private int upperBound = 200_000;

    @Test
    public void testMain() throws Exception {
        while (true) {
            int n = between(2, maxNums);
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = random.nextInt(upperBound);
            }
            System.out.println(Arrays.toString(numbers));
            long result1 = MaxPairwiseProduct.getMaxPairwiseProductFast(numbers);
            long result2 = MaxPairwiseProduct.getMaxPairwiseProductSort(numbers);
            if (result1 == result2) {
                System.out.println("OK");
            } else {
//                System.out.println(Arrays.toString(numbers));
                System.out.println(String.format("Wrong result: %d %d", result1, result2));
                return;
            }

            Thread.sleep(1);
        }

    }

    private int between(int min, int max) {
        return random.ints(min, max + 1).findFirst().getAsInt();
    }
}