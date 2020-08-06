package io.github.vcvitaly.algo.ds._04_binary_trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            // Implement correct algorithm here
            return true;
        }

        static class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
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
