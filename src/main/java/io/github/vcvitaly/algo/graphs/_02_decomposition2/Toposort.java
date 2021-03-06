package io.github.vcvitaly.algo.graphs._02_decomposition2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Toposort {
    static List<Integer> toposort(List<Integer>[] adj) {
        List<Integer> order = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];
        boolean[] processed = new boolean[adj.length];

        int[] countOfParents = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            if (!adj[i].isEmpty()) {
                for (int j = 0; j < adj[i].size(); j++) {
                    countOfParents[adj[i].get(j)]++;
                }
            }
        }

        Queue<Integer> nodesWithoutIncomingEdges = new LinkedList<>();
        for (int i = 0; i < countOfParents.length; i++) {
            if (countOfParents[i] == 0) {
                nodesWithoutIncomingEdges.add(i);
            }
        }

        Stack<Integer> stack = new Stack<>();
        while (!nodesWithoutIncomingEdges.isEmpty()) {
            stack.push(nodesWithoutIncomingEdges.poll());

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
                    if (!processed[node]) {
                        for (int neighbor : adj[node]) {
                            countOfParents[neighbor]--;
                            if (countOfParents[neighbor] == 0 && !visited[neighbor]) {
                                nodesWithoutIncomingEdges.add(neighbor);
                            }
                        }
                        order.add(0, node + 1);
                        processed[node] = true;
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
            System.out.print((x) + " ");
        }
    }
}
