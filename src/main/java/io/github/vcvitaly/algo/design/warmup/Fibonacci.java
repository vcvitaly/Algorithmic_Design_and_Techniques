package io.github.vcvitaly.algo.design.warmup;

import java.util.Scanner;

public class Fibonacci {
    private static long calcFib(int n) {
        if (n <= 1) {
            return n;
        }

        int[] numbers = new int[n+1];
        numbers[0] = 0;
        numbers[1] = 1;

        for (int i = 2; i <= n; i++) {
            numbers[i] = numbers[i-1] + numbers[i-2];
        }

        return numbers[n];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calcFib(n));
    }
}
