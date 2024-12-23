package com.example.getSource.factory;

import com.example.entity.Animal.Animal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BuildAnimal implements BuildObject<Animal> {

    @Override
    public Animal create(String value1, String value2, String value3) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Validate and re-enter value1 (species)
            while (value1 == null || value1.trim().isEmpty()) {
                System.out.print("Вид животного не может быть пустым. Введите вид животного: ");
                value1 = reader.readLine().trim();
            }

            // Validate and re-enter value2 (eye color)
            while (value2 == null || value2.trim().isEmpty()) {
                System.out.print("Цвет глаз животного не может быть пустым. Введите цвет глаз: ");
                value2 = reader.readLine().trim();
            }

            // Validate and re-enter value3 (has fur)
            while (value3 == null || !(value3.equalsIgnoreCase("true") || value3.equalsIgnoreCase("false"))) {
                System.out.print("Поле 'Есть ли шерсть' должно быть 'true' или 'false'. Пожалуйста, введите: ");
                value3 = reader.readLine().trim();
            }
        } catch (IOException e) {
            throw new RuntimeException("Произошла ошибка ввода-вывода: " + e.getMessage(), e);
        }

        // Create and return the Animal object
        Animal.Builder builder = new Animal.Builder();
        return builder
                .setSpecies(value1)
                .setEyeColor(value2)
                .setHasFur(Boolean.parseBoolean(value3))
                .build();
    }

    @Override
    public List<String> getQuestions() {
        return List.of(
                "Введите вид животного: ",
                "Введите цвет глаз животного: ",
                "Есть ли шерсть (true/false): "
        );
    }
}
