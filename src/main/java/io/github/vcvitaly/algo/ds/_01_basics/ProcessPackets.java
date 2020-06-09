package io.github.vcvitaly.algo.ds._01_basics;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Request {
    public int arrivalTime;
    public int processTime;

    public Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "arrivalTime=" + arrivalTime +
                ", processTime=" + processTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return arrivalTime == request.arrivalTime &&
                processTime == request.processTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalTime, processTime);
    }
}

class Response {
    public boolean dropped;
    public int startTime;

    public Response(boolean dropped, int startTime) {
        this.dropped = dropped;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Response{" +
                "dropped=" + dropped +
                ", startTime=" + startTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return dropped == response.dropped &&
                startTime == response.startTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dropped, startTime);
    }
}

class Buffer {
    static final int PACKET_WAS_DROPPED_START_TIME = -1;
    private int size;
    private Deque<Integer> finishTimeQ;

    public Buffer(int size) {
        this.size = size;
        this.finishTimeQ = new LinkedList<>();
    }

    public Response process(Request request) {
//        System.out.println("Processing " + request);

        while (!finishTimeQ.isEmpty() && finishTimeQ.peek() <= request.arrivalTime) {
            finishTimeQ.poll();
        }

        if (finishTimeQ.size() == size) {
            Response response = new Response(true, PACKET_WAS_DROPPED_START_TIME);
//            System.out.println("Buffer is full, dropping the request: " + response);
            return response;
        }

        int startTime = finishTimeQ.isEmpty() ? request.arrivalTime : finishTimeQ.peekLast();
        finishTimeQ.add(startTime + request.processTime);

        Response response = new Response(false, startTime);
//        System.out.println("Processing the request: " + request);
        return response;
    }
}

class ProcessPackets {
    static List<Request> readQueries(Scanner scanner) {
        int requestsCount = scanner.nextInt();
        List<Request> requests = new LinkedList<>();
        for (int i = 0; i < requestsCount; ++i) {
            int arrivalTime = scanner.nextInt();
            int processTime = scanner.nextInt();
            requests.add(new Request(arrivalTime, processTime));
        }
        return requests;
    }

    static List<Response> processRequests(List<Request> requests, Buffer buffer) {
        List<Response> responses = new LinkedList<>();
        for (Request request : requests) {
            responses.add(buffer.process(request));
        }
        return responses;
    }

    private static void printResponses(List<Response> responses) {
        for (Response response : responses) {
            if (response.dropped) {
                System.out.println(Buffer.PACKET_WAS_DROPPED_START_TIME);
            } else {
                System.out.println(response.startTime);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bufferMaxSize = scanner.nextInt();
        Buffer buffer = new Buffer(bufferMaxSize);

        List<Request> requests = readQueries(scanner);
        List<Response> responses = processRequests(requests, buffer);
        printResponses(responses);
    }
}
