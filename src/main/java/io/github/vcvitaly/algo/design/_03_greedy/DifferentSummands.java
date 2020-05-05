package io.github.vcvitaly.algo.design._03_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DifferentSummands {

    static List<Integer> optimalSummands(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(String.format("%d should be > 0", n));
        }

        if (n <= 2) {
            return Collections.singletonList(n);
        }

        List<Integer> summands = new ArrayList<>(Arrays.asList(1));
        n--;

        while (n > 0) {
            int a = summands.get(summands.size() - 1) + 1;
            while (summands.contains(n - a)) {
                a++;
            }
            summands.add(a);
            n -= a;
        }

        return summands;
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
