package io.github.vcvitaly.algo.design._06_dynamic2;

import java.util.Scanner;

// Maximum Amount of Gold

public class Knapsack {

    static int optimalWeight(int[] w, int W) {
        // I use w-1 because w is 0-indexed while m has a top row and a leftmost column both filled with 0 so i and j start at 1
        int[][] m = new int[w.length + 1][W + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= W; j++) {
                if (w[i-1] > j) {
                    m[i][j] = m[i-1][j];
                } else {
                    m[i][j] = Math.max(m[i-1][j-w[i-1]] + w[i-1], m[i-1][j]);
                }
            }
        }

        return m[w.length][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(w, W));
    }
}
