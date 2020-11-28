package io.github.vcvitaly.algo.ds._04_binary_trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class TreeOrders {
    private static final int ROOT_INDEX = 0;
    private static final int NONE = -1;

    private static Tree read() throws IOException {
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
        return new Tree(key, left, right);
    }

    List<Integer> inOrder(Tree tree) {
        if (tree.key.length == 0) {
            return Collections.emptyList();
        }
        if (tree.key.length == 1) {
            return Collections.singletonList(tree.key[0]);
        }

        boolean[] visited = new boolean[tree.key.length];
        List<Integer> list = new LinkedList<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(ROOT_INDEX);

        while (!stack.isEmpty()) {
            int node = stack.peek();
            if (visited[node]) {
                stack.pop();
                if (tree.right[node] != NONE) {
                    stack.push(tree.right[node]);
                }
                list.add(tree.key[node]);
            } else {
                if (tree.left[node] != NONE) {
                    stack.push(tree.left[node]);
                }
                visited[node] = true;
            }
        }

        return list;
    }

    List<Integer> preOrder(Tree tree) {
        if (tree.key.length == 0) {
            return Collections.emptyList();
        }
        if (tree.key.length == 1) {
            return Collections.singletonList(tree.key[0]);
        }

        List<Integer> list = new LinkedList<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(ROOT_INDEX);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            list.add(tree.key[node]);
            if (tree.right[node] != NONE) {
                stack.push(tree.right[node]);
            }
            if (tree.left[node] != NONE) {
                stack.push(tree.left[node]);
            }
        }

        return list;
    }

    List<Integer> postOrder(Tree tree) {
        if (tree.key.length == 0) {
            return Collections.emptyList();
        }
        if (tree.key.length == 1) {
            return Collections.singletonList(tree.key[0]);
        }
        
        boolean[] visited = new boolean[tree.key.length];
        List<Integer> list = new LinkedList<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(ROOT_INDEX);

        while (!stack.isEmpty()) {
            int node = stack.peek();
            if (visited[node]) {
                stack.pop();
                list.add(tree.key[node]);
            } else {
                if (tree.right[node] != NONE) {
                    stack.push(tree.right[node]);
                }
                if (tree.left[node] != NONE) {
                    stack.push(tree.left[node]);
                }
                visited[node] = true;
            }
        }

        return list;
    }

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

    static class Tree {
        private int[] key, left, right;

        public Tree(int[] key, int[] left, int[] right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        Tree tree = read();
        TreeOrders treeOrders = new TreeOrders();
        printList(treeOrders.inOrder(tree));
        printList(treeOrders.preOrder(tree));
        printList(treeOrders.postOrder(tree));
    }

    private static void printList(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
