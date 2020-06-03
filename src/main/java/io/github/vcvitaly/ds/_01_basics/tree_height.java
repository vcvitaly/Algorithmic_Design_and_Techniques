package io.github.vcvitaly.ds._01_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class tree_height {
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

    public static class TreeHeight {
        private static final int ROOT_HEIGHT = 1;
        public static final int NULL = -1;
        int n;
        int parents[];

        public TreeHeight(int n, int[] parents) {
            this.n = n;
            this.parents = parents;
        }

        public TreeHeight() {
        }

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = in.nextInt();
            }
        }

        int computeHeight() {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parents[i]) height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }

        int computeHeightFast() {
            Node[] nodes = Arrays.stream(parents)
                    .mapToObj(Node::new)
                    .toArray(Node[]::new);

            Queue<Node> queue = new LinkedList<>();

            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == NULL) {
                    Node root = nodes[i];
                    root.height = ROOT_HEIGHT;
                    queue.add(root);
                } else {
                    nodes[i].parent = nodes[parents[i]];
                }
            }

//            int maxHeight = 0;

            while (!queue.isEmpty()) {
                Node node = queue.poll();

            }

            /*for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;

                Queue<Node> queue = new LinkedList<>();



                if (parent != null) {
                    nodes[vertex] = new Node(vertex, parent, parent.height + 1);
                } else {
                    int i;
                    for (i = vertex; parents[i] != -1 || nodes[i] != null; i = this.parents[i]) {

                    }
                    height  = heights[vertex] = heights[i] + 1;
                }

                maxHeight = Math.max(maxHeight, height);
            }*/
            return 0;
        }
    }

    private static class Node {
        int value;
        Node parent;
        int height;

        public Node(int value, Node parent, int height) {
            this.value = value;
            this.parent = parent;
            this.height = height;
        }

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new tree_height().run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}

