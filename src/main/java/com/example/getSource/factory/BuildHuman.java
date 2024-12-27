package com.example.getSource.factory;

import com.example.entity.Human.Human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuildHuman implements BuildObject<Human> {

    @Override
    public Human create(String value1, String value2, String value3) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (value1 == null || value1.trim().isEmpty() || (!value1.equalsIgnoreCase("Мужчина") && !value1.equalsIgnoreCase("Женщина"))) {
                System.out.print("Введите пол человека (Мужчина/Женщина): ");
                value1 = reader.readLine().trim();
                if (!value1.equalsIgnoreCase("Мужчина") && !value1.equalsIgnoreCase("Женщина")) {
                    System.out.println("Пол должен быть 'Мужчина' или 'Женщина'.");
                }
            }

            while (true) {
                try {
                    if (value2 == null || value2.trim().isEmpty()) {
                        System.out.print("Введите возраст человека (целое положительное число): ");
                        value2 = reader.readLine().trim();
                    }
                    int age = Integer.parseInt(value2);
                    if (age > 0 && age <= 120) {
                        break;
                    } else {
                        System.out.println("Возраст должен быть положительным числом.");
                        value2 = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Пожалуйста, введите корректное целое число.");
                    value2 = null;
                }
            }

            while (value3 == null || value3.trim().isEmpty()) {
                System.out.print("Введите фамилию человека: ");
                value3 = reader.readLine().trim();
            }
        } catch (IOException e) {
            throw new RuntimeException("Произошла ошибка ввода-вывода: " + e.getMessage(), e);
        }

        Human.Builder builder = new Human.Builder();
        return builder
                .setGender(value1)
                .setAge(Integer.parseInt(value2))
                .setLastName(value3)
                .build();
    }
}
