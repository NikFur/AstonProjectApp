package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import com.example.binary.AnimalBinarySearch;
import com.example.context.ArrayFillingContext;
import com.example.entity.Animal.Animal;
import com.example.entity.Human.Human;
import com.example.entity.Barrel.Barrel;
import com.example.factory.BuildAnimal;
import com.example.factory.BuildBarrel;
import com.example.factory.BuildHuman;
import com.example.factory.BuildObject;
import com.example.strategy.fill.FileArrayFillingStrategy;
import com.example.strategy.fill.ManualArrayFillingStrategy;
import com.example.strategy.fill.RandomArrayFillingStrategy;

public class Application {
    private static final int MANUAL_INPUT = 1;
    private static final int FILE_INPUT = 2;
    private static final int RANDOM_INPUT = 3;
    private static final int SEARCH_MENU = 4;
    private static final int EXIT = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Выберите тип данных для заполнения массива:" +
                    "\n 1. Animal" +
                    "\n 2. Human" +
                    "\n 3. Barrel" +
                    "\n 4. Выход");

            int objectType = validateIntegerInput(scanner, "Введите ваш выбор: ");
            switch (objectType) {
                case 1 -> handleObject(scanner, new BuildAnimal(), Animal.class);
                case 2 -> handleObject(scanner, new BuildHuman(), Human.class);
                case 3 -> handleObject(scanner, new BuildBarrel(), Barrel.class);
                case 4 -> {
                    running = false;
                    System.out.println("Выход из приложения.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static <T> void handleObject(Scanner scanner, BuildObject<T> builder, Class<T> typeClass) {
        ArrayFillingContext<T> fillingContext = new ArrayFillingContext<>();
        boolean running = true;

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
                    searchMenu(fillingContext, arrayLength, scanner);
                }
                case FILE_INPUT -> {
                    System.out.print("Введите имя файла (из resources): ");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    int arrayLength = calculateFileLengthFromResources(fileName);
                    if (arrayLength > 0) {
                        fillingContext.setStrategy(new FileArrayFillingStrategy<>(fileName, builder));
                        executeArrayFillingAndSorting(fillingContext, arrayLength, scanner);
                    } else {
                        System.out.println("Ошибка: Файл пуст или недоступен.");
                    }
                }
                case RANDOM_INPUT -> {
                    int arrayLength = validateIntegerInput(scanner, "Введите длину массива: ");
                    fillingContext.setStrategy(new RandomArrayFillingStrategy<>(builder));
                    executeArrayFillingAndSorting(fillingContext, arrayLength, scanner);
                }
                //case SEARCH_MENU -> searchMenu(scanner, fillingContext, arrayLength);
                case EXIT -> running = false;
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static <T> void executeArrayFillingAndSorting(ArrayFillingContext<T> fillingContext, int arrayLength, Scanner scanner) {
      /* try {
            T[] dataArray = fillingContext.executeFill(arrayLength);
            System.out.println("Массив до сортировки: " + Arrays.toString(dataArray));
            Arrays.sort(dataArray);
            System.out.println("Массив после сортировки: " + Arrays.toString(dataArray));
        } catch (Exception e) {
            System.out.println("Ошибка при заполнении или сортировке массива: " + e.getMessage());
        }

       */
    }

    private static <T> void searchMenu(ArrayFillingContext<T> fillingContext, int arrayLength, Scanner scanner) {
        try {

            T[] dataArray = fillingContext.executeFill(arrayLength);

            if (dataArray.length > 0 && dataArray[0] instanceof Animal) {
                Animal[] animals = Arrays.copyOf(dataArray, dataArray.length, Animal[].class);

                System.out.println("Выберите поле для поиска:" +
                        "\n 1. Вид (species)" +
                        "\n 2. Цвет глаз (eyeColor)" +
                        "\n 3. Назад");

                int searchField = validateIntegerInput(scanner, "Введите ваш выбор: ");

                switch (searchField) {
                    case 1 -> {
                        Arrays.sort(animals, Comparator.comparing(Animal::getSpecies));
                        System.out.println("Массив отсортирован по виду: " + Arrays.toString(animals));

                        System.out.print("Введите вид для поиска: ");
                        scanner.nextLine();
                        String speciesQuery = scanner.nextLine();
                        Animal searchResult = AnimalBinarySearch.searchBySpecies(animals, speciesQuery);

                        if (searchResult != null) {
                            System.out.println("Найдено: " + searchResult);
                        } else {
                            System.out.println("Объект с указанным видом не найден.");
                        }
                    }
                    case 2 -> {
                        Arrays.sort(animals, Comparator.comparing(Animal::getEyeColor));
                        System.out.println("Массив отсортирован по цвету глаз: " + Arrays.toString(animals));

                        System.out.print("Введите цвет глаз для поиска: ");
                        scanner.nextLine();
                        String eyeColorQuery = scanner.nextLine();
                        Animal searchResult = AnimalBinarySearch.searchByEyeColor(animals, eyeColorQuery);

                        if (searchResult != null) {
                            System.out.println("Найдено: " + searchResult);
                        } else {
                            System.out.println("Объект с указанным цветом глаз не найден.");
                        }
                    }
                    case 3 -> System.out.println("Возврат в предыдущее меню.");
                    default -> System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } else {
                System.out.println("Массив не содержит объектов типа Animal.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обработке поиска: " + e.getMessage());
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