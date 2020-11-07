package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.strings._01_suffix.common.Edge;
import io.github.vcvitaly.algo.strings._01_suffix.common.PatternTrieCommon;
import io.github.vcvitaly.algo.strings._01_suffix.common.TrieNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

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

    List<Edge<Character>> edges(TrieNode<Character> trie) {
        return trieCommon.edges(trie);
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
}
