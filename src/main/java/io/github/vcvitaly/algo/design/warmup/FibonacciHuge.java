package io.github.vcvitaly.algo.design.warmup;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class FibonacciHuge {
    static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    static long getFibonacciHugeFast(long n, long m) {
        int pizanoPeriodOfM = getPizanoPeriod(m);
        return calcFib(
                (int) (n % pizanoPeriodOfM)
        ).mod(BigInteger.valueOf(m)).longValue();
    }

    static int getPizanoPeriod(long m) {
        BigInteger mBigInt = BigInteger.valueOf(m);

        List<Long> pizanoStartSeq = Arrays.asList(0L, 1L);
        int pizanoStartSeqSize = pizanoStartSeq.size();

        List<Long> remainders = new LinkedList<>(pizanoStartSeq);

        Predicate<List<Long>> isAPeriod = list -> {
            if (list.size() > pizanoStartSeqSize) {
                if (list.subList(list.size() - 2, list.size())
                        .equals(pizanoStartSeq)) {
                    return true;
                }
            }
            return false;
        };

        for (int i = pizanoStartSeqSize; !isAPeriod.test(remainders); i++) {
            BigInteger fib = calcFib(i);
            if (fib.compareTo(BigInteger.ZERO) < 0) {
                throw new IllegalStateException(String.format("fib(%d)=%d and is negative", i, fib));
            }

            remainders.add(fib.mod(mBigInt).longValue());
        }

        return remainders.size() - pizanoStartSeqSize;
    }

    private static BigInteger calcFib(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        BigInteger[] numbers = new BigInteger[n+1];
        numbers[0] = BigInteger.ZERO;
        numbers[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            numbers[i] = numbers[i-1].add(numbers[i-2]);
        }

        return numbers[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeFast(n, m));
    }
}
