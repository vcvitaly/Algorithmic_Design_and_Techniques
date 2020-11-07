package io.github.vcvitaly.algo.strings._01_suffix.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PatternTrieCommon {

    public TrieNode<Character> buildTrie(String[] patterns) {
        AtomicInteger counter = new AtomicInteger(1);
        TrieNode<Character> root = new TrieNode<>();

        for (String pattern : patterns) {
            TrieNode<Character> currentNode = root;

            for (char currentSymbol : pattern.toCharArray()) {
                if (currentNode.edges.containsKey(currentSymbol)) {
                    currentNode = currentNode.edges.get(currentSymbol);
                } else {
                    TrieNode<Character> newNode = new TrieNode<>(counter.getAndIncrement());
                    currentNode.edges.put(currentSymbol, newNode);
                    currentNode = newNode;
                }
            }
        }

        return root;
    }

    private  <T> Edge<T> toEdge(int u, Map.Entry<T, TrieNode<T>> entry) {
        int v = entry.getValue().value;
        T label = entry.getKey();
        return new Edge(u, v, label);
    }

    public <T> List<Edge<T>> edges(TrieNode<T> trie) {
        Queue<TrieNode<T>> queue = new LinkedList<>();
        List<Edge<T>> allEdges = new LinkedList<>();

        queue.add(trie);

        while (!queue.isEmpty()) {
            TrieNode<T> node = queue.poll();
            Set<Map.Entry<T, TrieNode<T>>> nodeEdges = node.edges.entrySet();
            allEdges.addAll(
                    nodeEdges.stream()
                            .map(entry -> toEdge(node.value, entry))
                            .collect(Collectors.toList())
            );
            queue.addAll(
                    nodeEdges.stream()
                            .map(Map.Entry::getValue)
                            .collect(Collectors.toList())
            );
        }
        return allEdges;
    }
}
