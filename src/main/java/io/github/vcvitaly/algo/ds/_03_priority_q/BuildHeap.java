package io.github.vcvitaly.algo.ds._03_priority_q;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class BuildHeap {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private int[] readData() throws IOException {
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }

        return data;
    }

    private void writeResponse(List<Swap> swaps) {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    List<Swap> generateSwapsNaive(int[] data) {
        List<Swap> swaps = new ArrayList<>();
        // The following naive implementation just sorts
        // the given sequence using selection sort algorithm
        // and saves the resulting sequence of swaps.
        // This turns the given array into a heap,
        // but in the worst case gives a quadratic number of swaps.
        for (int i = 0; i < data.length; ++i) {
            for (int j = i + 1; j < data.length; ++j) {
                if (data[i] > data[j]) {
                    swaps.add(new Swap(i, j));
                    swap(data, i, j);
                }
            }
        }

        return swaps;
    }

    List<Swap> generateSwapsFast(int[] data) {
        List<Swap> swaps = new ArrayList<>();

        for (int i = data.length / 2; i >= 0; i--) {
            swaps.addAll(siftDown(data, i));
        }

        return swaps;
    }

    private List<Swap> siftDown(int[] a, int i) {
        List<Swap> swaps = new ArrayList<>();

        int minIndex;
        while (i != (minIndex = minIndex(a, i))) {
            swap(a, i, minIndex);
            swaps.add(new Swap(i, minIndex));
        }

        return swaps;
    }

    private int minIndex(int[] a, int i) {
        int minIndex = i;

        int l = leftChild(i);
        if (hasChild(a, l) && a[l] < a[minIndex]) {
            minIndex = l;
        }

        int r = rightChild(i);
        if (hasChild(a, r) && a[r] < a[minIndex]) {
            minIndex = r;
        }

        return minIndex;
    }

    private boolean hasChild(int[] a, int childIndex) {
        return childIndex < a.length;
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return leftChild(i) + 1;
    }

    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int[] data = readData();
        List<Swap> swaps = generateSwapsNaive(data);
        writeResponse(swaps);
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public String toString() {
            return "Swap{" +
                    "index1=" + index1 +
                    ", index2=" + index2 +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Swap swap = (Swap) o;
            return index1 == swap.index1 &&
                    index2 == swap.index2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index1, index2);
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
