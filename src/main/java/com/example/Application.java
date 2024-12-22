package com.example;

import java.util.Arrays;
import java.util.Scanner;

import com.example.context.ArrayFillingContext;
import com.example.entity.Animal.Animal;
import com.example.strategy.length.FileBasedLengthStrategy;
import com.example.strategy.length.ManualLengthStrategy;
import com.example.strategy.length.RandomLengthStrategy;
import com.example.strategy.fill.ManualArrayFillingStrategy;
import com.example.strategy.fill.RandomArrayFillingStrategy;
import com.example.strategy.fill.FileArrayFillingStrategy;
import com.example.context.ArrayLengthContext;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayLengthContext lengthContext = new ArrayLengthContext();
        ArrayFillingContext<Animal> fillingContext = new ArrayFillingContext<>();

        System.out.println("Выберите способ задания длины массива:\n1. Вручную\n2. Рандомно\n3. Из файла");
        int lengthChoice = scanner.nextInt();
        switch (lengthChoice) {
            case 1 -> lengthContext.setStrategy(new ManualLengthStrategy());
            case 2 -> {
                System.out.print("Введите минимальную длину: ");
                int min = scanner.nextInt();
                System.out.print("Введите максимальную длину: ");
                int max = scanner.nextInt();
                lengthContext.setStrategy(new RandomLengthStrategy(min, max));
            }
            case 3 -> {
                System.out.print("Введите путь к файлу: ");
                scanner.nextLine();
                String filePath = scanner.nextLine();
                lengthContext.setStrategy(new FileBasedLengthStrategy(filePath));
            }
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }

        int length = lengthContext.getLength();
        System.out.println("Длина массива: " + length);

        System.out.println("Выберите способ заполнения массива:\n1. Вручную\n2. Рандомно\n3. Из файла");
        int fillChoice = scanner.nextInt();
        switch (fillChoice) {
            case 1 -> fillingContext.setStrategy(new ManualArrayFillingStrategy());
            case 2 -> fillingContext.setStrategy(new RandomArrayFillingStrategy());
            case 3 -> {
                System.out.print("Введите путь к файлу: ");
                scanner.nextLine();
                String filePath = scanner.nextLine();
                fillingContext.setStrategy(new FileArrayFillingStrategy(filePath));
            }
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }

        Animal[] animals = fillingContext.executeFill(length);
        System.out.println("Массив до сортировки: " + Arrays.toString(animals));

        Arrays.sort(animals);
        System.out.println("Массив после сортировки: " + Arrays.toString(animals));
    }
}
