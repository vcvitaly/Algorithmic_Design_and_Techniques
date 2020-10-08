package io.github.vcvitaly.algo.strings._01_suffix;

import java.util.concurrent.atomic.AtomicInteger;

public class PatternTrieBuilder {
    private static final int ROOT_VALUE = 0;

    TrieNode buildTrie(String[] patterns) {
        AtomicInteger counter = new AtomicInteger(1);
        TrieNode root = new TrieNode(ROOT_VALUE);

        for (String pattern : patterns) {
            TrieNode currentNode = root;

            for (char currentSymbol : pattern.toCharArray()) {
                if (currentNode.edges.containsKey(currentSymbol)) {
                    currentNode = currentNode.edges.get(currentSymbol);
                } else {
                    TrieNode newNode = new TrieNode(counter.getAndIncrement());
                    currentNode.edges.put(currentSymbol, newNode);
                    currentNode = newNode;
                }
            }
        }

        return root;
    }
}
