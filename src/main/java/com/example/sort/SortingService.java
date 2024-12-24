package com.example.sort;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class SortingService {

    public static void sortAndPrint(Object[] dataArray) {
        if (dataArray == null || dataArray.length == 0) {
            System.out.println("Массив пуст.");
            return;
        }

        Object firstElement = dataArray[0];

        if (firstElement instanceof Animal) {
            sortAnimals( Arrays.copyOf(dataArray, dataArray.length, Animal[].class));
        } else if (firstElement instanceof Human) {
            sortHumans(Arrays.copyOf(dataArray, dataArray.length, Human[].class));
        } else if (firstElement instanceof Barrel) {
            sortBarrels(Arrays.copyOf(dataArray, dataArray.length, Barrel[].class));
        } else {
            System.out.println("Тип массива не поддерживается для сортировки.");
        }
    }

    private static void sortAnimals(Animal[] animals) {
        System.out.println("Массив до сортировки: " + Arrays.toString(animals));
        TimSort.timSort(animals, Comparator.comparing(Animal::getSpecies));
        System.out.println("Массив после сортировки по виду: " + Arrays.toString(animals));
        TimSort.timSort(animals, Comparator.comparing(Animal::getEyeColor));
        System.out.println("Массив после сортировки по цвету глаз: " + Arrays.toString(animals));
        TimSort.timSort(animals, Comparator.comparing(Animal::hasFur));
        System.out.println("Массив после сортировки по наличию шерсти: " + Arrays.toString(animals));
        // Convert Animal[] to List<String>
        List<String> animalList = Arrays.stream(animals)
                .map(Animal::toString) // Convert each Animal to its string representation
                .collect(Collectors.toList()); // Use Collectors.toList() instead of toList()

        // Write the list to a file
        fileToWrite(animalList);

    }

    private static void sortHumans(Human[] humans) {
        System.out.println("Массив до сортировки: " + Arrays.toString(humans));
        TimSort.timSort(humans, Comparator.comparing(Human::getGender));
        System.out.println("Массив после сортировки по полу: " + Arrays.toString(humans));
        TimSort.timSort(humans, Comparator.comparing(Human::getAge));
        System.out.println("Массив после сортировки по возрасту: " + Arrays.toString(humans));
        TimSort.timSort(humans, Comparator.comparing(Human::getLastName));
        System.out.println("Массив после сортировки по фамилии: " + Arrays.toString(humans));
    }

    private static void sortBarrels(Barrel[] barrels) {
        System.out.println("Массив до сортировки: " + Arrays.toString(barrels));
        TimSort.timSort(barrels, Comparator.comparing(Barrel::getVolume));
        System.out.println("Массив после сортировки по объему: " + Arrays.toString(barrels));
        TimSort.timSort(barrels, Comparator.comparing(Barrel::getStoredMaterial));
        System.out.println("Массив после сортировки по хранимому материалу: " + Arrays.toString(barrels));
        TimSort.timSort(barrels, Comparator.comparing(Barrel::getMaterial));
        System.out.println("Массив после сортировки по материалу бочки: " + Arrays.toString(barrels));
    }
    private static void fileToWrite (List<String> arrayTo) {

        List<String> arrayData = arrayTo;

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the output file (e.g., output.txt): ");
        String fileName = scanner.nextLine();

        Path filePath = Paths.get(fileName);

        // Write or append the data to the file
        appendDataToFile(filePath, arrayData);
    }

    /**
     * Appends data to the specified file if it exists and has content,
     * or creates a new file and writes the data if it doesn't exist.
     *
     * @param filePath The path to the file.
     * @param data     The list of strings to write or append to the file.
     */
    private static void appendDataToFile(Path filePath, List<String> data) {
        try {
            if (Files.exists(filePath)) {
                // File exists, append the data
                Files.write(filePath, data, StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.APPEND);
                System.out.println("Data has been appended to " + filePath.getFileName());
            } else {
                // File does not exist, create it and write the data
                Files.write(filePath, data, StandardCharsets.UTF_8);
                System.out.println("File created, and data has been written to " + filePath.getFileName());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

