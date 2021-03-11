package io.github.vcvitaly.algo.graphs._03_bfs.common;

public class Vertex implements Comparable<Vertex> {
    public int prev;
    public int u;
    public int minDistance; // distance to v at some step, at the beginning it's a distance to itself, so it's 0

    public Vertex(int u, int minDistance) {
        this.u = u;
        this.minDistance = minDistance;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "prev=" + prev +
                ", u=" + u +
                ", minDistance=" + minDistance +
                '}';
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(minDistance, o.minDistance);
    }
}
