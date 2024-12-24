package com.example.binary;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class SearchService {

    public static void searchAndPrint(Scanner scanner, Object[] dataArray) {

        if (dataArray == null || dataArray.length == 0) {
            System.out.println("Массив пуст.");
            return;
        }

        Object firstElement = dataArray[0];

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
    }

    private static void AnimalSearch(Animal[] animals, Scanner scanner) {
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
                Animal target = new Animal.Builder().setSpecies(species).build();
                Comparator<Animal> speciesComparator = Comparator.comparing(Animal::getSpecies);
                Optional<Animal> result = search.search(animals, target, speciesComparator);
                result.ifPresentOrElse(
                        animal -> System.out.println("Найдено: " + animal),
                        () -> System.out.println("Животное с указанным видом не найдено.")
                );
            }
            case 2 -> {
                System.out.print("Введите цвет глаз для поиска: ");
                scanner.nextLine();
                String eyeColor = scanner.nextLine();
                Animal target = new Animal.Builder().setEyeColor(eyeColor).build();
                Comparator<Animal> eyeColorComparator = Comparator.comparing(Animal::getEyeColor);
                Optional<Animal> result = search.search(animals, target, eyeColorComparator);
                result.ifPresentOrElse(
                        animal -> System.out.println("Найдено: " + animal),
                        () -> System.out.println("Животное с указанным цветом глаз не найдено.")
                );
            }
            case 3 -> {
                System.out.print("Введите наличие шерсти (true/false) для поиска: ");
                scanner.nextLine();
                boolean hasFur = Boolean.parseBoolean(scanner.nextLine());
                Animal target = new Animal.Builder().setHasFur(hasFur).build();
                Comparator<Animal> hasFurComparator = Comparator.comparing(Animal::hasFur);
                Optional<Animal> result = search.search(animals, target, hasFurComparator);
                result.ifPresentOrElse(
                        animal -> System.out.println("Найдено: " + animal),
                        () -> System.out.println("Животное с указанным параметром наличия шерсти не найдено.")
                );
            }
            case 4 -> System.out.println("Возврат в предыдущее меню.");
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private static void BarrelSearch(Barrel[] barrels, Scanner scanner) {
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
                Barrel target = new Barrel.Builder().setVolume(volume).build();
                Comparator<Barrel> volumeComparator = Comparator.comparingDouble(Barrel::getVolume);
                Optional<Barrel> result = search.search(barrels, target, volumeComparator);
                result.ifPresentOrElse(
                        barrel -> System.out.println("Найдено: " + barrel),
                        () -> System.out.println("Бочка с таким объемом не найдена.")
                );
            }
            case 2 -> {
                System.out.print("Введите хранимый материал для поиска: ");
                scanner.nextLine();
                String storedMaterial = scanner.nextLine();
                Barrel target = new Barrel.Builder().setStoredMaterial(storedMaterial).build();
                Comparator<Barrel> storedMaterialComparator = Comparator.comparing(Barrel::getStoredMaterial);
                Optional<Barrel> result = search.search(barrels, target, storedMaterialComparator);
                result.ifPresentOrElse(
                        barrel -> System.out.println("Найдено: " + barrel),
                        () -> System.out.println("Бочка с указанным хранимым материалом не найдена.")
                );
            }
            case 3 -> {
                System.out.print("Введите материал бочки для поиска: ");
                scanner.nextLine();
                String material = scanner.nextLine();
                Barrel target = new Barrel.Builder().setMaterial(material).build();
                Comparator<Barrel> materialComparator = Comparator.comparing(Barrel::getMaterial);
                Optional<Barrel> result = search.search(barrels, target, materialComparator);
                result.ifPresentOrElse(
                        barrel -> System.out.println("Найдено: " + barrel),
                        () -> System.out.println("Бочка с указанным материалом не найдена.")
                );
            }
            case 4 -> System.out.println("Возврат в предыдущее меню.");
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private static void HumanSearch(Human[] humans, Scanner scanner) {
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
                Human target = new Human.Builder().setGender(gender).build();
                Comparator<Human> genderComparator = Comparator.comparing(Human::getGender);
                Optional<Human> result = search.search(humans, target, genderComparator);
                result.ifPresentOrElse(
                        human -> System.out.println("Найдено: " + human),
                        () -> System.out.println("Человек с указанным полом не найден.")
                );
            }
            case 2 -> {
                System.out.print("Введите возраст для поиска: ");
                scanner.nextLine();
                int age = Integer.parseInt(scanner.nextLine());
                Human target = new Human.Builder().setAge(age).build();
                Comparator<Human> ageComparator = Comparator.comparingInt(Human::getAge);
                Optional<Human> result = search.search(humans, target, ageComparator);
                result.ifPresentOrElse(
                        human -> System.out.println("Найдено: " + human),
                        () -> System.out.println("Человек с указанным возрастом не найден.")
                );
            }
            case 3 -> {
                System.out.print("Введите фамилию для поиска: ");
                scanner.nextLine();
                String lastName = scanner.nextLine();
                Human target = new Human.Builder().setLastName(lastName).build();
                Comparator<Human> lastNameComparator = Comparator.comparing(Human::getLastName);
                Optional<Human> result = search.search(humans, target, lastNameComparator);
                result.ifPresentOrElse(
                        human -> System.out.println("Найдено: " + human),
                        () -> System.out.println("Человек с указанной фамилией не найден.")
                );
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
