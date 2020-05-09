package io.github.vcvitaly.ds._01_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class tree_height {
    static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static class TreeHeight {
        private static final int ROOT_HEIGHT = 1;
        int n;
        int parent[];

        public TreeHeight(int n, int[] parent) {
            this.n = n;
            this.parent = parent;
        }

        public TreeHeight() {
        }

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parent[i]) height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }

        int computeHeightFast() {
            int[] heights = new int[n];
            Arrays.fill(heights, -1);

            int maxHeight = 0;

            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;

                if (parent[vertex] == -1) {
                    height = heights[vertex] = ROOT_HEIGHT;
                }
                else if (heights[parent[vertex]] >= ROOT_HEIGHT) {
                    height = heights[vertex] = heights[parent[vertex]] + 1;
                } else {
                    int i;
                    for (i = vertex; i != -1 && heights[i] >= 0; i = parent[i]) height++;
                    height  = heights[vertex] = heights[i] + 1;
                }

                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new tree_height().run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}

