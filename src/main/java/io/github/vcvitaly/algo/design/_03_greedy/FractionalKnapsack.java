package io.github.vcvitaly.algo.design._03_greedy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class FractionalKnapsack {
    static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double totalValue = 0;
        int n = values.length;

        for (int j = 0; j < n; j++) {
            if (capacity == 0) {
                return roundToFourDecimalPlaces(totalValue);
            }
            int i = bestItem(values, weights);
            int weightToAdd = Math.min(weights[i], capacity);
            totalValue += weightToAdd * ((double) values[i] / weights[i]);
            weights[i] -= weightToAdd;
            capacity -= weightToAdd;
        }

        return roundToFourDecimalPlaces(totalValue);
    }

    private static double roundToFourDecimalPlaces(double d) {
        return BigDecimal.valueOf(d).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    private static int bestItem(int[] values, int[] weights) {
        double maxValuePerWeight = 0;
        int bestItem = 0;
        for (int i = 0; i < values.length; i++) {
            if (weights[i] > 0) {
                double ithValuePerWeight = (double) values[i] / weights[i];
                if (ithValuePerWeight > maxValuePerWeight) {
                    maxValuePerWeight = ithValuePerWeight;
                    bestItem = i;
                }
            }
        }
        return bestItem;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
}
