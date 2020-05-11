package io.github.vcvitaly.algo.design._03_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DifferentSummands {

    static List<Integer> optimalSummands(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(String.format("%d should be > 0", n));
        }

        if (n <= 2) {
            return Collections.singletonList(n);
        }

        Set<Integer> summands = new HashSet<>(Collections.singletonList(1));
        n--;
        int a = 1;

        while (n > 0) {
            a++;
            if (summands.contains(n - a) || n - a == a) {
                a = n;
            }
            summands.add(a);
            n -= a;
        }

        return new ArrayList<>(summands);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}
