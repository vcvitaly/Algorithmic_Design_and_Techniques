package io.github.vcvitaly.algo.strings._01_suffix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
            Set<String> edgeLabels = node.neighbors.stream().map(e -> e.label).collect(Collectors.toSet());
            allEdgeLabels.addAll(edgeLabels);

            Collection<TrieNode<String>> childNodes = node.neighbors;
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
            root = TrieNode.root();
        }

        void add(String s) {
            TrieNode<String> currentNode = root;

            for (char c : s.toCharArray()) {
                String currentSymbol = String.valueOf(c);
                if (currentNode.hasChildNode(currentSymbol)) {
                    currentNode = currentNode.getChildNode(currentSymbol);
                } else {
                    TrieNode<String> newNode = new TrieNode<>(currentSymbol, counter++);
                    currentNode.addChildNode(newNode);
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
                if (v.childCount() != 1) { // if is a leaf or has many children - a compressed label (from the buf) has to be applied
                    if (buf.length() > 0) {
                        buf.append(v.label);
                        v.label = buf.toString();
                        String keyPrefixForRemoval = String.valueOf(buf.toString().charAt(0));
                        u.removeChildNode(keyPrefixForRemoval);
                        u.addChildNode(v);
                        buf = new StringBuilder();
                    }
                    if (v.hasChildren()) {
                        stack.addAll(v.neighbors);
                        u = v; // make v the current node
                    }
                } else {
                    buf.append(v.label);
                    TrieNode<String> next = v.neighbors.get(0);
                    stack.push(next);
                }
            }
        }
    }
    
    static class TrieNode<T> {
        public static final int ROOT_VALUE = 0;

        public int value;
        public T label;
        public List<TrieNode<T>> neighbors;

        public TrieNode(T label, int value) {
            this.label = label;
            this.value = value;
            neighbors = new LinkedList<>();
        }

        public static <T> TrieNode<T> root() {
            return new TrieNode<>(null, ROOT_VALUE);
        }

        public boolean hasChildNode(T label) {
            return neighbors.stream().anyMatch(node -> node.label.equals(label));
        }

        public boolean hasChildren() {
            return !neighbors.isEmpty();
        }

        public void removeChildNode(T label) {
            neighbors.removeIf(node -> node.label.equals(label));
        }

        public TrieNode<T> getChildNode(T label) {
            return neighbors.stream().filter(node -> node.label.equals(label)).findFirst().get();
        }

        public void addChildNode(TrieNode<T> node) {
            neighbors.add(node);
        }

        public int childCount() {
            return neighbors.size();
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "label=" + label +
                    ", edges=" + neighbors +
                    '}';
        }
    }
}
