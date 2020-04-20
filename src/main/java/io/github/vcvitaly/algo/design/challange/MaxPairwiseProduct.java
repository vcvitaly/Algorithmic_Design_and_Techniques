package io.github.vcvitaly.algo.design.challange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class MaxPairwiseProduct {

    public static void main(String[] args) throws IOException {
        int n = 0;
        int[] numbers = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            if (br.ready()) {
                n = Integer.parseInt(br.readLine());
            }
            if (br.ready()) {
                String[] stringNums = br.readLine().split(" ");
                numbers = Arrays.stream(stringNums)
                        .map(Integer::parseInt)
                        .mapToInt(Integer::intValue)
                        .limit(n)
                        .toArray();
            }
        }

        System.out.println(getMaxPairwiseProductFast(Objects.requireNonNull(numbers)));

    }

    public static long getMaxPairwiseProductFast(int[] numbers) {
        int max = 0;
        int prevMax = 0;

        for (int number : numbers) {
            if (number > max) {
                prevMax = max;
                max = number;
            } else if (number > prevMax && number <= max) {
                prevMax = number;
            }
        }

        return max * (long) prevMax;
    }

    public static long getMaxPairwiseProductNaive(int[] numbers) {
        long product = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                product = Long.max(product, numbers[i] * (long) numbers[j]);
            }
        }
        return product;
    }

    public static long getMaxPairwiseProductSort(int[] numbers) {
        int n = numbers.length;
        Arrays.sort(numbers);
        return (long) numbers[n-1] * numbers[n-2];
    }
}
