package com.example.strategy.fill;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.entity.Animal.Animal;


public class FileArrayFillingStrategy implements ArrayFillingStrategy<Animal> {
    private final String fileName;

    public FileArrayFillingStrategy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Animal[] fillArray(int length) {
        Animal[] array = new Animal[length];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(fileName)))) {

            if (reader == null) {
                System.out.println("Файл не найден в resources: " + fileName);
                return array;
            }

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