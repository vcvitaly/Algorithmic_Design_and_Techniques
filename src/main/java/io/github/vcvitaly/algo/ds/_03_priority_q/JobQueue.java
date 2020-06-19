package io.github.vcvitaly.algo.ds._03_priority_q;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class JobQueue {
    private int numWorkers;
    private Job[] jobs;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new Job[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = new Job(in.nextInt());
        }
    }

    private void writeResponse(ProcessingEvent[] processingEvents) {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(String.format("%d %d", processingEvents[i].threadIndex, processingEvents[i].startTime));
        }
    }

    ProcessingEvent[] assignJobsNaive(int numWorkers, Job[] jobs) {
        // TODO: replace this code with a faster algorithm.
        ProcessingEvent[] processingEvents = new ProcessingEvent[jobs.length];

        ThreadInfo[] threads = IntStream.range(0, numWorkers).mapToObj(i -> new ThreadInfo()).toArray(ThreadInfo[]::new);

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i].duration;
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (threads[j].nextFreeTime < threads[bestWorker].nextFreeTime)
                    bestWorker = j;
            }
            processingEvents[i] = new ProcessingEvent(bestWorker, threads[bestWorker].nextFreeTime);
            threads[bestWorker].nextFreeTime += duration;
        }

        return processingEvents;
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        ProcessingEvent[] processingEvents = assignJobsNaive(numWorkers, jobs);
        writeResponse(processingEvents);
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    static class Job {
        int duration;

        public Job(int processingTime) {
            this.duration = processingTime;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "processingTime=" + duration +
                    '}';
        }
    }

    static class ThreadInfo {
        int nextFreeTime;

        @Override
        public String toString() {
            return "Thread{" +
                    "nextFreeTime=" + nextFreeTime +
                    '}';
        }
    }

    static class ProcessingEvent {
        int threadIndex;
        long startTime;

        public ProcessingEvent(int threadIndex, long startTime) {
            this.threadIndex = threadIndex;
            this.startTime = startTime;
        }

        @Override
        public String toString() {
            return "ProcessingEvent{" +
                    "threadIndex=" + threadIndex +
                    ", startTime=" + startTime +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProcessingEvent that = (ProcessingEvent) o;
            return threadIndex == that.threadIndex &&
                    startTime == that.startTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(threadIndex, startTime);
        }
    }
}
