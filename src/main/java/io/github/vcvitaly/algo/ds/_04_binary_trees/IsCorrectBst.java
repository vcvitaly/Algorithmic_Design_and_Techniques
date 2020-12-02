package io.github.vcvitaly.algo.ds._04_binary_trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IsCorrectBst {

    static final String CORRECT = "CORRECT";
    static final String INCORRECT = "INCORRECT";

    private static class FastScanner {
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

    static class BinaryTree {
        private Node[] nodes;

        public BinaryTree(Node[] nodes) {
            this.nodes = nodes;
        }

        boolean isBinarySearchTree() {
            if (nodes.length <= 1) {
                return true;
            }

            try {
                subtreeMaxVal(0);
                return true;
            } catch (IllegalStateException e) {
                return false;
            }
        }

        private int subtreeMaxVal(int i) {
            Node node = nodes[i];
            int max = node.key;

            if (node.left > -1) {
                int left = subtreeMaxVal(node.left);
                if (left >= node.key) {
                    throw new IllegalStateException(String.format("Max value in the left subtree %d is >= than the node %d", left,
                            node.key));
                }
            }
            if (node.right > -1) {
                int right = subtreeMaxVal(node.right);
                if (right <= node.key) {
                    throw new IllegalStateException(String.format("Max value in the right subtree %d is <= than the node %d", right,
                            node.key));
                }
                max = right;
            }

            return max;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "nodes=" + Arrays.toString(nodes) +
                    '}';
        }

        static class Node {
            int key;
            int left; // left child index
            int right; // right child index

            public Node(int key, int left, int right) {
                this.key = key;
                this.left = left;
                this.right = right;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "key=" + key +
                        ", left=" + left +
                        ", right=" + right +
                        '}';
            }
        }
    }

    public static void main(String[] args) {
        new Thread(null, () -> run(), "1", 1 << 26).start();
    }

    private static BinaryTree.Node[] read() {
        try {
            FastScanner in = new FastScanner();
            int n = in.nextInt();
            BinaryTree.Node[] nodes = new BinaryTree.Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new BinaryTree.Node(in.nextInt(), in.nextInt(), in.nextInt());
            }

            return nodes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void run() {
        BinaryTree tree = new BinaryTree(read());
        if (tree.isBinarySearchTree()) {
            System.out.println(CORRECT);
        } else {
            System.out.println(INCORRECT);
        }
    }
}
