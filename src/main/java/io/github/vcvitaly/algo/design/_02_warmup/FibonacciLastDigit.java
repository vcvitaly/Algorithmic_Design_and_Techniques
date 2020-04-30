package io.github.vcvitaly.algo.design._02_warmup;

import java.util.Scanner;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitFast(int n) {
        if (n <= 1) {
            return n;
        }

        int[] digits = new int[n+1];
        digits[0] = 0;
        digits[1] = 1;

        for (int i = 2; i <= n; i++) {
            digits[i] = (digits[i-1] + digits[i-2]) % 10;
        }

        return digits[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitFast(n);
        System.out.println(c);
    }
}
