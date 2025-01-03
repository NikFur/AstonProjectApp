package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;
import com.example.binary.SearchHandlers;
import com.example.context.ArrayFillingContext;
import com.example.getSource.factory.BuildAnimal;
import com.example.getSource.factory.BuildBarrel;
import com.example.getSource.factory.BuildHuman;
import com.example.getSource.factory.BuildObject;
import com.example.sort.SortingService;
import com.example.strategy.fill.FileArrayFillingStrategy;
import com.example.strategy.fill.ManualArrayFillingStrategy;
import com.example.strategy.fill.RandomArrayFillingStrategy;


public class Application {
    private static final int MANUAL_INPUT = 1;
    private static final int FILE_INPUT = 2;
    private static final int RANDOM_INPUT = 3;
    private static final int SEARCH = 4;
    private static final int EXIT = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Выберите тип данных для заполнения массива:" +
                    "\n 1. Животное" +
                    "\n 2. Человек" +
                    "\n 3. Бочка" +
                    "\n 4. Выход");

            int objectType = validateIntegerInput(scanner, "Введите ваш выбор: ");
            switch (objectType) {
                case 1 -> handleObject(scanner, new BuildAnimal());
                case 2 -> handleObject(scanner, new BuildHuman());
                case 3 -> handleObject(scanner, new BuildBarrel());
                case 4 -> {
                    running = false;
                    System.out.println("Выход из приложения.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static <T> void handleObject(Scanner scanner, BuildObject<T> builder) {
        ArrayFillingContext<T> fillingContext = new ArrayFillingContext<>();
        boolean running = true;
        T[] dataArray = null;

        while (running) {
            System.out.println("Выберите способ ввода данных в массив: " +
                    "\n 1. Вручную" +
                    "\n 2. Из файла" +
                    "\n 3. Рандомно" +
                    "\n 4. Поиск по массиву" +
                    "\n 5. Назад");

            int inputChoice = validateIntegerInput(scanner, "Введите ваш выбор: ");

            switch (inputChoice) {
                case MANUAL_INPUT -> {
                    int arrayLength = validateIntegerInput(scanner, "Введите длину массива: ");
                    fillingContext.setStrategy(new ManualArrayFillingStrategy<>(builder));
                    dataArray = executeArrayFillingAndSorting(fillingContext, arrayLength);
                }
                case FILE_INPUT -> {
                    System.out.print("Введите имя файла (из resources): ");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    int arrayLength = calculateFileLengthFromResources(fileName);
                    if (arrayLength > 0) {
                        fillingContext.setStrategy(new FileArrayFillingStrategy<>(fileName, builder));
                        dataArray = executeArrayFillingAndSorting(fillingContext, arrayLength);
                    } else {
                        System.out.println("Ошибка: Файл пуст или недоступен.");
                    }
                }
                case RANDOM_INPUT -> {
                    int arrayLength = validateIntegerInput(scanner, "Введите длину массива: ");
                    fillingContext.setStrategy(new RandomArrayFillingStrategy<>(builder));
                    dataArray = executeArrayFillingAndSorting(fillingContext, arrayLength);
                }
                case SEARCH -> {
                    if (dataArray == null) {
                        System.out.println("Сначала заполните массив данных.");
                    } else {
                        SearchHandlers.searchMenu(dataArray, scanner);
                    }
                }
                case EXIT -> running = false;
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static <T> T[] executeArrayFillingAndSorting(ArrayFillingContext<T> fillingContext, int arrayLength) {
        try {
            T[] dataArray = fillingContext.executeFill(arrayLength);
            SortingService.sortAndPrint(dataArray);
            return dataArray;
        } catch (Exception e) {
            System.out.println("Ошибка при заполнении или сортировке массива: " + e.getMessage());
            return null;
        }
    }

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
                scanner.next();
            }
        }
    }

    private static int calculateFileLengthFromResources(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Application.class.getClassLoader().getResourceAsStream(fileName),
                        "Файл не найден в resources: " + fileName)))) {

            int length = 0;
            while (reader.readLine() != null) {
                length++;
            }
            return length;
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return 0;
    }
}

