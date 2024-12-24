package com.example.binary;

import com.example.entity.Animal.Animal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class AnimalBinarySearch {

    private static <T> Optional<Animal> search(Animal[] animals, T key, Comparator<Animal> comparator, Animal target) {
        if (animals == null || animals.length == 0) return Optional.empty();

        Animal[] sortedAnimals = Arrays.copyOf(animals, animals.length);
        Arrays.sort(sortedAnimals, comparator);

        int index = Arrays.binarySearch(sortedAnimals, target, comparator);
        return (index >= 0) ? Optional.of(sortedAnimals[index]) : Optional.empty();
    }

    public static Optional<Animal> searchBySpecies(Animal[] animals, String species) {
        Animal target = new Animal.Builder().setSpecies(species).build();
        return search(animals, species, Comparator.comparing(animal -> animal.getSpecies().toLowerCase()), target);
    }

    public static Optional<Animal> searchByEyeColor(Animal[] animals, String eyeColor) {
        Animal target = new Animal.Builder().setEyeColor(eyeColor.toLowerCase()).build();
        return search(animals, eyeColor, Comparator.comparing(animal -> animal.getEyeColor().toLowerCase()), target);
    }

    public static Optional<Animal> searchByHasFur(Animal[] animals, boolean hasFur) {
        Animal target = new Animal.Builder().setHasFur(hasFur).build();
        return search(animals, hasFur, Comparator.comparing(Animal::hasFur), target);
    }
}