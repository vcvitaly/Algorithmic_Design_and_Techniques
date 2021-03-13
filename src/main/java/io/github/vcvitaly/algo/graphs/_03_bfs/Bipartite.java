package io.github.vcvitaly.algo.graphs._03_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {

    static final int IS_BIPARTITE = 1;
    static final int IS_NOT_BIPARTITE = 0;

    private static final int WHITE = -1;
    private static final int BLACK = -2;
    private static final int NO_COLOR = 0;
    private static final int START_INDEX = 0;

    static boolean isBipartite(ArrayList<Integer>[] adj) {
        if (adj.length == 0 || adj.length == 1 && adj[0].isEmpty()) {
            return false;
        }

        int[] colors = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        colors[START_INDEX] = WHITE;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                queue.add(i);
            }

            while (!queue.isEmpty()) {
                int node = queue.poll();
                ArrayList<Integer> neighbors = adj[node];
                if (neighbors.isEmpty() && colors[node] == NO_COLOR) {
                    return false;
                }
                for (int neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        if (node != neighbor) {
                            queue.add(neighbor);
                            colors[neighbor] = colors[node] == WHITE ? BLACK : WHITE;
                        } else {
                            return false;
                        }
                    } else {
                        if (colors[node] == colors[neighbor]) {
                            return false;
                        }
                    }
                }
                visited[node] = true;
            }
        }

        for (int u = 0; u < adj.length; u++) {
            for (int v = 0; v < adj[u].size(); v++) {
                if (colors[u] == colors[adj[u].get(v)]) {
                    return false;
                }
            }
        }

        return true;
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
        System.out.println(isBipartite(adj) ? IS_BIPARTITE : IS_NOT_BIPARTITE);
    }
}

