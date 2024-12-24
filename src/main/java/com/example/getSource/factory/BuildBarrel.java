package com.example.getSource.factory;

import com.example.entity.Barrel.Barrel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BuildBarrel implements BuildObject<Barrel> {

    @Override
    public Barrel create(String value1, String value2, String value3) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Validate and re-enter value1 (volume)
            while (true) {
                try {
                    if (value1 == null || value1.trim().isEmpty()) {
                        System.out.print("Введите объем бочки (положительное число): ");
                        value1 = reader.readLine().trim();
                    }
                    if (Double.parseDouble(value1) > 0) {
                        break;
                    } else {
                        System.out.println("Объем должен быть положительным числом.");
                        value1 = null; // Reset for re-entry
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Пожалуйста, введите корректное число.");
                    value1 = null; // Reset for re-entry
                }
            }

            // Validate and re-enter value2 (stored material)
            while (value2 == null || value2.trim().isEmpty()) {
                System.out.print("Введите хранимый материал (не может быть пустым): ");
                value2 = reader.readLine().trim();
            }

            // Validate and re-enter value3 (material)
            while (value3 == null || value3.trim().isEmpty()) {
                System.out.print("Введите материал бочки: ");
                value3 = reader.readLine().trim();
            }
        } catch (IOException e) {
            throw new RuntimeException("Произошла ошибка ввода-вывода: " + e.getMessage(), e);
        }

        // Create and return the Barrel object
        Barrel.Builder builder = new Barrel.Builder();
        return builder
                .setVolume(Double.parseDouble(value1))
                .setStoredMaterial(value2)
                .setMaterial(value3)
                .build();
    }

    @Override
    public List<String> getQuestions() {
        return List.of(
                "Введите объем бочки: ",
                "Введите хранимый материал: ",
                "Введите материал бочки: "
        );
    }
}
