package io.github.vcvitaly.algo.ds._01_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
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
        public static final int NULL = -1;
        int n;
        int parents[];

        public TreeHeight(int n, int[] parents) {
            this.n = n;
            this.parents = parents;
        }

        public TreeHeight() {
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

        // Pre-order traversal is used
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
            Stack<Node> stack = new Stack<>();
            for (Node node : nodes) {
                if (node.height == 0)
                if (node.height >= maxHeight) {
                    maxHeight = node.height;
                } else {
                    while (node.height == 0) {
                        stack.push(node);
                        node = node.parent;
                    }
                    for (Node nodeOnStack : stack) {
                        nodeOnStack.height = node.height + 1;
                        node = nodeOnStack;
                    }
                    if (node.height >= maxHeight) {
                        maxHeight = node.height;
                    }
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
        System.out.println(tree.computeHeightFast());
    }
}
