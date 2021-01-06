package io.github.vcvitaly.algo.design._06_dynamic2;

import java.util.Scanner;

public class Partition3 {

    static final int NO = 0;
    static final int YES = 1;

    static int cabBePartitionedInto3(int[] A) {
        //write your code here
        return NO;
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
