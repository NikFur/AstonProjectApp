package com.example.strategy.fill;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.entity.Animal.Animal;
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
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < length) {
                String[] parts = line.split(",");
                String species = parts[0];
                String eyeColor = parts[1];
                boolean hasFur = Boolean.parseBoolean(parts[2]);

                array[index++] = new Animal.Builder()
                        .setSpecies(species)
                        .setEyeColor(eyeColor)
                        .setHasFur(hasFur)
                        .build();
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return array;
    }
}