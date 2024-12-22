package com.example.strategy.fill;
import com.example.entity.Animal.Animal;
import java.util.Scanner;



public class ManualArrayFillingStrategy implements ArrayFillingStrategy<Animal> {
    @Override
    public Animal[] fillArray(int length) {
        Scanner scanner = new Scanner(System.in);
        Animal[] array = new Animal[length];
        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для животного " + (i + 1) + ":");
            System.out.print("Вид: ");
            String species = scanner.nextLine();
            System.out.print("Цвет глаз: ");
            String eyeColor = scanner.nextLine();
            System.out.print("Есть ли шерсть (true/false): ");
            boolean hasFur = scanner.nextBoolean();
            scanner.nextLine(); // Очистка буфера

            array[i] = new Animal.Builder()
                    .setSpecies(species)
                    .setEyeColor(eyeColor)
                    .setHasFur(hasFur)
                    .build();
        }
        return array;
    }
}
