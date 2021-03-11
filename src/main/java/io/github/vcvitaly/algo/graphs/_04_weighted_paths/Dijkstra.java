package io.github.vcvitaly.algo.graphs._04_weighted_paths;

import io.github.vcvitaly.algo.graphs._03_bfs.common.Edge;
import io.github.vcvitaly.algo.graphs._03_bfs.common.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Dijkstra {

    public static final int NONE = -1;

    static int distance(ArrayList<Edge>[] adj, int s, int t) {
        // Prepare
        Vertex[] vertices = new Vertex[adj.length];
        Set<Vertex> set = new HashSet<>();

        for (int i = 0; i < adj.length; i++) {
            Vertex v = new Vertex(i, Integer.MAX_VALUE);
            v.prev = NONE;
            vertices[i] = v;
        }

        PriorityQueue<Vertex> q = new PriorityQueue<>();
        vertices[s].minDistance = 0;
        q.add(vertices[s]);

        // Execute the algorithm
        while (!q.isEmpty()) {
            Vertex u = q.poll();
            set.add(u);
            for (Edge edge : adj[u.u]) {
                Vertex neighbor = vertices[edge.v];
                if (neighbor.minDistance > u.minDistance + edge.weight) {
                    q.remove(neighbor);
                    neighbor.minDistance = u.minDistance + edge.weight;
                    neighbor.prev = u.u;
                    q.add(neighbor);
                }
            }
        }

        return vertices[t].minDistance > 0 && vertices[t].minDistance < Integer.MAX_VALUE ? vertices[t].minDistance : NONE;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}
