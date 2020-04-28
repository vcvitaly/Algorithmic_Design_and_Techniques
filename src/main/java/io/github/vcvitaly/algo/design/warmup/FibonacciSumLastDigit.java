package io.github.vcvitaly.algo.design.warmup;

import java.util.Scanner;

public class FibonacciSumLastDigit {

    private static final int PERIOD_OF_10 = 60;

    static long getFibonacciSumNaive(long n) {
        if (n <= 1) {
            return n;
        }

        long previous = 0,
             current  = 1,
             sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmpPrevious = previous;
            previous = current;
            current = tmpPrevious + current;
            sum += current;
        }

        return sum % 10;
    }

    static long getFibonacciSumFast(long n) {
        if (n <= 1) {
            return n;
        }

        return getLastDigitOfFibSumUpto(n);
    }

    private static long getLastDigitOfFibSumUpto(long n) {
        return ((getLastDigitOfFibN(n + 2) - 1) + 10) % 10;
    }

    static long getLastDigitOfFibN(long n) {
        return calcFib((int) (n % PERIOD_OF_10)) % 10;
    }

    private static long calcFib(int n) {
        if (n <= 1) {
            return n;
        }

        Long[] numbers = new Long[n+1];
        numbers[0] = 0L;
        numbers[1] = 1L;

        for (int i = 2; i <= n; i++) {
            numbers[i] = numbers[i-1] + numbers[i-2];
        }

        return numbers[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumFast(n);
        System.out.println(s);
    }
}


