package io.github.vcvitaly.algo.graphs._04_weighted_paths;

import io.github.vcvitaly.algo.graphs._03_bfs.common.Edge;

import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    static boolean hasNegativeCycle(ArrayList<Edge>[] adj) {
        // write your code here
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Edge>[] adj = (ArrayList<Edge>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(new Edge(y - 1, w));
        }
        System.out.println(hasNegativeCycle(adj) ? 1 : 0); // TODO refactor
    }
}
