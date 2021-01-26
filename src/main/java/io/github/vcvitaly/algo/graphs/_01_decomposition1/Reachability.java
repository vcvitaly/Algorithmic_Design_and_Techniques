package io.github.vcvitaly.algo.graphs._01_decomposition1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Reachability {
    static final int NO = 0;
    static final int YES = 1;

    static int reach(List<Integer>[] adj, int x, int y) {
        Stack<Integer> stack = new Stack<>();
        stack.add(x);

        boolean[] visited = new boolean[adj.length];

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (node == y) {
                return YES;
            }
            if (!visited[node]) {
                stack.addAll(adj[node]);
                visited[node] = true;
            }
        }

        return NO;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer>[] adj = (List<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}
