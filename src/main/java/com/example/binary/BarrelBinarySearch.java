package com.example.binary;

import com.example.entity.Barrel.Barrel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class BarrelBinarySearch {

    private static <T> Optional<Barrel> search(Barrel[] barrels, T key, Comparator<Barrel> comparator, Barrel target) {
        if (barrels == null || barrels.length == 0) return Optional.empty();

        Barrel[] sortedBarrels = Arrays.copyOf(barrels, barrels.length);
        Arrays.sort(sortedBarrels, comparator);

        int index = Arrays.binarySearch(sortedBarrels, target, comparator);
        return (index >= 0) ? Optional.of(sortedBarrels[index]) : Optional.empty();
    }

    public static Optional<Barrel> searchByVolume(Barrel[] barrels, double volume) {
        Barrel target = new Barrel.Builder().setVolume(volume).build();
        return search(barrels, volume, Comparator.comparing(Barrel::getVolume), target);
    }

    public static Optional<Barrel> searchByStoredMaterial(Barrel[] barrels, String storedMaterial) {
        Barrel target = new Barrel.Builder().setStoredMaterial(storedMaterial).build();
        return search(barrels, storedMaterial, Comparator.comparing(barrel -> barrel.getStoredMaterial().toLowerCase()), target);
    }

    public static Optional<Barrel> searchByMaterial(Barrel[] barrels, String material) {
        Barrel target = new Barrel.Builder().setMaterial(material).build();
        return search(barrels, material, Comparator.comparing(barrel -> barrel.getMaterial().toLowerCase()), target);
    }
}

