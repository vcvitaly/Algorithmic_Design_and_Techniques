package io.github.vcvitaly.algo.strings._01_suffix;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrieNode {
    int value;
    Map<Character, TrieNode> edges;

    public TrieNode(int value) {
        this.value = value;
        edges = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "value=" + value +
                ", edges=" + edges +
                '}';
    }
}
