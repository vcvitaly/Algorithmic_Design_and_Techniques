package io.github.vcvitaly.algo.ds._03_priority_q;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {
    private Table[] tables;

    private int maximumNumberOfRows;

    public MergingTables(Table[] tables) {
        this.tables = tables;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);

        int n = reader.nextInt();
        int m = reader.nextInt();

        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows);
        }

        Merge[] merges = new Merge[m];
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merges[i] = new Merge(destination, source);
        }

        List<Integer> maxSizes = new MergingTables(tables).applyMergesAndReturnMaxSizes(merges);

        for (int maxRowsAtSnapshot : maxSizes) {
            writer.printf("%d\n", maxRowsAtSnapshot);
        }
        writer.writer.flush();
    }

    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();
        Table realSource = source.getParent();
        if (realDestination == realSource) {
            return;
        }
        // merge two components here
        // use rank heuristic
    }

    List<Integer> applyMergesAndReturnMaxSizes(Merge[] merges) {
        List<Integer> maxSizes = new ArrayList<>();

        for (Merge merge : merges) {
            merge(tables[merge.destinationIndex], tables[merge.sourceIndex]);
            maxSizes.add(maximumNumberOfRows);
        }

        return maxSizes;
    }

    static class Task {
        Table[] tables;
        Merge[] merges;

        public Task(Table[] tables, Merge[] merges) {
            this.tables = tables;
            this.merges = merges;
        }
    }

    static class Table {
        Table parent;
        int rank;
        int numberOfRows;

        Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
        }

        Table getParent() {
            // find super parent and compress path
            return parent;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "parent=" + parent +
                    ", rank=" + rank +
                    ", numberOfRows=" + numberOfRows +
                    '}';
        }
    }

    static class Merge {
        int destinationIndex;
        int sourceIndex;

        public Merge(int destinationIndex, int sourceIndex) {
            this.destinationIndex = destinationIndex;
            this.sourceIndex = sourceIndex;
        }

        @Override
        public String toString() {
            return "Merge{" +
                    "destinationIndex=" + destinationIndex +
                    ", sourceIndex=" + sourceIndex +
                    '}';
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}
