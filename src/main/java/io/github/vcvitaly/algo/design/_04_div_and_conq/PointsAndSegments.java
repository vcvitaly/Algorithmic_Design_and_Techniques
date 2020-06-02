package io.github.vcvitaly.algo.design._04_div_and_conq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PointsAndSegments {

    static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        Segment[] segments = new Segment[starts.length];
        for (int i = 0; i < starts.length; i++) {
            Segment segment = new Segment(starts[i], ends[i]);
            segments[i] = segment;
        }
        Arrays.sort(segments, Comparator.comparing(segment -> segment.start));

        for (int i = 0; i < points.length; i++) {
            cnt[i] = countK(segments, points[i]);
        }

        return cnt;
    }

    static int countK(Segment[] segments, int point) {
        if (segments.length == 0) {
            return 0;
        }

        int left = 0,
            right = segments.length - 1;

        while (left <= right) {
            int mid = mid(left, right);
            if (point <= segments[mid].end) {
                if (point >= segments[mid].start) {
                    return naiveCountK(segments, left, right, point);
                } else {
                    right = mid-1;
                }
            } else {
                left = mid+1;
            }
        }

        return 0;
    }

    static int naiveCountK(Segment[] segments, int right, int left, int point) {
        int cnt = 0;
        for (int i = right; i <= left; i++) {
            if (segments[i].contains(point)) {
                cnt++;
            }
        }
        return cnt;
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
        int[] cnt = fastCountSegments(starts, ends, points);
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

        boolean contains(int point) {
            return start <= point && point <= end;
        }
    }
}
