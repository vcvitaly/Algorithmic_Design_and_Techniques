package io.github.vcvitaly.algo.design._03_greedy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LargestNumber {
    static String largestNumber(String[] a) {

        return Arrays.stream(a)
                .sorted(
                        (s1, s2) -> -(greaterThanOrEqual(s1, s2))
                )
                .collect(Collectors.joining(""));
    }

    private static int greaterThanOrEqual(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return s1.compareTo(s2);
        } else {
            int minLength = Math.min(s1.length(), s2.length());
            int compare = s1.substring(0, minLength).compareTo(s2.substring(0, minLength));
            if (compare == 0) {
                return s1.length() < s2.length() ? 1 : -1;
            }
            return compare;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}
