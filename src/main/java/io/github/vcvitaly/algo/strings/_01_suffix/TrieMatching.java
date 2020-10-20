package io.github.vcvitaly.algo.strings._01_suffix;

import io.github.vcvitaly.algo.strings._01_suffix.common.PatternTrieBuilder;
import io.github.vcvitaly.algo.strings._01_suffix.common.TrieNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TrieMatching implements Runnable {

    List<Integer> solve(String text, String[] patterns) {
        List<Integer> result = new ArrayList<>();

        TrieNode<Character> root = new PatternTrieBuilder().buildTrie(patterns);

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            TrieNode<Character> currentNode = root;
            for (int j = i; j < chars.length && currentNode.hasEdge(chars[j]); j++) {
                currentNode = currentNode.followEdge(chars[j]);
            }
            if (currentNode.isALeaf()) {
                result.add(i);
            }
        }

        return result;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String text = in.readLine();
            int n = Integer.parseInt(in.readLine());
            String[] patterns = new String[n];
            for (int i = 0; i < n; i++) {
                patterns[i]= in.readLine();
            }

            List<Integer> ans = solve(text, patterns);

            for (int j = 0; j < ans.size(); j++) {
                System.out.print("" + ans.get(j));
                System.out.print(j + 1 < ans.size() ? " " : "\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Thread(new TrieMatching()).start();
    }
}
