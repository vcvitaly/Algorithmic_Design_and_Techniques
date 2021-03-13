package io.github.vcvitaly.algo.strings._01_suffix.common;

import java.util.concurrent.atomic.AtomicInteger;

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
}
