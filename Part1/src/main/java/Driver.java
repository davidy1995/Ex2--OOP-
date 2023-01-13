package main.java;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
public class Driver {
    public static void main(String[] args) {
        //Write file without thread
        String[] files = Ex2_1.createTextFiles(3000, 1, 10000);

        List<String> time = new ArrayList<>();
        // Read file without thread
        Instant start = Instant.now();
        System.out.println("=============Without Threads============");
        int numberOfLines = Ex2_1.getNumOfLines(files);
        Instant end = Instant.now();
        time.add("*****Time without Threads*****");
        time.add(Duration.between(start, end).toMillis() + " miliseconds");


        //Read file with thread
        start = Instant.now();
        System.out.println("=============With Threads============");
        numberOfLines = Ex2_1.getNumOfLinesThreads(files);
        end = Instant.now();
        time.add("*****Time with Threads*****");
        time.add(Duration.between(start, end).toMillis() + " miliseconds");

        //Read files with thread pool
        start = Instant.now();
        numberOfLines = Ex2_1.getNumOfLinesThreadPool(files);
        end = Instant.now();
        time.add("=============With Thread Pool============");
        time.add(Duration.between(start, end).toMillis() + " miliseconds");

        System.out.println("===================Execution time=================");
        time.stream().forEach(t -> {
            System.out.println(t);
        });
    }
}
