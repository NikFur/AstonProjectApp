package com.example.getSource.file;

import com.example.getSource.GetData;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromFile implements GetData {
    private final String fileName;

    public GetDataFromFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> get() {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(fileName)))) {

            if (reader == null) {
                throw new IllegalArgumentException("Файл не найден: " + fileName);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return strings;
    }
}

