package com.example.strategy.length;

import java.util.Scanner;

public class ManualLengthStrategy implements ArrayLengthStrategy {
    @Override
    public int getLength() {
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        do {
            System.out.print("Введите длину массива (положительное число): ");
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();
            } else {
                scanner.next(); // Пропустить неверный ввод
            }
        } while (length <= 0);
        return length;
    }
}