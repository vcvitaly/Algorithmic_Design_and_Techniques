package io.github.vcvitaly.algo.graphs._05_mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ConnectingPoints {
    static double minimumDistance(int[] x, int[] y) {
        List<Edge> allEdges = pointsToEdges(x, y);
        DisjointSet disjointSet = new DisjointSet();
        for (int i = 0; i < x.length; i++) {
            disjointSet.makeSet(i);
        }

        Set<Edge> mst = new HashSet<>();
        allEdges.sort(Comparator.comparingDouble(e -> e.weight));

        for (Edge edge : allEdges) {
            if (disjointSet.find(edge.u) != disjointSet.find(edge.v)) {
                mst.add(edge);
                disjointSet.union(edge.u, edge.v);
            }
        }

        return mst.stream().map(e -> e.weight).reduce(0d, Double::sum);
    }

    private static List<Edge> pointsToEdges(int[] x, int[] y) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < x.length - 1; i++) {
            for (int j = i + 1; j < x.length; j++) {
                double distance = Math.sqrt(Math.pow(x[j]-x[i], 2) + Math.pow(y[j]-y[i], 2));
                edges.add(new Edge(i, j, distance));
            }
        }

        return edges;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }

    public static class Edge {
        public int u;
        public int v;
        public double weight;

        public Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("Edge{%d->%d, (%f)}", u, v, weight);
        }
    }

    private static class DisjointSet {
        private Map<Integer, Integer> parent = new HashMap<>();
        private Map<Integer, Integer> rank = new HashMap<>();

        private void makeSet(int i) {
            parent.put(i, i);
            rank.put(i, 0);
        }

        private int find(int i) {
             if (i != parent.get(i)) {
                 parent.put(
                         i, find(parent.get(i))
                 );
             }
            return parent.get(i);
        }

        private void union(int i, int j) {
            int iId = find(i);
            int jId = find(j);
            if (iId == jId) {
                return;
            }

            if (rank.get(i) > rank.get(j)) {
                parent.put(j, iId);
            } else {
                parent.put(i, jId);
                if (rank.get(i).equals(rank.get(j))) {
                    rank.put(j, rank.get(j) + 1);
                }
            }
        }
    }
}
