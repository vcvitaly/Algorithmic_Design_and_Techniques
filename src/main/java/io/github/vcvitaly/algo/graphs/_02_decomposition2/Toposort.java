package io.github.vcvitaly.algo.graphs._02_decomposition2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Toposort {
    static List<Integer> toposort(List<Integer>[] adj) {
        List<Integer> order = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                Stack<Integer> stack = new Stack<>();
                stack.add(i);

                while (!stack.isEmpty()) {
                    int node = stack.peek();
                    if (!visited[node]) {
                        for (int neighbor : adj[node]) {
                            if (!visited[neighbor]) {
                                stack.push(neighbor);
                            }
                        }
                        visited[node] = true;
                    } else {
                        stack.pop();
                        order.add(0, node + 1);
                    }
                }
            }
        }

        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        //write your code here
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        List<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}
