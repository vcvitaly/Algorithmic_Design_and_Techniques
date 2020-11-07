package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.strings._01_suffix.common.Edge;
import io.github.vcvitaly.algo.strings._01_suffix.common.PatternTrieBuilder;
import io.github.vcvitaly.algo.strings._01_suffix.common.TrieNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TrieConstruction {

    private final PatternTrieBuilder trieBuilder;

    public TrieConstruction(PatternTrieBuilder trieBuilder) {
        this.trieBuilder = trieBuilder;
    }

    static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    TrieNode<Character> buildTrie(String[] patterns) {
        return trieBuilder.buildTrie(patterns);
    }

    List<Edge> edges(TrieNode<Character> trie) {
        Queue<TrieNode<Character>> queue = new LinkedList<>();
        List<Edge> allEdges = new LinkedList<>();

        queue.add(trie);

        while (!queue.isEmpty()) {
            TrieNode<Character> node = queue.poll();
            Set<Map.Entry<Character, TrieNode<Character>>> nodeEdges = node.edges.entrySet();
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

    private Edge toEdge(int u, Map.Entry<Character, TrieNode<Character>> entry) {
        int v = entry.getValue().value;
        Character label = entry.getKey();
        return new Edge(u, v, label);
    }

    private void print(List<Edge> edges) {
        for (Edge edge : edges) {
            System.out.printf("%d->%d:%s%n", edge.u, edge.v, edge.label);
        }
    }

    private void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        TrieNode<Character> trie = buildTrie(patterns);
        print(edges(trie));
    }

    public static void main(String[] args) throws IOException {
        new TrieConstruction(new PatternTrieBuilder()).run();
    }
}
