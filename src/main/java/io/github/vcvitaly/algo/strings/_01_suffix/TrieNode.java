package io.github.vcvitaly.algo.strings._01_suffix;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrieNode {
    public static final int ROOT_VALUE = 0;

    int value;
    Map<Character, TrieNode> edges;

    public TrieNode() {
        this(ROOT_VALUE);
    }

    public TrieNode(int value) {
        this.value = value;
        edges = new LinkedHashMap<>();
    }

    public boolean hasEdge(char label) {
        return edges.containsKey(label);
    }

    public boolean isALeaf() {
        return value != ROOT_VALUE && edges.isEmpty();
    }

    public TrieNode followEdge(char label) {
        return edges.get(label);
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "value=" + value +
                ", edges=" + edges +
                '}';
    }
}
