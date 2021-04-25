package io.github.vcvitaly.algo.graphs._02_decomposition2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        int sccCount = 0;

        ArrayList<Integer>[] adjReversed = adjReversed(adj);

        Node[] nodesWithPostOrder = dfs(adjReversed);
        Arrays.sort(nodesWithPostOrder, Comparator.reverseOrder());

        boolean[] visited = new boolean[adj.length];

        for (Node orderedNode : nodesWithPostOrder) {
            if (!visited[orderedNode.index]) {
                Stack<Integer> stack = new Stack<>();
                stack.add(orderedNode.index);

                while (!stack.isEmpty()) {
                    int node = stack.peek();
                    if (!visited[node]) {
                        for (int neighbor : adj[node]) {
                            stack.push(neighbor);
                        }
                        visited[node] = true;
                    } else {
                        stack.pop();
                    }
                }

                sccCount++;
            }
        }


        return sccCount;
    }

    private static ArrayList<Integer>[] adjReversed(ArrayList<Integer>[] adj) {
        ArrayList<Integer>[] adjReversed = (ArrayList<Integer>[])new ArrayList[adj.length];
        for (int i = 0; i < adjReversed.length; i++) {
            adjReversed[i] = new ArrayList<>();
        }
        for (int u = 0; u < adj.length; u++) {
            for (int j = 0; j < adj[u].size(); j++) {
                int v = adj[u].get(j);
                adjReversed[v].add(u);
            }
        }

        return adjReversed;
    }

    private static Node[] dfs(ArrayList<Integer>[] adj) {
        Node[] nodesWithPostOrder = new Node[adj.length];
        boolean[] visited = new boolean[adj.length];
        int clock = 0;

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                Stack<Integer> stack = new Stack<>();
                stack.add(i);

                while (!stack.isEmpty()) {
                    int node = stack.peek();
                    if (!visited[node]) {
                        for (int neighbor : adj[node]) {
                            stack.push(neighbor);
                        }
                        visited[node] = true;
                    } else {
                        stack.pop();
                        nodesWithPostOrder[node] = new Node(node, ++clock);
                    }
                }
            }
        }

        return nodesWithPostOrder;
    }

    private static class Node implements Comparable<Node> {
        private int index;
        private int postOrder;

        public Node(int index, int postOrder) {
            this.index = index;
            this.postOrder = postOrder;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(postOrder, o.postOrder);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}
