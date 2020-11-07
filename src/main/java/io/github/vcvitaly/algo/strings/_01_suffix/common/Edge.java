package io.github.vcvitaly.algo.strings._01_suffix.common;

import java.util.Objects;

public class Edge<T> {
    public int u;
    public int v;
    public T label;

    public Edge(int u, int v, T label) {
        this.u = u;
        this.v = v;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "u=" + u +
                ", v=" + v +
                ", label=" + label +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return u == edge.u &&
                v == edge.v &&
                label == edge.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, label);
    }
}
