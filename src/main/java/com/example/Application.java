package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import com.example.context.ArrayFillingContext;
import com.example.entity.Animal.Animal;
import com.example.strategy.fill.ManualArrayFillingStrategy;
import com.example.strategy.fill.RandomArrayFillingStrategy;
import com.example.strategy.fill.FileArrayFillingStrategy;


public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Контексты для стратегии заполнения массива
        ArrayFillingContext<Animal> fillingContext = new ArrayFillingContext<>();

        // Выбор способа ввода данных в массив
        System.out.println("Выберите способ ввода данных в массив:\n1. Вручную\n2. Из файла\n3. Рандомно");
        int inputChoice = scanner.nextInt();
        int arrayLength = 0; // Длина массива будет определяться автоматически или вручную

        switch (inputChoice) {
            case 1 -> {
                // Ввод вручную
                System.out.print("Введите длину массива: ");
                arrayLength = scanner.nextInt();
                fillingContext.setStrategy(new ManualArrayFillingStrategy());
            }
            case 2 -> {
                // Чтение из файла
                System.out.print("Введите имя файла (из resources): ");
                scanner.nextLine(); // Считываем перевод строки
                String fileName = scanner.nextLine();
                arrayLength = calculateFileLengthFromResources(fileName);
                if (arrayLength > 0) {
                    fillingContext.setStrategy(new FileArrayFillingStrategy(fileName));
                } else {
                    System.out.println("Ошибка: Файл пуст или недоступен.");
                    return;
                }
            }
            case 3 -> {
                // Рандомный ввод
                arrayLength = generateRandomLength(5, 20); // Пример диапазона длины
                fillingContext.setStrategy(new RandomArrayFillingStrategy());
            }
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }

        // Заполнение массива
        Animal[] animals = fillingContext.executeFill(arrayLength);
        System.out.println("Массив до сортировки: " + Arrays.toString(animals));

        // Сортировка массива
        Arrays.sort(animals);
        System.out.println("Массив после сортировки: " + Arrays.toString(animals));
    }

    // Метод для определения длины массива из файла в resources
    private static int calculateFileLengthFromResources(String fileName) {
        int length = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Application.class.getClassLoader().getResourceAsStream(fileName)))) {

            if (reader == null) {
                System.out.println("Файл не найден в resources: " + fileName);
                return length;
            }

            while (reader.readLine() != null) {
                length++;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return length;
    }

    // Метод для генерации случайной длины массива
    private static int generateRandomLength(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
