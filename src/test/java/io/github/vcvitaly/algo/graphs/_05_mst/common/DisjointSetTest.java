package io.github.vcvitaly.algo.graphs._05_mst.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DisjointSetTest {

    private DisjointSet disjointSet = new DisjointSet();

    // Testing a scenario from ConnectingPoints sample 1
    @Test
    void simpleTest1() {
        for (int i = 0; i < 4; i++) {
            disjointSet.makeSet(i);
        }
        disjointSet.union(0,1);
        assertThat(disjointSet.find(0)).isEqualTo(1);
        assertThat(disjointSet.find(1)).isEqualTo(1);
        disjointSet.union(0,2);
        assertThat(disjointSet.find(0)).isEqualTo(1);
        assertThat(disjointSet.find(1)).isEqualTo(1);
        assertThat(disjointSet.find(2)).isEqualTo(1);
        disjointSet.union(1,3);
        assertThat(disjointSet.find(0)).isEqualTo(1);
        assertThat(disjointSet.find(1)).isEqualTo(1);
        assertThat(disjointSet.find(2)).isEqualTo(1);
        assertThat(disjointSet.find(3)).isEqualTo(1);
    }

    // Testing a scenario from ConnectingPoints sample 3
    @Test
    void simpleTest2() {
        for (int i = 0; i <= 4; i++) {
            disjointSet.makeSet(i);
        }
        disjointSet.union(0,1);
        assertThat(disjointSet.find(0)).isEqualTo(1);
        assertThat(disjointSet.find(1)).isEqualTo(1);
        disjointSet.union(2,4);
        assertThat(disjointSet.find(2)).isEqualTo(4);
        assertThat(disjointSet.find(4)).isEqualTo(4);
        disjointSet.union(0,2);
        assertThat(disjointSet.find(0)).isEqualTo(4);
        assertThat(disjointSet.find(1)).isEqualTo(4);
        assertThat(disjointSet.find(2)).isEqualTo(4);
        assertThat(disjointSet.find(4)).isEqualTo(4);
    }
}