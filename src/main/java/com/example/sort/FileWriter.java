package com.example.sort;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class FileWriter {
    public static void fileToWrite (List<String> arrayTo) {

        List<String> arrayData = arrayTo;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя сохраняемого файла (например, data, animal): ");
        String fileName = scanner.nextLine();

        Path filePath = Paths.get("src/main/resources/" + fileName + ".txt");
        appendDataToFile(filePath, arrayData);
    }

    public static void saveSearchResult(Object data, Scanner scanner) {
        // Format the object data into the desired format
        String formattedData = formatObjectData(data);
        System.out.println(formattedData);

        System.out.print("Do you want to save the result to a file? (yes/no): ");
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(userResponse)) {
            System.out.print("Enter the name of the output file (e.g., search_result): ");
            String fileName = scanner.nextLine();

            Path filePath = Paths.get("src/main/resources/" + fileName + ".txt");
            appendDataToFile(filePath, List.of(formattedData));
        } else {
            System.out.println("Result was not saved.");
        }
    }

    private static String formatObjectData(Object data) {
        if (data instanceof Animal) {
            Animal animal = (Animal) data;
            return String.format("%s,%s,%b", animal.getSpecies(), animal.getEyeColor(), animal.hasFur());
        } else if (data instanceof Barrel) {
            Barrel barrel = (Barrel) data;
            return String.format("%s,%s,%s", barrel.getVolume(), barrel.getStoredMaterial(), barrel.getMaterial());
        } else if (data instanceof Human) {
            Human human = (Human) data;
            return String.format("%s,%s,%s", human.getGender(), human.getAge(), human.getLastName());
        } else {
            return data.toString();
        }
    }

    private static void appendDataToFile(Path filePath, List<String> data) {
        try {
            if (Files.exists(filePath)) {
                Files.write(filePath, data, StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.APPEND);
                System.out.println("Данные были добавлены в файл " + filePath.getFileName());
            } else {
                Files.write(filePath, data, StandardCharsets.UTF_8);
                System.out.println("Файл был создан и данные были добавлены в файл " + filePath.getFileName());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл " + e.getMessage());
        }
    }
}
