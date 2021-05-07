package io.github.vcvitaly.algo.graphs._05_mst;

import io.github.vcvitaly.algo.graphs._05_mst.common.DisjointSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConnectingPoints {

    static double minimumDistance(int[] x, int[] y) {
        List<Edge> allEdges = pointsToEdges(x, y);
        DisjointSet disjointSet = new DisjointSet();
        for (int i = 0; i < x.length; i++) {
            disjointSet.makeSet(i);
        }

        List<Edge> mst = new ArrayList<>();
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
}
