package com.example.sort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class FileWriter {
    public static void fileToWrite (List<String> arrayTo) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя сохраняемого файла (например, data, animal): ");
        String fileName = scanner.nextLine();

        Path filePath = Paths.get("src/main/resources/" + fileName + ".txt");
        appendDataToFile(filePath, arrayTo);
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
