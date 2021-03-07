package io.github.vcvitaly.algo.graphs._03_bfs;

import java.util.ArrayList;
import java.util.Scanner;

public class Bipartite {

    public static final int IS_BIPARTITE = 1;
    public static final int IS_NOT_BIPARTITE = 0;

    static boolean isBipartite(ArrayList<Integer>[] adj) {
        //write your code here
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
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

