package com.example.strategy.fill;

import com.example.factory.BuildObject;

import java.util.Scanner;



public class ManualArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final BuildObject<T> object;

    public ManualArrayFillingStrategy(BuildObject<T> object) {
        this.object = object;
    }

    @Override
    public T[] fillArray(int length) {
        Scanner scanner = new Scanner(System.in);
        T[] array = (T[]) new Object[length];
        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для животного " + (i + 1) + ":");
            System.out.print("Вид: ");
            String value1 = scanner.nextLine();
            System.out.print("Цвет глаз: ");
            String value2 = scanner.nextLine();
            System.out.print("Есть ли шерсть (true/false): ");
            String value3 = scanner.nextLine();
            scanner.nextLine(); // Очистка буфера

            array[i] = object.create(value1, value2, value3);
        }
        return array;
    }
}
