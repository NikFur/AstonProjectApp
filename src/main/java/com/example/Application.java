package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import com.example.context.ArrayFillingContext;
import com.example.entity.Animal.Animal;
import com.example.strategy.fill.FileArrayFillingStrategy;
import com.example.strategy.fill.ManualArrayFillingStrategy;
import com.example.strategy.fill.RandomArrayFillingStrategy;

public class Application {
    private static final int MANUAL_INPUT = 1;
    private static final int FILE_INPUT = 2;
    private static final int RANDOM_INPUT = 3;
    private static final int SORT_AND_SEARCH = 4;
    private static final int EXIT = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Strategy contexts
        ArrayFillingContext<Animal> fillingContext = new ArrayFillingContext<>();
        boolean running = true;

        while (running) {
            System.out.println("Выберите способ ввода данных в массив: \n 1. Вручную \n 2. Из файла \n 3. Рандомно \n 4. Сортировка и Поиск \n 5. Выход  ");

            int inputChoice = validateIntegerInput(scanner, "Введите ваш выбор: ");

            switch (inputChoice) {
                case MANUAL_INPUT -> {
                    int arrayLength = validateIntegerInput(scanner, "Введите длину массива: ");
                    fillingContext.setStrategy(new ManualArrayFillingStrategy());
                    executeArrayFillingAndSorting(fillingContext, arrayLength);
                }
                case FILE_INPUT -> {
                    System.out.print("Введите имя файла (из resources): ");
                    scanner.nextLine(); // Consume newline
                    String fileName = scanner.nextLine();
                    int arrayLength = calculateFileLengthFromResources(fileName);
                    if (arrayLength > 0) {
                        fillingContext.setStrategy(new FileArrayFillingStrategy(fileName));
                        executeArrayFillingAndSorting(fillingContext, arrayLength);
                    } else {
                        System.out.println("Ошибка: Файл пуст или недоступен.");
                    }
                }
                case RANDOM_INPUT -> {
                    int arrayLength = validateIntegerInput(scanner, "Введите длину массива: ");
                    fillingContext.setStrategy(new RandomArrayFillingStrategy());
                    executeArrayFillingAndSorting(fillingContext, arrayLength);
                }
                case SORT_AND_SEARCH -> {
                    System.out.println("Сортировка и Поиск еще не реализованы.");
                }
                case EXIT -> {
                    running = false;
                    System.out.println("Выход из приложения.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static void executeArrayFillingAndSorting(ArrayFillingContext<Animal> fillingContext, int arrayLength) {
        try {
            // Fill the array using the selected strategy
            Animal[] animals = fillingContext.executeFill(arrayLength);
            System.out.println("Массив до сортировки: " + Arrays.toString(animals));

            // Sort the array if comparable or custom comparator exists
            Arrays.sort(animals);
            System.out.println("Массив после сортировки: " + Arrays.toString(animals));
        } catch (Exception e) {
            System.out.println("Ошибка при заполнении или сортировке массива: " + e.getMessage());
        }
    }

    // Validate integer input
    private static int validateIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Введите положительное целое число.");
                }
            } else {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Calculate file length from resources
    private static int calculateFileLengthFromResources(String fileName) {
        int length = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Application.class.getClassLoader().getResourceAsStream(fileName)))) {

            if (reader == null) {
                System.out.println("Файл не найден в resources: " + fileName);
                return 0;
            }

            while (reader.readLine() != null) {
                length++;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return length;
    }
}
