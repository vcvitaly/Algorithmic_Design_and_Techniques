package io.github.vcvitaly.algo.design._03_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CoveringSegments {

    static int[] optimalPoints(Segment[] segments) {
        List<Segment> segmentList = new LinkedList<>(Arrays.asList(segments));
        List<Integer> points = new LinkedList<>();

        segmentList.sort(Comparator.comparingInt(s -> s.end));

        while (!segmentList.isEmpty()) {
            Segment segment = segmentList.remove(0);
            List<Segment> intersectingSegments = segmentList.stream()
                    .filter(s -> s.start <= segment.end)
                    .collect(Collectors.toList());

            points.add(segment.end);

            segmentList.removeAll(intersectingSegments);
        }

        return points.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
