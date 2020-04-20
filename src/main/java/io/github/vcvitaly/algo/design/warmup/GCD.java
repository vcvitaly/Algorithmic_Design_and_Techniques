package io.github.vcvitaly.algo.design.warmup;

import java.util.Scanner;

public class GCD {
    static int gcdFast(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdFast(b, a % b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(gcdFast(a, b));
    }
}
