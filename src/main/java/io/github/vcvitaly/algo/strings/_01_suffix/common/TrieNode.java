package io.github.vcvitaly.algo.strings._01_suffix.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrieNode<T> {
    public static final int ROOT_VALUE = 0;

    public int value;
    public T label;
    public Map<T, TrieNode<T>> edges;

    public TrieNode(T label, int value) {
        this.label = label;
        this.value = value;
        edges = new LinkedHashMap<>();
    }

    public static <T> TrieNode<T> root() {
        return new TrieNode<>(null, ROOT_VALUE);
    }

    public boolean hasEdge(T label) {
        return edges.containsKey(label);
    }

    public boolean hasChildren() {
        return !edges.isEmpty();
    }

    public boolean isALeaf() {
        return value != ROOT_VALUE && edges.isEmpty();
    }

    public TrieNode<T> followEdge(T label) {
        return edges.get(label);
    }

    public void removeEdge(T label) {
        edges.remove(label);
    }

    public void addEdge(T label, TrieNode<T> node) {
        edges.put(label, node);
    }

    public int edgeCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "value=" + value +
                ", edges=" + edges +
                '}';
    }
}
