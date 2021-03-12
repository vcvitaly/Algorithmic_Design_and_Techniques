package io.github.vcvitaly.algo.graphs._04_weighted_paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShortestPaths {

    public static final String NO_SHORTEST_PATH = "-";
    public static final String NO_PATH_AT_ALL = "*";

    static List<String> shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s) {

        long distance[] = new long[adj.length];
        int reachable[] = new int[adj.length];
        int shortest[] = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        List<String> answers = shortestPaths(adj, cost, s);
        answers.forEach(System.out::println);
    }

    // just a snippet
    public static void transform(long[] distance, int[] reachable, int[] shortest) {
        int n = 1;
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println(NO_PATH_AT_ALL);
            } else if (shortest[i] == 0) {
                System.out.println(NO_SHORTEST_PATH);
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}
