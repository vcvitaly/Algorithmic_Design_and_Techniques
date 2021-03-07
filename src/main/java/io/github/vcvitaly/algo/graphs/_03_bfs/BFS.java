package io.github.vcvitaly.algo.graphs._03_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    static final int NONE = -1;

    static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int[] disntances = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        Arrays.fill(disntances, NONE);
        disntances[s] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            ArrayList<Integer> neighbors = adj[node];
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                }
                if (disntances[neighbor] == NONE || disntances[node] + 1 < disntances[neighbor]) {
                    disntances[neighbor] = disntances[node] + 1;
                }
            }
            visited[node] = true;
        }

        return disntances[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}
