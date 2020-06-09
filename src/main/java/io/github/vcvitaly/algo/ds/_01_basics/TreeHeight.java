package io.github.vcvitaly.algo.ds._01_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TreeHeight {
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

    public static class Tree {
        private static final int ROOT_HEIGHT = 1;
        public static final int NULL = -1;
        int n;
        int parents[];

        public Tree(int n, int[] parents) {
            this.n = n;
            this.parents = parents;
        }

        public Tree() {
        }

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = in.nextInt();
            }
        }

        int computeHeight() {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parents[i]) height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }

        // Pre-order traversal is used.
        // TODO can be furter improved by using an array to remember heights instead of a node class to reduce the space complexity and
        // possibly do that in CPU cache
        int computeHeightFast() {
            if (parents.length <= 2) {
                return parents.length;
            }

            Node[] nodes = new Node[parents.length];
            for (int i = 0; i < parents.length; i++) {
                nodes[i] = new Node(i);
            }

            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == NULL) {
                    nodes[i].height = ROOT_HEIGHT;
                } else {
                    nodes[i].parent = nodes[parents[i]];
                }
            }

            int maxHeight = 0;
            for (Node node : nodes) {
                int nodeHeight = node.getHeightOrCompute();
                if (maxHeight < nodeHeight) {
                    maxHeight = nodeHeight;
                }
            }

            return maxHeight;
        }
    }

    private static class Node {
        int value;
        Node parent;
        int height;

        public Node(int value) {
            this.value = value;
        }

        public int getHeightOrCompute() {
            if (height == 0) {
                height = parent.getHeightOrCompute() + 1;
            }
            return height;
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new TreeHeight().run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        Tree tree = new Tree();
        tree.read();
        System.out.println(tree.computeHeightFast());
    }
}
