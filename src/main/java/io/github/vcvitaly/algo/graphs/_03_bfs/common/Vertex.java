package io.github.vcvitaly.algo.graphs._03_bfs.common;

import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    public int prev;
    public int u;
    public int minDistance; // distance to v at some step, at the beginning it's a distance to itself, so it's 0

    public Vertex(int prev, int u, int minDistance) {
        this.prev = prev;
        this.u = u;
        this.minDistance = minDistance;
    }

    public Vertex(int u, int minDistance) {
        this.u = u;
        this.minDistance = minDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return prev == vertex.prev && u == vertex.u && minDistance == vertex.minDistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prev, u, minDistance);
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
