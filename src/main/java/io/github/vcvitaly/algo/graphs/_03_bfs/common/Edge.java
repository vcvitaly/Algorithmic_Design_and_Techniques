package io.github.vcvitaly.algo.graphs._03_bfs.common;

import java.util.Objects;

public class Edge {
    public int v;
    public int weight;

    public Edge(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Edge{->%d, (%d)}", v, weight);
    }
}
