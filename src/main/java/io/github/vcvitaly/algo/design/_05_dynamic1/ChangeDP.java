package io.github.vcvitaly.algo.design._05_dynamic1;

import java.util.Scanner;

public class ChangeDP {

    private static int[] COINS = {1, 3, 4};

    int getChange(int m) {
        if (m < 0) {
            throw new IllegalArgumentException(String.format("m should be at least 0, but was %d", m));
        }

        if (m <= 1) {
            return m;
        }

        int[] memorized = new int[m+1];
        memorized[0] = 0;

        for (int i = 1; i <= m; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : COINS) {
                if (i >= coin) {
                    int prev = memorized[i-coin];
                    if (prev + 1 < min) {
                        min = prev + 1;
                    }
                }
            }
            memorized[i] = min;
        }

        return memorized[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(new ChangeDP().getChange(m));

    }
}
