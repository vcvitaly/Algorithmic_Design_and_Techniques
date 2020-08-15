package io.github.vcvitaly.algo.ds._03_priority_q;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class JobQueue {
    private FastScanner in;
    private PrintWriter out;

    public JobQueue(FastScanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public static void main(String[] args) throws IOException {

        new JobQueue(
                new FastScanner(), new PrintWriter(new BufferedOutputStream(System.out))
        ).solve();
    }

    private Task readData() throws IOException {
        int numWorkers = in.nextInt();
        int m = in.nextInt();
        Job[] jobs = new Job[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = new Job(in.nextInt());
        }

        return new Task(numWorkers, jobs);
    }

    private void writeResponse(ProcessingEvent[] processingEvents) {
        for (int i = 0; i < processingEvents.length; ++i) {
            out.println(String.format("%d %d", processingEvents[i].threadIndex, processingEvents[i].startTime));
        }
    }

    ProcessingEvent[] assignJobsNaive(int numWorkers, Job[] jobs) {
        ProcessingEvent[] processingEvents = new ProcessingEvent[jobs.length];

        ThreadInfo[] threads = IntStream.range(0, numWorkers).mapToObj(ThreadInfo::new).toArray(ThreadInfo[]::new);

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

    ProcessingEvent[] assignJobsFast(int numWorkers, Job[] jobs) {
        ProcessingEvent[] processingEvents = new ProcessingEvent[jobs.length];

        Heap<ThreadInfo> threads = new Heap<>(
                IntStream.range(0, numWorkers)
                        .mapToObj(ThreadInfo::new)
                        .toArray(ThreadInfo[]::new)
        );

        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i].duration;
            ThreadInfo bestWorker = threads.extractMax();
            processingEvents[i] = new ProcessingEvent(bestWorker.threadIndex, bestWorker.nextFreeTime);
            bestWorker.nextFreeTime += duration;
            threads.insert(bestWorker);
        }

        return processingEvents;
    }

    public void solve() throws IOException {
        Task task = readData();
        ProcessingEvent[] processingEvents = assignJobsFast(task.numWorkers, task.jobs);
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

    static class Task {
        int numWorkers;
        Job[] jobs;

        public Task(int numWorkers, Job[] jobs) {
            this.numWorkers = numWorkers;
            this.jobs = jobs;
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

    static class ThreadInfo implements Comparable<ThreadInfo> {
        int threadIndex;
        long nextFreeTime;

        ThreadInfo(int threadIndex) {
            this.threadIndex = threadIndex;
        }

        ThreadInfo(int threadIndex, int nextFreeTime) {
            this.threadIndex = threadIndex;
            this.nextFreeTime = nextFreeTime;
        }

        @Override
        public String toString() {
            return "Thread{" +
                    "i=" + threadIndex +
                    ", nfT=" + nextFreeTime +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ThreadInfo that = (ThreadInfo) o;
            return threadIndex == that.threadIndex &&
                    nextFreeTime == that.nextFreeTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(threadIndex, nextFreeTime);
        }

        @Override
        public int compareTo(ThreadInfo o) {
            int compareTime = Long.compare(nextFreeTime, o.nextFreeTime) * -1;
            return compareTime != 0 ? compareTime : Integer.compare(threadIndex, o.threadIndex) * -1;
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

    static class  Heap<E extends Comparable<E>> {
        private static final int INITIAL_SIZE = 2;
        private static final int NONE = -1;
        private E[] a;
        private int boundaryIndex;

        public Heap(E[] a) {
            this.a = a;
            boundaryIndex = a.length - 1;
            buildHeap();
        }

        @SuppressWarnings("unchecked")
        public Heap() {
            a = (E[]) new Comparable[INITIAL_SIZE];
            boundaryIndex = NONE;
        }

        public int size() {
            return boundaryIndex + 1;
        }

        E extractMax() {
            if (boundaryIndex == NONE) {
                return null;
            }

            E result = a[0];
            a[0] = a[boundaryIndex];
            --boundaryIndex;
            siftDown(0);
            return result;
        }

        E peekMax() {
            return a[0];
        }

        E peekMin() {
            return a[boundaryIndex];
        }

        void insert(E e) {
            if (boundaryIndex == Integer.MAX_VALUE) {
                throw new ArrayIndexOutOfBoundsException(
                        String.format("Internal heap array has reached it maximum capacity %d, cannot add %s", Integer.MAX_VALUE, e)
                );
            }
            if (boundaryIndex + 1 == a.length) {
                resize();
            }
            a[++boundaryIndex] = e;
            siftUp(boundaryIndex);
        }

        void siftUp (int i) {
            int parent = parent(i);
            while (i > 0 && a[i].compareTo(a[parent]) > 0) {
                swap(parent, i);
                i = parent;
                parent = parent(i);
            }
        }

        void siftDown(int i) {
            int maxIndex;
            while (i != (maxIndex = maxIndex(i))) {
                swap(i, maxIndex);
                i = maxIndex;
            }
        }

        private void buildHeap() {
            for (int i = a.length / 2; i >= 0; i--) {
                siftDown(i);
            }
        }

        private int maxIndex(int i) {
            int maxIndex = i;

            int l = leftChild(i);
            if (hasChild(l) && a[l].compareTo(a[maxIndex]) > 0) {
                maxIndex = l;
            }

            int r = rightChild(i);
            if (hasChild(r) && a[r].compareTo(a[maxIndex]) > 0) {
                maxIndex = r;
            }

            return maxIndex;
        }

        private boolean hasChild(int childIndex) {
            return childIndex < a.length;
        }

        private int parent(int i) {
            return (i - 1) / 2;
        }

        private int leftChild(int i) {
            return i * 2 + 1;
        }

        private int rightChild(int i) {
            return leftChild(i) + 1;
        }

        private void swap(int i, int j) {
            E tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        private void resize() {
            a = Arrays.copyOf(a, (int) (a.length * 1.5));
        }

        @Override
        public String toString() {
            return "Heap{" + Arrays.toString(Arrays.copyOfRange(a, 0, boundaryIndex + 1)) + "}";
        }
    }
}
