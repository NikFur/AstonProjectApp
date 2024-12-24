package com.example.sort;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.util.Arrays;
import java.util.Comparator;


public class SortingService<T> {

    public static <T> void sortAndPrint(T[] dataArray) {
        if (dataArray == null || dataArray.length == 0) {
            System.out.println("Массив пуст.");
            return;
        }

        T firstElement = dataArray[0];

        if (firstElement instanceof Animal) {
            sortAnimals(Arrays.copyOf(dataArray, dataArray.length, Animal[].class));
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
}
