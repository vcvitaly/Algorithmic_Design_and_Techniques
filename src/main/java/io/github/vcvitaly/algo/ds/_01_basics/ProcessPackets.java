package io.github.vcvitaly.algo.ds._01_basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Request {
    public int arrivalTime;
    public int processTime;

    public Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }
}

class Response {
    public boolean dropped;
    public int startTime;

    public Response(boolean dropped, int startTime) {
        this.dropped = dropped;
        this.startTime = startTime;
    }
}

class Buffer {
    private int size;
    private List<Integer> finishTime;

    public Buffer(int size) {
        this.size = size;
        this.finishTime = new ArrayList<>();
    }

    public Response process(Request request) {
        // write your code here
        return new Response(false, -1);
    }
}

class ProcessPackets {
    static List<Request> readQueries(Scanner scanner) {
        int requestsCount = scanner.nextInt();
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < requestsCount; ++i) {
            int arrivalTime = scanner.nextInt();
            int processTime = scanner.nextInt();
            requests.add(new Request(arrivalTime, processTime));
        }
        return requests;
    }

    static List<Response> processRequests(List<Request> requests, Buffer buffer) {
        List<Response> responses = new ArrayList<>();
        for (Request request : requests) {
            responses.add(buffer.process(request));
        }
        return responses;
    }

    private static void printResponses(List<Response> responses) {
        for (Response response : responses) {
            if (response.dropped) {
                System.out.println(-1);
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
