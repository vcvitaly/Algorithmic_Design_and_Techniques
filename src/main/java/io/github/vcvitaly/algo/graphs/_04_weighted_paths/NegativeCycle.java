package io.github.vcvitaly.algo.graphs._04_weighted_paths;

import io.github.vcvitaly.algo.graphs._03_bfs.common.Edge;

import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {

    public static final int YES = 1;
    public static final int NO = 0;

    static boolean hasNegativeCycle(ArrayList<Edge>[] adj) {
        int[] distances = new int[adj.length];
        int[] prev = new int[adj.length];

        boolean performedUpdates;

        for (int k = 0; k < adj.length - 1; k++) {
            performedUpdates = false;
            for (int i = 0; i < adj.length; i++) {
                for (Edge e : adj[i]) {
                    if (distances[e.v] > distances[i] + e.weight) {
                        distances[e.v] = distances[i] + e.weight;
                        prev[e.v] = i;
                        performedUpdates = true;
                    }
                }
            }
            if (!performedUpdates) {
                break;
            }
        }

        for (int i = 0; i < adj.length; i++) {
            for (Edge e : adj[i]) {
                if (distances[e.v] > distances[i] + e.weight) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Edge>[] adj = (ArrayList<Edge>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(new Edge(y - 1, w));
        }
        System.out.println(hasNegativeCycle(adj) ? YES : NO); // TODO refactor
    }
}
