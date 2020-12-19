package io.github.vcvitaly.algo.ds._04_binary_trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
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
        private static final int ROOT_INDEX = 0;
        private static final int NULL = -1;
        private int[] key, left, right;

        public BinaryTree(int[] key, int[] left, int[] right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        boolean isCorrectBst() {
            if (key.length <= 1) {
                return true;
            }

            boolean[] visited = new boolean[key.length];

            int[] max = new int[key.length];
            for (int i = 0; i < max.length; i++) {
                max[i] = Integer.MIN_VALUE;
            }

            int[] min = new int[key.length];
            for (int i = 0; i < min.length; i++) {
                min[i] = Integer.MAX_VALUE;
            }

            Stack<Integer> stack = new Stack<>();
            stack.push(ROOT_INDEX);

            while (!stack.isEmpty()) {
                int node = stack.peek();
                if (visited[node]) {
                    if (left[node] != NULL) {
                        if (max[left[node]] < key[node]) {
                            min[node] = min[left[node]];
                        } else {
                            return false;
                        }
                    }
                    if (right[node] != NULL) {
                        if (min[right[node]] > key[node]) {
                            max[node] = max[right[node]];
                        } else {
                            return false;
                        }
                    }
                    stack.pop();
                } else {
                    if (right[node] != NULL) {
                        if (key[right[node]] > key[node]) {
                            stack.push(right[node]);
                        } else {
                            return false;
                        }
                    } else {
                        max[node] = key[node];
                    }
                    if (left[node] != NULL) {
                        if (key[left[node]] < key[node]) {
                            stack.push(left[node]);
                        } else {
                            return false;
                        }
                    } else {
                        min[node] = key[node];
                    }
                    visited[node] = true;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "key=" + Arrays.toString(key) +
                    ", left=" + Arrays.toString(left) +
                    ", right=" + Arrays.toString(right) +
                    '}';
        }
    }

    public static void main(String[] args) {
        new Thread(null, () -> run(), "1", 1 << 26).start();
    }

    private static BinaryTree read() {
        try {
            FastScanner in = new FastScanner();
            int n = in.nextInt();
            int[] key = new int[n];
            int[] left = new int[n];
            int[] right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }

            return new BinaryTree(key, left, right);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void run() {
        BinaryTree tree = read();
        if (tree.isCorrectBst()) {
            System.out.println(CORRECT);
        } else {
            System.out.println(INCORRECT);
        }
    }
}
