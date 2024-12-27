package com.example.binary;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.sort.WriteToFile.fileToWrite;


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

        int searchField = validateIntegerInput(scanner);

        switch (searchField) {
            case 1 -> {
                System.out.print("Введите вид для поиска: ");
                String species = scanner.nextLine().trim();

                Arrays.sort(animals, Comparator.comparing(Animal::getSpecies, String.CASE_INSENSITIVE_ORDER));

                Animal searchAnimal = new Animal.Builder()
                        .setSpecies(species)
                        .build();
                int index = Arrays.binarySearch(animals, searchAnimal, Comparator.comparing(Animal::getSpecies, String.CASE_INSENSITIVE_ORDER));
                if (index < 0) {
                    System.out.println("Животное с указанным видом не найдено.");
                } else {
                    int startIndex = index;
                    int endIndex = index;
                    while (startIndex > 0 && animals[startIndex - 1].getSpecies().equalsIgnoreCase(species)) {
                        startIndex--;
                    }
                    while (endIndex < animals.length - 1 && animals[endIndex + 1].getSpecies().equalsIgnoreCase(species)) {
                        endIndex++;
                    }
                    List<Animal> matchingAnimals = new ArrayList<>(Arrays.asList(animals).subList(startIndex, endIndex + 1));

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
                String eyeColor = scanner.nextLine().trim();
                Arrays.sort(animals, Comparator.comparing(Animal::getEyeColor, String.CASE_INSENSITIVE_ORDER));
                Animal searchAnimal = new Animal.Builder()
                        .setEyeColor(eyeColor)
                        .build();

                int index = Arrays.binarySearch(animals, searchAnimal, Comparator.comparing(Animal::getEyeColor, String.CASE_INSENSITIVE_ORDER));
                if (index < 0) {
                    System.out.println("Животное с указанным цветом глаз не найдено.");
                } else {
                    int startIndex = index;
                    int endIndex = index;

                    while (startIndex > 0 && animals[startIndex - 1].getEyeColor().equalsIgnoreCase(eyeColor)) {
                        startIndex--;
                    }
                    while (endIndex < animals.length - 1 && animals[endIndex + 1].getEyeColor().equalsIgnoreCase(eyeColor)) {
                        endIndex++;
                    }

                    List<Animal> matchingAnimals = new ArrayList<>(Arrays.asList(animals).subList(startIndex, endIndex + 1));
                    System.out.println("Найдено совпадений: " + matchingAnimals.size());
                    matchingAnimals.forEach(System.out::println);

                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine().trim();
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
                boolean hasFur = Boolean.parseBoolean(scanner.nextLine().trim());
                Arrays.sort(animals, Comparator.comparing(Animal::hasFur));
                Animal searchAnimal = new Animal.Builder()
                        .setHasFur(hasFur)
                        .build();
                int index = Arrays.binarySearch(animals, searchAnimal, Comparator.comparing(Animal::hasFur));

                if (index < 0) {
                    System.out.println("Животное с указанным параметром наличия шерсти не найдено.");
                } else {
                    int startIndex = index;
                    int endIndex = index;

                    while (startIndex > 0 && animals[startIndex - 1].hasFur() == hasFur) {
                        startIndex--;
                    }
                    while (endIndex < animals.length - 1 && animals[endIndex + 1].hasFur() == hasFur) {
                        endIndex++;
                    }

                    List<Animal> matchingAnimals = new ArrayList<>(Arrays.asList(animals).subList(startIndex, endIndex + 1));
                    System.out.println("Найдено совпадений: " + matchingAnimals.size());
                    matchingAnimals.forEach(System.out::println);

                    System.out.print("Хотите сохранить результаты поиска в файл? (yes/no): ");
                    String saveChoice = scanner.nextLine().trim();
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

        int searchField = validateIntegerInput(scanner);scanner.nextLine();

        switch (searchField) {
            case 1 -> {
                System.out.print("Введите объём для поиска: ");
                double volume = Double.parseDouble(scanner.nextLine());
                Arrays.sort(barrels, Comparator.comparingDouble(Barrel::getVolume));
                Barrel searchBarrel = new Barrel.Builder()
                        .setVolume(volume)
                        .build();

                int index = Arrays.binarySearch(barrels, searchBarrel, Comparator.comparingDouble(Barrel::getVolume));

                if (index < 0) {
                    System.out.println("Бочка с таким объемом не найдена.");
                } else {

                    int startIndex = index;
                    while (startIndex > 0 && barrels[startIndex - 1].getVolume() == volume) {
                        startIndex--;
                    }

                    int endIndex = index;
                    while (endIndex < barrels.length - 1 && barrels[endIndex + 1].getVolume() == volume) {
                        endIndex++;
                    }

                    List<Barrel> matchingBarrels = new ArrayList<>(Arrays.asList(barrels).subList(startIndex, endIndex + 1));
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
                String storedMaterial = scanner.nextLine();
                Arrays.sort(barrels, Comparator.comparing(Barrel::getStoredMaterial));
                Barrel searchBarrelStoredMaterial = new Barrel.Builder()
                        .setStoredMaterial(storedMaterial)
                        .build();

                int index = Arrays.binarySearch(barrels, searchBarrelStoredMaterial, Comparator.comparing(Barrel::getStoredMaterial));

                if (index < 0) {
                    System.out.println("Бочка с таким хранимым материалом не найдена.");
                } else {

                    int startIndex = index;
                    while (startIndex > 0 && Objects.equals(barrels[startIndex - 1].getStoredMaterial(), storedMaterial)) {
                        startIndex--;
                    }

                    int endIndex = index;
                    while (endIndex < barrels.length - 1 && Objects.equals(barrels[endIndex + 1].getStoredMaterial(), storedMaterial)) {
                        endIndex++;
                    }

                    List<Barrel> matchingBarrels = new ArrayList<>(Arrays.asList(barrels).subList(startIndex, endIndex + 1));
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
                String material = scanner.nextLine();
                Arrays.sort(barrels, Comparator.comparing(Barrel::getMaterial, String.CASE_INSENSITIVE_ORDER));
                Barrel searchBarrelMaterial = new Barrel.Builder()
                        .setMaterial(material)
                        .build();

                int index = Arrays.binarySearch(barrels, searchBarrelMaterial, Comparator.comparing(Barrel::getMaterial, String.CASE_INSENSITIVE_ORDER));

                if (index < 0) {
                    System.out.println("Бочка с указанным материалом не найдена.");
                } else {
                    int startIndex = index;
                    while (startIndex > 0 && barrels[startIndex - 1].getMaterial().equalsIgnoreCase(material)) {
                        startIndex--;
                    }
                    int endIndex = index;
                    while (endIndex < barrels.length - 1 && barrels[endIndex + 1].getMaterial().equalsIgnoreCase(material)) {
                        endIndex++;
                    }

                    List<Barrel> matchingBarrels = new ArrayList<>(Arrays.asList(barrels).subList(startIndex, endIndex + 1));
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

        int searchField = validateIntegerInput(scanner);scanner.nextLine();

        switch (searchField) {
            case 1 -> {
                System.out.print("Введите пол для поиска: ");
                String gender = scanner.nextLine();
                Arrays.sort(humans, Comparator.comparing(Human::getGender, String.CASE_INSENSITIVE_ORDER));

                Human searchHumanGender = new Human.Builder()
                        .setGender(gender)
                        .build();
                int index = Arrays.binarySearch(humans, searchHumanGender, Comparator.comparing(Human::getGender, String.CASE_INSENSITIVE_ORDER));

                if (index < 0) {
                    System.out.println("Человек с указанным полом не найден.");
                } else {

                    int startIndex = index;
                    while (startIndex > 0 && humans[startIndex - 1].getGender().equalsIgnoreCase(gender)) {
                        startIndex--;
                    }

                    int endIndex = index;
                    while (endIndex < humans.length - 1 && humans[endIndex + 1].getGender().equalsIgnoreCase(gender)) {
                        endIndex++;
                    }

                    List<Human> matchingHumans = new ArrayList<>(Arrays.asList(humans).subList(startIndex, endIndex + 1));
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
                int age = Integer.parseInt(scanner.nextLine());
                Arrays.sort(humans, Comparator.comparingInt(Human::getAge));
                Human searchHumanAge = new Human.Builder()
                        .setAge(age)
                        .build();

                int index = Arrays.binarySearch(humans, searchHumanAge, Comparator.comparingInt(Human::getAge));

                if (index < 0) {
                    System.out.println("Человек с указанным возрастом не найден.");
                } else {
                    int startIndex = index;
                    while (startIndex > 0 && humans[startIndex - 1].getAge() == age) {
                        startIndex--;
                    }

                    int endIndex = index;
                    while (endIndex < humans.length - 1 && humans[endIndex + 1].getAge() == age) {
                        endIndex++;
                    }

                    List<Human> matchingHumans = new ArrayList<>(Arrays.asList(humans).subList(startIndex, endIndex + 1));
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
                String lastName = scanner.nextLine();
                Arrays.sort(humans, Comparator.comparing(Human::getLastName, String.CASE_INSENSITIVE_ORDER));
                Human searchHumanLastName = new Human.Builder()
                        .setLastName(lastName)
                        .build();

                int index = Arrays.binarySearch(humans, searchHumanLastName, Comparator.comparing(Human::getLastName, String.CASE_INSENSITIVE_ORDER));

                if (index < 0) {
                    System.out.println("Человек с указанной фамилией не найден.");
                } else {
                    int startIndex = index;
                    while (startIndex > 0 && humans[startIndex - 1].getLastName().equalsIgnoreCase(lastName)) {
                        startIndex--;
                    }

                    int endIndex = index;
                    while (endIndex < humans.length - 1 && humans[endIndex + 1].getLastName().equalsIgnoreCase(lastName)) {
                        endIndex++;
                    }

                    List<Human> matchingHumans = new ArrayList<>(Arrays.asList(humans).subList(startIndex, endIndex + 1));
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

    private static int validateIntegerInput(Scanner scanner) {
        while (true) {
            System.out.print("Введите ваш выбор: ");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0 && input < 5) {
                    return input;
                } else {
                    System.out.println("Введите положительное целое число от 1 до 4.");
                }
            } else {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                scanner.next();
            }
        }
    }
}
