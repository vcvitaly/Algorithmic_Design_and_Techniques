package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.strings._01_suffix.common.PatternTrieCommon;
import io.github.vcvitaly.algo.strings._01_suffix.common.TrieNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TrieConstruction {

    final PatternTrieCommon trieCommon;

    public TrieConstruction(PatternTrieCommon trieBuilder) {
        this.trieCommon = trieBuilder;
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

    private Edge<Character> toEdge(int u, Map.Entry<Character, TrieNode<Character>> entry) {
        int v = entry.getValue().value;
        Character label = entry.getKey();
        return new Edge(u, v, label);
    }

    List<Edge<Character>> edges(TrieNode<Character> trie) {
        Queue<TrieNode<Character>> queue = new LinkedList<>();
        List<Edge<Character>> allEdges = new LinkedList<>();

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

    TrieNode<Character> buildTrie(String[] patterns) {
        return trieCommon.buildTrie(patterns);
    }

    private <T> void print(List<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
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
        new TrieConstruction(new PatternTrieCommon()).run();
    }

    static class Edge<T> {
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
}
