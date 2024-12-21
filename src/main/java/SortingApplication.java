import java.io.*;
import java.util.*;

public class SortingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введены не корректные данные, пожалуйста ввидите число.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Fill data manually
                    System.out.println("Выбранна опция: Ручного ввода");
                    break;
                case 2:
                    // Fill data randomly
                    System.out.println("Выбранна опция: Рандомной генерации данных");
                    break;
                case 3:
                    // Fill data from file
                    System.out.println("Выбранна опция: Заполнения данных из файла");
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
