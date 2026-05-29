package com.example.fighttracker.Logic;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * Utility class for counting the total lines of code in the project.
 * Scans the project directory for .java and .fxml files, specifically excluding
 * JSON data files and this counter class itself to provide an accurate metric of written code.
 */

public class LineCounter {

    /**
     * The main method that executes the line counting process.
     * Traverses the "src" directory, filters applicable files, and prints the total line count to the console.
     *
     * @param args Command line arguments (not used).
     */

    public static void main(String[] args) {
        Path projectDir = Paths.get("src");
        long totalLines = 0;

        try (Stream<Path> paths = Files.walk(projectDir)) {
            totalLines = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        String fileName = path.getFileName().toString();
                        return (fileName.endsWith(".java") || fileName.endsWith(".fxml"))
                                && !fileName.equals("LineCounter.java");
                    })
                    .mapToLong(path -> {
                        try (Stream<String> lines = Files.lines(path)) {
                            return lines.count();
                        } catch (IOException e) {
                            return 0;
                        }
                    })
                    .sum();

            System.out.println("🔥 Total lines: " + totalLines);

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}