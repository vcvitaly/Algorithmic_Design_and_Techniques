package io.github.vcvitaly.algo.graphs;

import java.util.ArrayList;

public class GraphTransformationHelper {

    public static ArrayList<Integer>[] undirectedEdgesToAdj(int n, int[][] edges) {
        return edgesToAdj(n, edges, false);
    }

    public static ArrayList<Integer>[] directedEdgesToAdj(int n, int[][] edges) {
        return edgesToAdj(n, edges, true);
    }

    private static ArrayList<Integer>[] edgesToAdj(int n, int[][] edges, boolean isDirectedGraph) {
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int x, y;
            x = edges[i][0];
            y = edges[i][1];

            adj[x - 1].add(y - 1);
            if (!isDirectedGraph) {
                adj[y - 1].add(x - 1);
            }
        }

        return adj;
    }
}
