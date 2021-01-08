package io.github.vcvitaly.algo.design._06_dynamic2;

import java.util.Arrays;
import java.util.Scanner;

public class Partition3 {

    static final int NO = 0;
    static final int YES = 1;

    static int cabBePartitionedInto3(int[] a) {
        int sum = Arrays.stream(a).sum();
        if (sum % 3 != 0) {
            return NO;
        }

        int n = a.length;
        int oneThird = sum / 3;
        int[][] m = new int[n+1][oneThird+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= oneThird; j++) {
                if (a[i-1] > j) {
                    m[i][j] = m[i-1][j];
                } else {
                    m[i][j] = Math.max(m[i-1][j-a[i-1]] + a[i-1], m[i-1][j]);
                }
            }
        }

        int howManyOneThirds = 0;
        for (int i = 0; i <= n; i++) {
            if (m[i][oneThird] == oneThird) {
                howManyOneThirds++;
            }
        }

        return howManyOneThirds >= 3 ? YES : NO;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(cabBePartitionedInto3(a));
    }
}
