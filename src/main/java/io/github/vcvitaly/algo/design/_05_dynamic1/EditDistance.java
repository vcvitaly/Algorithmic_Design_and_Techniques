package io.github.vcvitaly.algo.design._05_dynamic1;

import java.util.Scanner;

class EditDistance {
    private static int editDistance(char[] s, char[] t) {
        int[][] distances = new int[s.length+1][t.length+1];

        for (int i = 1; i <= s.length; i++) {
            distances[i][0] = i;
        }
        for (int j = 1; j <= t.length; j++) {
            distances[0][j] = j;
        }

        for (int j = 1; j <= t.length; j++) {
            for (int i = 1; i <= s.length; i++) {
                int insertion = distances[i][j-1] + 1;
                int deletion = distances[i-1][j] + 1;
                int replacement = s[i-1] == t[j-1] ? distances[i-1][j-1] : distances[i-1][j-1] + 1;
                distances[i][j] = Math.min(Math.min(insertion, deletion), replacement);
            }
        }

        return distances[s.length][t.length];
    }

    static int editDistance(String s, String t) {
        if (s.isEmpty()) {
            return t.length();
        }
        if (t.isEmpty()) {
            return s.length();
        }

        return editDistance(s.toCharArray(), t.toCharArray());
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(editDistance(s, t));
    }

    static class Step {
        int i;
        int j;

    }

    
}
