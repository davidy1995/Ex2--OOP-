package main.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderThread extends Thread {
    private final String file;
    private int numberOfLines;

    /**
     * Args constructor.
     *
     * @param file the file name
     * */
    public FileReaderThread(String file) {

        this.file = file;
    }

    /**
     * Returns the number of lines.
     *
     * @return number of lines
     * */
    public int getNumOfLinesOfFile() {
        return this.numberOfLines;
    }

    /**
     * The run method, calculates number of lines in the file, called by thread.
     * */
    @Override
    public void run() {
        int lines = 0;
        try {
            Path path = Paths.get(file);
            lines += (int) Files.lines(path).count();
            System.out.println("Total lines = " + lines);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.numberOfLines = lines;
    }
}
