package io.github.vcvitaly.algo.design._02_warmup;

import java.util.Scanner;

public class FibonacciPartialSum {

    private static final int PERIOD_OF_10 = 60;

    static long getFibonacciPartialSumFast(long from, long to) {
        if (to <= 1) {
            return to;
        }

        long fromMinus1LastDigit = getLastDigitOfFibSumUpto(from - 1);
        long toLastDigit = getLastDigitOfFibSumUpto(to);

        return (toLastDigit - fromMinus1LastDigit + 10) % 10;
    }

    private static long getLastDigitOfFibSumUpto(long to) {
        return ((getLastDigitOfFibN(to + 2) - 1) + 10) % 10;
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
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumFast(from, to));
    }
}
