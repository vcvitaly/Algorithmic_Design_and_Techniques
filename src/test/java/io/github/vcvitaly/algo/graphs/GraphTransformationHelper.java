package io.github.vcvitaly.algo.graphs;

import io.github.vcvitaly.algo.graphs._03_bfs.common.Edge;

import java.util.ArrayList;
import java.util.function.Function;

public class GraphTransformationHelper {

    public static ArrayList<Integer>[] edgesToAdj(int n, int[][] edges, boolean isDirectedGraph) {
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int x = edge[0],
                y = edge[1];

            adj[x - 1].add(y - 1);
            if (!isDirectedGraph) {
                adj[y - 1].add(x - 1);
            }
        }

        return adj;
    }

    public static ArrayList<Edge>[] weightedEdgesToAdj(int n, int[][] edges, boolean isDirectedGraph) {
        ArrayList<Edge>[] adj = (ArrayList<Edge>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int x = edge[0],
                y = edge[1],
                w = edge[2];

            adj[x - 1].add(new Edge(y - 1, w));
            if (!isDirectedGraph) {
                adj[y - 1].add(new Edge(x - 1, w));
            }
        }

        return adj;
    }
}
