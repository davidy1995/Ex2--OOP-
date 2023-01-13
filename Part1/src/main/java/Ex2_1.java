package main.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex2_1 {
    /**
     * Creates files
     *
     * @param n number of files
     * @param seed random number generation seed
     * @param  bound bound for random number
     * @return returns array of file names.
     * */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] createdFiles = new String[n];
        try {
            Random rand = new Random(seed);
            for (int i = 0; i < n; i++) {
                //Create file using streams
                String fileName = "files/file_" + i + ".txt";
                java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
                int numberOfLines = rand.nextInt(bound);
                // Write file
                for (int j = 0; j < numberOfLines; j++) {
                    fileWriter.append("Hello World");
                    // To remove space at the end of file.
                    if ( j != numberOfLines -1) {
                        fileWriter.append("\n");
                    }
                }
                //Close the file writer stream of current file.
                fileWriter.flush();
                fileWriter.close();
                createdFiles[i] = fileName;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return createdFiles;
    }

    /**
     * Gets number of line in files without using threads.
     *
     * @param fileNames array of files
     * @return number of lines
     * */
    public static int getNumOfLines(String[] fileNames) {
        int lines = 0;
        try {
            // Loop through the files array.
            for (String fileName : fileNames) {
                // Get path of current file
                Path path = Paths.get(fileName);
                //Calculate number of lines inside file.
                lines += (int) Files.lines(path).count();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Return number of lines inside files.
        return lines;
    }

    /**
     * Gets number of line in files using threads.
     *
     * @param fileNames array of files
     * @return number of lines
     * */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int lines = 0;
        // Loop through the files array.
        for (String fileName : fileNames) {
            //Create FileReaderThread class object and call start method,
            //to start the thread.
            FileReaderThread thread = new FileReaderThread(fileName);
            thread.start();
            // Get result of thread execution
            lines += thread.getNumOfLinesOfFile();
        }
        //Return number of lines in all files.
        return lines;
    }


    /**
     * Gets number of line in files using thread pool.
     *
     * @param fileNames array of files
     * @return number of lines
     * */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int lines = 0;
        try {
            //Create thread pool with the size equal to the size of number of files.
            ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
            // Loop through the files array.
            for (String fileName : fileNames) {
                //Create callable class object and submit to thread pool
                FileReaderCallable threadRunningInThreadPool = new FileReaderCallable(fileName);
                //Thread pool will assign a thread internally and execute the call method inside loop.
                Future<Integer> result = executor.submit(threadRunningInThreadPool);
                //Get the result from thread.
                lines += result.get();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
