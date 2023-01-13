package main.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

public class FileReaderCallable implements Callable<Integer> {

    private final String file;

    /**
     * Args constructor.
     *
     * @param file the file name
     * */
    public FileReaderCallable(String file) {

        this.file = file;
    }

    /**
     * Gets number of lines in file using threads.
     *
     * @return number of lines
     * */
    @Override
    public Integer call() throws Exception {
        return getNumberOfLines();
    }

    /**
     * Gets number of lines in file.
     *
     * @return number of lines
     * */
    private int getNumberOfLines() {
        int lines = 0;
        try {
            Path path = Paths.get(file);
            lines += (int) Files.lines(path).count();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
