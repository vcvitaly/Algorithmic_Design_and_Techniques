package io.github.vcvitaly.algo.graphs._01_decomposition1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ConnectedComponents {
    static int numberOfComponents(ArrayList<Integer>[] adj) {
        if (adj.length <= 1) {
            return adj.length;
        }

        int result = 0;

        boolean[] visited = new boolean[adj.length];

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                Stack<Integer> stack = new Stack<>();
                stack.add(i);

                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    if (!visited[node]) {
                        stack.addAll(adj[node]);
                        visited[node] = true;
                    }
                }

                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
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
        System.out.println(numberOfComponents(adj));
    }
}
