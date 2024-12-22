package com.example.RandomGenerator;

import com.example.Animal.Animal;
import com.example.Barrel.Barrel;
import com.example.Human.Human;

import java.util.Random;

// Random Data Generator
public class RandomDataGenerator {
    private static final Random RANDOM = new Random();

    // Generate random Animal
    public static Animal generateRandomAnimal() {
        return new Animal.Builder()
                .setSpecies(Species.values()[RANDOM.nextInt(Species.values().length)].name())
                .setEyeColor(EyeColor.values()[RANDOM.nextInt(EyeColor.values().length)].name())
                .setHasFur(RANDOM.nextBoolean())
                .build();
    }

    // Generate random Barrel
    public static Barrel generateRandomBarrel() {
        return new Barrel.Builder()
                .setVolume(1 + (Math.round(99 * RANDOM.nextDouble())))
                .setStoredMaterial(StoredMaterial.values()[RANDOM.nextInt(StoredMaterial.values().length)].name())
                .setMaterial(Material.values()[RANDOM.nextInt(Material.values().length)].name())
                .build();
    }

    // Generate random Human
    public static Human generateRandomHuman() {
        return new Human.Builder()
                .setGender(Gender.values()[RANDOM.nextInt(Gender.values().length)].name())
                .setAge(1 + RANDOM.nextInt(90))
                .setLastName(LastNames.values()[RANDOM.nextInt(LastNames.values().length)].name())
                .build();
    }
}
