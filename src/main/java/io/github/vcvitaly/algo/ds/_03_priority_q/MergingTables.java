package io.github.vcvitaly.algo.ds._03_priority_q;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {
    private Table[] tables;

    // TODO the maximumNumberOfRows variable seems specific to this particular problem, how to avoid a lot of case-specific metrics?
    private int maximumNumberOfRows;

    public MergingTables(Table[] tables) {
        this.tables = tables;
        maximumNumberOfRows = Arrays.stream(tables).map(t -> t.numberOfRows).max(Integer::compareTo).get();
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
            int destination = reader.nextInt();
            int source = reader.nextInt();
            merges[i] = new Merge(destination, source);
        }

        List<Integer> maxSizes = new MergingTables(tables).applyMergesAndReturnMaxSizes(merges);

        for (int maxRowsAtSnapshot : maxSizes) {
            writer.printf("%d\n", maxRowsAtSnapshot);
        }
        writer.writer.flush();
    }

    // TODO However, you will need to store the number of the actual
    // second table to which you were requested to merge the first table in the parent node of the corresponding
    // Disjoint Set, and you will need an additional field in the nodes of Disjoint Set Union to store it.
    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();
        Table realSource = source.getParent();
        if (realDestination == realSource) {
            return;
        }
        if (realDestination.rank > realSource.rank) {
            copyData(realDestination, realSource);
            realSource.parent = realDestination;
        } else {
            copyData(realSource, realDestination);
            realDestination.parent = realSource;
            if (realDestination.rank == realSource.rank) {
                realSource.rank++;
            }
        }
    }

    List<Integer> applyMergesAndReturnMaxSizes(Merge[] merges) {
        List<Integer> maxSizes = new ArrayList<>();

        for (Merge merge : merges) {
            merge(tables[merge.destinationIndex - 1], tables[merge.sourceIndex - 1]);
            maxSizes.add(maximumNumberOfRows);
        }

        return maxSizes;
    }

    private void copyData(Table destination, Table source) {
        destination.numberOfRows += source.numberOfRows;
        source.numberOfRows = 0;

        // TODO  is it logical to do that in a method whose name doesn't reflect the fact that metric is updated?
        if (destination.numberOfRows > maximumNumberOfRows) {
            maximumNumberOfRows = destination.numberOfRows;
        }
    }

    static class Table {
        private static int num = 1;

        int id;
        Table parent;
        int rank;
        int numberOfRows;

        Table(int numberOfRows) {
            id = num++;
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
        }

        Table getParent() {
            if (this != parent) {
                parent = parent.getParent();
            }
            return parent;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "id=" + id +
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
