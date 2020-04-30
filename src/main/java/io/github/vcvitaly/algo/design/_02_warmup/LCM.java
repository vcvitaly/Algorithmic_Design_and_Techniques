package io.github.vcvitaly.algo.design._02_warmup;

import java.util.Scanner;

public class LCM {
    static long lcmFast(int a, int b) {
        return (a * (long) b) / gcdFast(a, b);
    }

    private static int gcdFast(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdFast(b, a % b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(lcmFast(a, b));
    }
}
