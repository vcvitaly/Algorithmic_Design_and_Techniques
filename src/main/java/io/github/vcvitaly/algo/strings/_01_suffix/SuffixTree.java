package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.strings._01_suffix.common.TrieNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

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

    public List<String> edges(TrieNode<String> trie) {
        Queue<TrieNode<String>> queue = new LinkedList<>();
        List<String> allEdgeLabels = new LinkedList<>();

        queue.add(trie);

        while (!queue.isEmpty()) {
            TrieNode<String> node = queue.poll();
            Set<String> edgeLabels = node.edges.keySet();
            allEdgeLabels.addAll(edgeLabels);

            Collection<TrieNode<String>> childNodes = node.edges.values();
            queue.addAll(childNodes);
        }
        return allEdgeLabels;
    }

    // Build a suffix tree of the string text and return a list
    // with all of the labels of its edges (the corresponding
    // substrings of the text) in any order.
    public List<String> computeSuffixTreeEdges(String text) {
        SuffixTrie trie = new SuffixTrie();
        for (int i = 0; i < text.length(); i++) {
            String substring = text.substring(i);
            trie.add(substring);
        }

        trie.compress();

        return edges(trie.root);
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
        private int counter = 1;
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
                    TrieNode<String> newNode = new TrieNode<>(counter++);
                    currentNode.edges.put(currentSymbol, newNode);
                    currentNode = newNode;
                }
            }
        }

        void compress() {
            Stack<TrieNode<String>> stack = new Stack<>();
            stack.push(root);

            StringBuilder buf = new StringBuilder();
            TrieNode<String> u = root;

            while (!stack.isEmpty()) {
                TrieNode<String> v = stack.pop();
                if (v.edgeCount() != 1) { // if is a leaf or has many children - a compressed label (from the buf) has to be applied
                    if (buf.length() > 0) {
                        String keyPrefixForRemoval = String.valueOf(buf.toString().charAt(0));
                        u.removeEdge(keyPrefixForRemoval);
                        u.addEdge(buf.toString(), v);
                        buf = new StringBuilder();
                    }
                    if (v.hasChildren()) {
                        stack.addAll(v.edges.values());
                        u = v; // make v the current node
                    }
                } else {
//                    if (u.edges.containsKey(v.))
                    Map.Entry<String, TrieNode<String>> next = v.edges.entrySet().iterator().next();
                    buf.append(next.getKey());
                    stack.push(next.getValue());
                }
            }
        }

        private String getKeyPrefixForRemoval(StringBuilder buf) {
            return String.valueOf(buf.toString().charAt(0));
        }
    }
}
