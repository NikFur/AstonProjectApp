package com.example.binary;

import com.example.entity.Animal.Animal;

import java.util.Arrays;
import java.util.Comparator;

public class AnimalBinarySearch {

    public static Animal searchBySpecies(Animal[] animals, String species) {
        Arrays.sort(animals, Comparator.comparing(animal -> animal.getSpecies().toLowerCase()));
        int index = Arrays.binarySearch(animals, new Animal.Builder().setSpecies(species.toLowerCase()).build(),
                Comparator.comparing(animal -> animal.getSpecies().toLowerCase()));
        return (index >= 0) ? animals[index] : null;
    }

    public static Animal searchByEyeColor(Animal[] animals, String eyeColor) {
        Arrays.sort(animals, Comparator.comparing(animal -> animal.getEyeColor().toLowerCase()));
        int index = Arrays.binarySearch(animals, new Animal.Builder().setEyeColor(eyeColor.toLowerCase()).build(),
                Comparator.comparing(animal -> animal.getEyeColor().toLowerCase()));
        return (index >= 0) ? animals[index] : null;
    }
    public static Animal searchByHasFur(Animal[] animals, boolean hasFur) {
        Arrays.sort(animals, Comparator.comparing(Animal::hasFur));
        int index = Arrays.binarySearch(animals, new Animal.Builder().setHasFur(hasFur).build(),
                Comparator.comparing(Animal::hasFur));
        return (index >= 0) ? animals[index] : null;
    }
}