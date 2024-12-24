package com.example.sort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileWriter {
    public static void fileToWrite (List<String> arrayTo) {

        List<String> arrayData = arrayTo;

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the output file (e.g., output.txt): ");
        String fileName = scanner.nextLine();

        Path filePath = Paths.get("src/main/resources/" + fileName + ".txt");

        // Write or append the data to the file
        appendDataToFile(filePath, arrayData);
    }

    /**
     * Appends data to the specified file if it exists and has content,
     * or creates a new file and writes the data if it doesn't exist.
     *
     * @param filePath The path to the file.
     * @param data     The list of strings to write or append to the file.
     */
    private static void appendDataToFile(Path filePath, List<String> data) {
        try {
            if (Files.exists(filePath)) {
                // File exists, append the data
                Files.write(filePath, data, StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.APPEND);
                System.out.println("Data has been appended to " + filePath.getFileName());
            } else {
                // File does not exist, create it and write the data
                Files.write(filePath, data, StandardCharsets.UTF_8);
                System.out.println("File created, and data has been written to " + filePath.getFileName());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
