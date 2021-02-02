package io.github.vcvitaly.algo.graphs._02_decomposition2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Acyclicity {

    static final int NO_CYCLES = 0;
    static final int HAS_CYCLE = 1;
    private static final int DEFAULT_POST_ORDER = 0;

    // [CLRS] 22.4 , [DVP] 3.3.2
    static int acyclic(ArrayList<Integer>[] adj) {
        if (adj.length <= 1) {
            return NO_CYCLES;
        }

        boolean[] visited = new boolean[adj.length];
        int[] postOrder = new int[adj.length];
        int clock = 1;

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                Stack<Integer> stack = new Stack<>();
                stack.add(i);

                while (!stack.isEmpty()) {
                    int node = stack.peek();
                    if (!visited[node]) {
                        for (int neighbor : adj[node]) {
                            if (visited[neighbor] && postOrder[neighbor] == DEFAULT_POST_ORDER) {
                                return HAS_CYCLE;
                            } else {
                                stack.push(neighbor);
                            }
                        }
                        visited[node] = true;
                    } else {
                        stack.pop();
                        postOrder[node] = clock++;
                    }
                }
            }
        }

        return NO_CYCLES;
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
        }
        System.out.println(acyclic(adj));
    }
}

