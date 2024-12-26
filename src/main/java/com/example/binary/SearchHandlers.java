package com.example.binary;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.util.*;
import java.util.stream.Collectors;

import com.example.sort.FileWriter;

import static com.example.sort.FileWriter.fileToWrite;


public class SearchHandlers {

    public static <T> void searchMenu(T[] dataArray, Scanner scanner) {
        try {
            if (dataArray.length > 0) {
                if (dataArray[0] instanceof Animal) {
                    Animal[] animals = Arrays.copyOf(dataArray, dataArray.length, Animal[].class);
                    AnimalSearch(animals, scanner);
                } else if (dataArray[0] instanceof Barrel) {
                    Barrel[] barrels = Arrays.copyOf(dataArray, dataArray.length, Barrel[].class);
                    BarrelSearch(barrels, scanner);
                } else if (dataArray[0] instanceof Human) {
                    Human[] humans = Arrays.copyOf(dataArray, dataArray.length, Human[].class);
                    HumanSearch(humans, scanner);
                } else {
                    System.out.println("Данный тип объектов не поддерживается для поиска.");
                }
            } else {
                System.out.println("Массив данных пуст.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обработке поиска: " + e.getMessage());
        }
    }

    public static void AnimalSearch(Animal[] animals, Scanner scanner) {
        System.out.println("Выберите поле для поиска (Животные):" +
                "\n 1. Вид" +
                "\n 2. Цвет глаз" +
                "\n 3. Наличие шерсти" +
                "\n 4. Назад");

        int searchField = validateIntegerInput(scanner, "Введите ваш выбор: ");
        GenericBinarySearch<Animal> search = new GenericBinarySearch<>();

        switch (searchField) {
            case 1 -> {
                System.out.print("Введите вид для поиска: ");
                scanner.nextLine();
                String species = scanner.nextLine();

                List<Animal> matchingAnimals = Arrays.stream(animals)
                        .filter(animal -> species.equals(animal.getSpecies()))
                        .collect(Collectors.toList());

                if (matchingAnimals.isEmpty()) {
                    System.out.println("Животное с указанным видом не найдено.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingAnimals.size());
                    matchingAnimals.forEach(System.out::println);

                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingAnimals.stream()
                                .map(animal -> String.join(",", animal.getSpecies(), animal.getEyeColor(), String.valueOf(animal.hasFur())))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 2 -> {
                System.out.print("Введите цвет глаз для поиска: ");
                scanner.nextLine();
                String eyeColor = scanner.nextLine();

                List<Animal> matchingAnimals = Arrays.stream(animals)
                        .filter(animal -> eyeColor.equals(animal.getEyeColor()))
                        .collect(Collectors.toList());

                if (matchingAnimals.isEmpty()) {
                    System.out.println("Животное с указанным цветом глаз не найдено.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingAnimals.size());
                    matchingAnimals.forEach(System.out::println);

                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingAnimals.stream()
                                .map(animal -> String.join(",", animal.getSpecies(), animal.getEyeColor(), String.valueOf(animal.hasFur())))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 3 -> {
                System.out.print("Введите наличие шерсти (true/false) для поиска: ");
                scanner.nextLine();
                boolean hasFur = Boolean.parseBoolean(scanner.nextLine());

                List<Animal> matchingAnimals = Arrays.stream(animals)
                        .filter(animal -> animal.hasFur() == hasFur)
                        .collect(Collectors.toList());

                if (matchingAnimals.isEmpty()) {
                    System.out.println("Животное с указанным параметром наличия шерсти не найдено.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingAnimals.size());
                    matchingAnimals.forEach(System.out::println);

                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingAnimals.stream()
                                .map(animal -> String.join(",", animal.getSpecies(), animal.getEyeColor(), String.valueOf(animal.hasFur())))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 4 -> System.out.println("Возврат в предыдущее меню.");
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    public static void BarrelSearch(Barrel[] barrels, Scanner scanner) {
        System.out.println("Выберите поле для поиска (Бочки):" +
                "\n 1. Объём" +
                "\n 2. Хранимый материал" +
                "\n 3. Материал бочки" +
                "\n 4. Назад");

        int searchField = validateIntegerInput(scanner, "Введите ваш выбор: ");
        GenericBinarySearch<Barrel> search = new GenericBinarySearch<>();

        switch (searchField) {
            case 1 -> {
                System.out.print("Введите объём для поиска: ");
                scanner.nextLine();
                double volume = Double.parseDouble(scanner.nextLine());

                List<Barrel> matchingBarrels = Arrays.stream(barrels)
                        .filter(barrel -> barrel.getVolume() == volume)
                        .collect(Collectors.toList());

                if (matchingBarrels.isEmpty()) {
                    System.out.println("Бочка с таким объемом не найдена.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingBarrels.size());
                    matchingBarrels.forEach(System.out::println);
                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingBarrels.stream()
                                .map(barrel -> String.join(",", String.valueOf(barrel.getVolume()), barrel.getStoredMaterial(), barrel.getMaterial()))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 2 -> {
                System.out.print("Введите хранимый материал для поиска: ");
                scanner.nextLine();
                String storedMaterial = scanner.nextLine();

                List<Barrel> matchingBarrels = Arrays.stream(barrels)
                        .filter(barrel -> storedMaterial.equals(barrel.getStoredMaterial()))
                        .collect(Collectors.toList());

                if (matchingBarrels.isEmpty()) {
                    System.out.println("Бочка с указанным хранимым материалом не найдена.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingBarrels.size());
                    matchingBarrels.forEach(System.out::println);
                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingBarrels.stream()
                                .map(barrel -> String.join(",", String.valueOf(barrel.getVolume()), barrel.getStoredMaterial(), barrel.getMaterial()))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 3 -> {
                System.out.print("Введите материал бочки для поиска: ");
                scanner.nextLine();
                String material = scanner.nextLine();

                List<Barrel> matchingBarrels = Arrays.stream(barrels)
                        .filter(barrel -> material.equals(barrel.getMaterial()))
                        .collect(Collectors.toList());

                if (matchingBarrels.isEmpty()) {
                    System.out.println("Бочка с указанным материалом не найдена.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingBarrels.size());
                    matchingBarrels.forEach(System.out::println);
                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingBarrels.stream()
                                .map(barrel -> String.join(",", String.valueOf(barrel.getVolume()), barrel.getStoredMaterial(), barrel.getMaterial()))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 4 -> System.out.println("Возврат в предыдущее меню.");
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    public static void HumanSearch(Human[] humans, Scanner scanner) {
        System.out.println("Выберите поле для поиска (Люди):" +
                "\n 1. Пол" +
                "\n 2. Возраст" +
                "\n 3. Фамилия" +
                "\n 4. Назад");

        int searchField = validateIntegerInput(scanner, "Введите ваш выбор: ");
        GenericBinarySearch<Human> search = new GenericBinarySearch<>();

        switch (searchField) {
            case 1 -> {
                System.out.print("Введите пол для поиска: ");
                scanner.nextLine();
                String gender = scanner.nextLine();

                List<Human> matchingHumans = Arrays.stream(humans)
                        .filter(human -> gender.equals(human.getGender()))
                        .collect(Collectors.toList());

                if (matchingHumans.isEmpty()) {
                    System.out.println("Человек с указанным полом не найден.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingHumans.size());
                    matchingHumans.forEach(System.out::println);
                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingHumans.stream()
                                .map(human -> String.join(",", human.getGender(), String.valueOf(human.getAge()), human.getLastName()))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 2 -> {
                System.out.print("Введите возраст для поиска: ");
                scanner.nextLine();
                int age = Integer.parseInt(scanner.nextLine());

                List<Human> matchingHumans = Arrays.stream(humans)
                        .filter(human -> human.getAge() == age)
                        .collect(Collectors.toList());

                if (matchingHumans.isEmpty()) {
                    System.out.println("Человек с указанным возрастом не найден.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingHumans.size());
                    matchingHumans.forEach(System.out::println);
                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingHumans.stream()
                                .map(human -> String.join(",", human.getGender(), String.valueOf(human.getAge()), human.getLastName()))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 3 -> {
                System.out.print("Введите фамилию для поиска: ");
                scanner.nextLine();
                String lastName = scanner.nextLine();

                List<Human> matchingHumans = Arrays.stream(humans)
                        .filter(human -> lastName.equals(human.getLastName()))
                        .collect(Collectors.toList());

                if (matchingHumans.isEmpty()) {
                    System.out.println("Человек с указанной фамилией не найден.");
                } else {
                    System.out.println("Найдено совпадений: " + matchingHumans.size());
                    matchingHumans.forEach(System.out::println);
                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("yes")) {
                        List<String> resultStrings = matchingHumans.stream()
                                .map(human -> String.join(",", human.getGender(), String.valueOf(human.getAge()), human.getLastName()))
                                .collect(Collectors.toList());
                        fileToWrite(resultStrings);
                    }
                }
            }
            case 4 -> System.out.println("Возврат в предыдущее меню.");
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
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
}
