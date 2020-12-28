package io.github.vcvitaly.algo.ds._04_binary_trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class IsCorrectBstHard {

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

        private static final int NOT_VISITED = 0;
        private static final int ONCE = 1;
        private static final int TWICE = 2;
        private static final int ROOT_INDEX = 0;
        private static final int NULL = -1;

        Node[] nodes;

        public BinaryTree(Node[] nodes) {
            this.nodes = nodes;
        }

        boolean isBinarySearchTree() {
            if (nodes.length <= 1) {
                return true;
            }

            int[] visited = new int[nodes.length];

            int[] max = new int[nodes.length];
            int[] min = new int[nodes.length];

            Stack<Integer> stack = new Stack<>();
            stack.push(ROOT_INDEX);

            while (!stack.isEmpty()) {
                int i = stack.peek();
                Node node = nodes[i];
                if (visited[i] == NOT_VISITED) {
                    // start the left subtree traversal
                    if (node.left != NULL) {
                        if (nodes[node.left].key < node.key) {
                            stack.push(node.left);
                        } else {
                            return false;
                        }
                    } else {
                        min[i] = node.key;
                    }
                    visited[i] = ONCE;
                } else if (visited[i] == ONCE) {
                    // verify the left subtree correctness
                    if (node.left != NULL) {
                        if (max[node.left] < node.key) {
                            min[i] = min[node.left];
                        } else {
                            return false;
                        }
                    }
                    // start the right subtree traversal
                    if (node.right != NULL) {
                        if (nodes[node.right].key >= node.key) {
                            stack.push(node.right);
                        } else {
                            return false;
                        }
                    } else {
                        max[i] = node.key;
                    }
                    visited[i] = TWICE;
                } else {
                    // verify the right subtree correctness
                    if (node.right != NULL) {
                        if (min[node.right] >= node.key) {
                            max[i] = max[node.right];
                        } else {
                            return false;
                        }
                    }
                    stack.pop();
                }
            }
            return true;
        }
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

    static BinaryTree treeOf(int[] key, int[] left, int[] right) {
        Node[] nodes = new Node[key.length];

        for (int i = 0; i < key.length; i++) {
            nodes[i] = new Node(key[i], left[i], right[i]);
        }

        return new BinaryTree(nodes);
    }

    static BinaryTree read() {
        try {
            FastScanner in = new FastScanner();
            int n = in.nextInt();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }

            return new BinaryTree(nodes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        BinaryTree tree = read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
