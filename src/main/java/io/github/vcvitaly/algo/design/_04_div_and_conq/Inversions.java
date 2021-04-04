package io.github.vcvitaly.algo.design._04_div_and_conq;

import java.util.Scanner;

public class Inversions {

    static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        return mergeSortWithInversion(a, left, right).right;
    }

    private static Tuple<int[], Long> mergeSortWithInversion(int[] a, int left, int right) {
        if (left == right) {
            return new Tuple<>(new int[] {a[left]}, 0L);
        }

        int mid = mid(left, right);

        Tuple<int[], Long> t1 = mergeSortWithInversion(a, left, mid);
        Tuple<int[], Long> t2 = mergeSortWithInversion(a, mid + 1, right);

        return merge(t1, t2);
    }

    // Also an algorithm to merge 2 arrays/lists
    private static Tuple<int[], Long> merge(Tuple<int[], Long> t1, Tuple<int[], Long> t2) {
        long nOfInversions = t1.right + t2.right;
        int[] a = t1.left;
        int[] b = t2.left;
        int[] sorted = new int[a.length + b.length];

        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                sorted[i+j] = a[i++];
            } else {
                sorted[i+j] = b[j++];
                nOfInversions += a.length - i;
            }
        }

        for (; i < a.length; i++) {
            sorted[i+j] = a[i];
        }

        for (; j < b.length; j++) {
            sorted[i+j] = b[j];
        }

        return new Tuple<>(sorted, nOfInversions);
    }

    private static int mid(int left, int right) {
        return left + (right - left) / 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(
                getNumberOfInversions(a, b, 0, a.length - 1)
        );
    }

    private static class Tuple<L, R> {
        L left;
        R right;

        public Tuple(L left, R right) {
            this.left = left;
            this.right = right;
        }
    }
}
