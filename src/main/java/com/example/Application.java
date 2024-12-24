package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

import com.example.binary.GenericBinarySearch;
import com.example.context.ArrayFillingContext;
import com.example.entity.Animal.Animal;
import com.example.entity.Human.Human;
import com.example.entity.Barrel.Barrel;
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
    private static final int EXIT = 4;

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
                    "\n 4. Назад");

            int inputChoice = validateIntegerInput(scanner, "Введите ваш выбор: ");

            switch (inputChoice) {
                case MANUAL_INPUT -> {
                    int arrayLength = validateIntegerInput(scanner, "Введите длину массива: ");
                    fillingContext.setStrategy(new ManualArrayFillingStrategy<>(builder));
                    executeArrayFillingAndSorting(fillingContext, arrayLength);
                }
                case FILE_INPUT -> {
                    System.out.print("Введите имя файла (из resources): ");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    int arrayLength = calculateFileLengthFromResources(fileName);
                    if (arrayLength > 0) {
                        fillingContext.setStrategy(new FileArrayFillingStrategy<>(fileName, builder));
                        executeArrayFillingAndSorting(fillingContext, arrayLength);
                    } else {
                        System.out.println("Ошибка: Файл пуст или недоступен.");
                    }
                }
                case RANDOM_INPUT -> {
                    int arrayLength = validateIntegerInput(scanner, "Введите длину массива: ");
                    fillingContext.setStrategy(new RandomArrayFillingStrategy<>(builder));
                    executeArrayFillingAndSorting(fillingContext, arrayLength);
                }
                case EXIT -> running = false;
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static <T> void executeArrayFillingAndSorting(ArrayFillingContext<T> fillingContext, int arrayLength) {
        try {
            T[] dataArray = fillingContext.executeFill(arrayLength);
            SortingService.sortAndPrint(dataArray);
        } catch (Exception e) {
            System.out.println("Ошибка при заполнении или сортировке массива: " + e.getMessage());
        }
    }
    private static <T> void searchMenu(ArrayFillingContext<T> fillingContext, int arrayLength, Scanner scanner) {
        try {
            T[] dataArray = fillingContext.executeFill(arrayLength);

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
