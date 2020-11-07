package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.strings._01_suffix.common.TrieNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class SuffixTree {
    static final String TERMINAL_SIGN = "$";

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

    // Build a suffix tree of the string text and return a list
    // with all of the labels of its edges (the corresponding
    // substrings of the text) in any order.
    public List<String> computeSuffixTreeEdges(String text) {
        List<String> result = new ArrayList<>();

        SuffixTrie trie = new SuffixTrie();
        for (int i = 0; i < text.length(); i++) {
            trie.add(text.substring(i));
        }

        // Implement this function yourself
        return result;
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
    }

    public static void main(String[] args) throws IOException {
        new SuffixTree().run();
    }

    static class SuffixTrie {
        private final AtomicInteger counter = new AtomicInteger(1);
        TrieNode<String> root;

        public SuffixTrie() {
            root = new TrieNode<>();
        }

        void add(String s) {
            TrieNode<String> currentNode = root;

            for (char c : s.toCharArray()) {
                String currentSymbol = String.valueOf(c);
                if (currentNode.edges.containsKey(currentSymbol)) {
                    currentNode = currentNode.edges.get(currentSymbol);
                } else {
                    TrieNode<String> newNode = new TrieNode<>(counter.getAndIncrement());
                    currentNode.edges.put(currentSymbol, newNode);
                    currentNode = newNode;
                }
            }
        }

        void compress() {
            Queue<TrieNode<String>> queue = new LinkedList<>();
            queue.add(root);

            StringBuilder buf = new StringBuilder();
            TrieNode<String> u = root, v = null;

            while (!queue.isEmpty()) {
                v = queue.poll();
                if (v.edges.size() != 1) { // if is a leaf or has many children - a compressed label has to be applied
                    if (buf.length() > 0) {
                        u.edges.clear();
                        u.edges.put(buf.toString(), v);
                        buf = new StringBuilder();
                    }
                    if (v.edges.size() > 1) {
                        queue.addAll(v.edges.values());
                        u = queue.peek();
                    }
                } else {
                    Map.Entry<String, TrieNode<String>> next = v.edges.entrySet().iterator().next();
                    buf.append(next.getKey());
                    queue.add(next.getValue());
                }
            }
        }
    }
}
