package com.example.getSource.file;

import com.example.getSource.GetData;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GetDataFromFile implements GetData {
    private final String fileName;

    public GetDataFromFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> get() {
        List<String> strings = null;
        try {
            strings = Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return strings;
    }
}
