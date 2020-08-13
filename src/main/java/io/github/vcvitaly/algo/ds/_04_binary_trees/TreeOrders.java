package io.github.vcvitaly.algo.ds._04_binary_trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeOrders {
    Tree tree;

    public TreeOrders(Tree tree) {
        this.tree = tree;
    }

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

    List<Integer> inOrder() {
        if (tree.key.length == 0) {
            return Collections.emptyList();
        }
        if (tree.key.length == 1) {
            return Collections.singletonList(tree.key[0]);
        }

        return inOrder(0);
    }

    private List<Integer> inOrder(int rootIndex) {
        List<Integer> list = new LinkedList<>();
        if (tree.left[rootIndex] > -1) {
            list.addAll(inOrder(tree.left[rootIndex]));
        }
        list.add(tree.key[rootIndex]);
        if (tree.right[rootIndex] > -1) {
            list.addAll(inOrder(tree.right[rootIndex]));
        }
        return list;
    }

    List<Integer> preOrder() {
        if (tree.key.length == 0) {
            return Collections.emptyList();
        }
        if (tree.key.length == 1) {
            return Collections.singletonList(tree.key[0]);
        }

        return preOrder(0);
    }

    private List<Integer> preOrder(int rootIndex) {
        List<Integer> list = new LinkedList<>();
        list.add(tree.key[rootIndex]);
        if (tree.left[rootIndex] > -1) {
            list.addAll(preOrder(tree.left[rootIndex]));
        }
        if (tree.right[rootIndex] > -1) {
            list.addAll(preOrder(tree.right[rootIndex]));
        }
        return list;
    }

    List<Integer> postOrder() {
        if (tree.key.length == 0) {
            return Collections.emptyList();
        }
        if (tree.key.length == 1) {
            return Collections.singletonList(tree.key[0]);
        }

        return postOrder(0);
    }

    private List<Integer> postOrder(int rootIndex) {
        List<Integer> list = new LinkedList<>();
        if (tree.left[rootIndex] > -1) {
            list.addAll(postOrder(tree.left[rootIndex]));
        }
        if (tree.right[rootIndex] > -1) {
            list.addAll(postOrder(tree.right[rootIndex]));
        }
        list.add(tree.key[rootIndex]);
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
        TreeOrders tree = new TreeOrders(read());
        printList(tree.inOrder());
        printList(tree.preOrder());
        printList(tree.postOrder());
    }

    private static void printList(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
