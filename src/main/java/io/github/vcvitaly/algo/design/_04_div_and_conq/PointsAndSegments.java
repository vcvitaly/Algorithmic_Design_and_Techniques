package io.github.vcvitaly.algo.design._04_div_and_conq;

import java.util.Scanner;

public class PointsAndSegments {

    static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        Segment[] segments = new Segment[starts.length];
        for (int i = 0; i < starts.length; i++) {
            Segment segment = new Segment(starts[i], ends[i]);
            segments[i] = segment;
        }
//        Arrays.sort(segments, Comparator.comparing(Segment::getStart));

        for (int i = 0; i < points.length; i++) {
            cnt[i] = search(segments, 0, segments.length - 1, points[i]);
        }

        return cnt;
    }

    static int search (Segment[] segments, int left, int right, int point) {
        if (left == right) {
            return segments[left].start <= point && point <= segments[left].end ? 1 : 0;
        }

        int mid = mid(left, right);

        return search(segments, left, mid, point) + search(segments, mid + 1, right, point);
    }

    private static int mid(int left, int right) {
        return left + (right - left) / 2;
    }

    static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = naiveCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /*public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }*/
    }
}
