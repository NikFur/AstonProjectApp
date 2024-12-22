package com.example.strategy.length;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileBasedLengthStrategy implements ArrayLengthStrategy {
    private final String fileName;

    public FileBasedLengthStrategy(String filePath) {
        this.fileName = filePath;
    }

    @Override
    public int getLength() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return count;
    }
}
