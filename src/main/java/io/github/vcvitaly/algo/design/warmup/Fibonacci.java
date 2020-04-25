package io.github.vcvitaly.algo.design.warmup;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Fibonacci {
    static long calcFibFast(int n) {
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

    static BigInteger calcFibFastBigInt(int n) {
        if (n <= 92) {
            return BigInteger.valueOf(calcFibFast(n));
        }

        BigInteger[] numbers = new BigInteger[n+1];
        numbers[0] = BigInteger.ZERO;
        numbers[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            numbers[i] = numbers[i-1].add(numbers[i-2]);
        }

        return numbers[n];
    }

    /*static BigInteger calcFibWithBinetFormula(int n) {
        return n < 100 ? calcFibFastBigInt(n) :
                BigDecimal.valueOf(5).sqrt(MathContext.DECIMAL128).add(BigDecimal.ONE)
                        .pow(n)
                        .subtract(
                                BigDecimal.valueOf(5).sqrt(MathContext.DECIMAL128).subtract(BigDecimal.ONE)
                                        .pow(n)
                        )
                        .divide(
                                BigDecimal.valueOf(2).pow(n)
                                        .multiply(
                                                BigDecimal.valueOf(5).sqrt(MathContext.DECIMAL128)
                                        ), RoundingMode.HALF_UP
                        ).toBigInteger();
    }*/

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calcFibFast(n));
    }
}
