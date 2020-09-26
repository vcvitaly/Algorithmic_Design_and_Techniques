package io.github.vcvitaly.algo.strings._01_suffix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TrieConstruction {
    private static final int ROOT_NODE = 0;

    class FastScanner {
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

    List<Edge> buildTrie(String[] patterns) {
        List<Edge> trie = new ArrayList<>();

        for (String s : patterns) {
            String[] pattern = s.split("");
            int currentNode = ROOT_NODE;

        }

        return trie;
    }

    static public void main(String[] args) throws IOException {
        new TrieConstruction().run();
    }

    private void print(List<Edge> trie) {
        for (Edge edge : trie) {
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
        List<Edge> trie = buildTrie(patterns);
        print(trie);
    }

    static class Edge {
        int u;
        int v;
        char label;

        public Edge(int u, int v, char label) {
            this.u = u;
            this.v = v;
            this.label = label;
        }
    }
}
