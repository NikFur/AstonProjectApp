package com.example.strategy.fill;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import com.example.factory.BuildObject;


public class FileArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final String fileName;
    private final BuildObject<T> object;

    public FileArrayFillingStrategy(String fileName, BuildObject<T> object) {
        this.fileName = fileName;
        this.object = object;
    }

    @Override
    public T[] fillArray(int length) {
        T[] array = (T[]) new Object[length];
        List<String> strings = null;
        try {
            strings = Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        if (!strings.isEmpty()) {
            for (int i = 0; i <array.length; i++) {
                String[] parts = strings.get(i).split(",");
                String value1 = parts[0];
                String value2 = parts[1];
                String value3 = parts[2];
                array[i] = object.create(value1, value2, value3);
            }
        }
        return array;
    }
}