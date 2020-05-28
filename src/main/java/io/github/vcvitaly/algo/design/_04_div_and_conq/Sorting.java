package io.github.vcvitaly.algo.design._04_div_and_conq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;


/**
 * <code>x</code> - pivot number<br>
 * <code>j</code> - pivot index
 */
public class Sorting {
    private static Random random = new Random();

    static int[] partition3(int[] a, int l, int r) {
        int x = a[l];
        int j = l,
            m1 = l;

        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                swap(a, i, j);
                if (a[j] < x) {
                    swap(a, m1, j);
                    m1++;
                }
            }
        }

        int m2 = j;
        return new int[]{m1, m2};
    }

    static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                swap(a, i, j);
            }
        }
        swap(a, l, j);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }

        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;

        while (l < r) {
            int[] m = partition3(a, l, r);
            if (m[0] - 1 - l < r - m[1] + 1) {
                randomizedQuickSort(a, l, m[0] - 1);
                l = m[1] + 1;
            } else {
                randomizedQuickSort(a, m[1] + 1, r);
                r = m[0] - 1;
            }
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
