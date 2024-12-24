package com.example.sort;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;
import com.example.sort.FileWriter;


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

import static com.example.sort.FileWriter.fileToWrite;


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
        // Convert Animal[] to List<String> in the desired format
        List<String> animalList = Arrays.stream(animals)
                .map(animal -> String.format("%s,%s,%b",
                        animal.getSpecies(),
                        animal.getEyeColor(),
                        animal.hasFur()))
                .collect(Collectors.toList());

        // Ask the user whether to save the file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to save the sorted data to a file? (yes/no): ");
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if (userResponse.equals("yes") || userResponse.equals("да")) {
            // Write the list to a file
            fileToWrite(animalList);
        } else {
            System.out.println("Data was not saved to a file.");
        }

    }

    private static void sortHumans(Human[] humans) {
        System.out.println("Массив до сортировки: " + Arrays.toString(humans));
        TimSort.timSort(humans, Comparator.comparing(Human::getGender));
        System.out.println("Массив после сортировки по полу: " + Arrays.toString(humans));
        TimSort.timSort(humans, Comparator.comparing(Human::getAge));
        System.out.println("Массив после сортировки по возрасту: " + Arrays.toString(humans));
        TimSort.timSort(humans, Comparator.comparing(Human::getLastName));
        System.out.println("Массив после сортировки по фамилии: " + Arrays.toString(humans));
        // Convert Human[] to List<String> in the desired format
        List<String> humanList = Arrays.stream(humans)
                .map(human -> String.format("%s,%s,%b",
                        human.getGender(),
                        human.getAge(),
                        human.getLastName()))
                .collect(Collectors.toList());
        // Ask the user whether to save the file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to save the sorted data to a file? (yes/no): ");
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if (userResponse.equals("yes") || userResponse.equals("да")) {
            // Write the list to a file
            fileToWrite(humanList);
        } else {
            System.out.println("Data was not saved to a file.");
        }
    }

    private static void sortBarrels(Barrel[] barrels) {
        System.out.println("Массив до сортировки: " + Arrays.toString(barrels));
        TimSort.timSort(barrels, Comparator.comparing(Barrel::getVolume));
        System.out.println("Массив после сортировки по объему: " + Arrays.toString(barrels));
        TimSort.timSort(barrels, Comparator.comparing(Barrel::getStoredMaterial));
        System.out.println("Массив после сортировки по хранимому материалу: " + Arrays.toString(barrels));
        TimSort.timSort(barrels, Comparator.comparing(Barrel::getMaterial));
        System.out.println("Массив после сортировки по материалу бочки: " + Arrays.toString(barrels));
        // Convert Barrel[] to List<String> in the desired format
        List<String> barrelList = Arrays.stream(barrels)
                .map(barrel -> String.format("%s,%s,%b",
                        barrel.getVolume(),
                        barrel.getStoredMaterial(),
                        barrel.getMaterial()))
                .collect(Collectors.toList());
        // Ask the user whether to save the file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to save the sorted data to a file? (yes/no): ");
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if (userResponse.equals("yes") || userResponse.equals("да")) {
            // Write the list to a file
            fileToWrite(barrelList);
        } else {
            System.out.println("Data was not saved to a file.");
        }


    }

}

