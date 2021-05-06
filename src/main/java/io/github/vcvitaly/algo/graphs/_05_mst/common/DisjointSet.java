package io.github.vcvitaly.algo.graphs._05_mst.common;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private Map<Integer, Integer> parents = new HashMap<>();
    private Map<Integer, Integer> ranks = new HashMap<>();

    public void makeSet(int i) {
        parents.put(i, i);
        ranks.put(i, 0);
    }

    public int find(int i) {
        if (i != parents.get(i)) {
            parents.put(
                    i, find(parents.get(i))
            );
        }
        return parents.get(i);
    }

    public void union(int i, int j) {
        int iId = find(i);
        int jId = find(j);
        if (iId == jId) {
            return;
        }

        if (ranks.get(iId) > ranks.get(jId)) {
            parents.put(j, iId);
        } else {
            parents.put(i, jId);
            if (ranks.get(iId).equals(ranks.get(jId))) {
                ranks.put(j, ranks.get(j) + 1);
            }
        }
    }
}
