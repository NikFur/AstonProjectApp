package com.example.getSource.factory;

import com.example.entity.Barrel.Barrel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuildBarrel implements BuildObject<Barrel> {

    @Override
    public Barrel create(String value1, String value2, String value3) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
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
                        value1 = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Пожалуйста, введите корректное число.");
                    value1 = null;
                }
            }

            while (value2 == null || value2.trim().isEmpty()) {
                System.out.print("Введите хранимый материал (не может быть пустым): ");
                value2 = reader.readLine().trim();
            }

            while (value3 == null || value3.trim().isEmpty()) {
                System.out.print("Введите материал бочки: ");
                value3 = reader.readLine().trim();
            }
        } catch (IOException e) {
            throw new RuntimeException("Произошла ошибка ввода-вывода: " + e.getMessage(), e);
        }

        Barrel.Builder builder = new Barrel.Builder();
        return builder
                .setVolume(Double.parseDouble(value1))
                .setStoredMaterial(value2)
                .setMaterial(value3)
                .build();
    }
}
