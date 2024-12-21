import java.io.*;
import java.util.*;

public class SortingApplication {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;

        while (running) {
            System.out.println("Пожалуйста выбирите опцию:");
            System.out.println("1. Заполнить массив в ручную");
            System.out.println("2. Заполнить массив рандомными значениями");
            System.out.println("3. Заполнить значениями из файла");
            System.out.println("4. Сортировка и поиск информации");
            System.out.println("5. Выход из программы");

            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Введены не корректные данные, пожалуйста ввидите число.");
                continue;
            }

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.println("Выберите класс массива:");
                    System.out.println("1. Животные ");
                    System.out.println("2. Бочки");
                    System.out.println("3. Люди");
                    int classChoice = 0;
                    try {
                        classChoice = Integer.parseInt(reader.readLine());
                        if (classChoice < 1 || classChoice > 3) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException | IOException e) {
                        System.out.println("Введены не корректные данные, пожалуйста ввидите число от 1-3");
                        continue;
                    }

                    System.out.println("Ввидите число элементов массива:");
                    int arrayLength = 0;
                    try {
                        arrayLength = Integer.parseInt(reader.readLine());
                        if (arrayLength <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException | IOException e) {
                        System.out.println("Введены не корректные данные, пожалуйста ввидите число.");
                        continue;
                    }

                    System.out.println("Тип класса: " + (classChoice == 1 ? "Животные" : classChoice == 2 ? "Бочки" : "Люди"));
                    System.out.println("Длинна элементов массива: " + arrayLength);
                    System.out.println("Выбор опции: " + choice);
                    break;
                case 4:
                    // Sort and search data
                    System.out.println("Выбранна опция: сортировки и поиска информации");
                    break;
                case 5:
                    running = false;
                    System.out.println("Выход из приложения");
                    break;
                default:
                    System.out.println("Пожалуйста повторите выбор опции");
            }
        }
    }
}
