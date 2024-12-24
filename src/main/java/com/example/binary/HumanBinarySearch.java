package com.example.binary;

import com.example.entity.Human.Human;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class HumanBinarySearch {

    private static <T> Optional<Human> search(Human[] humans, T key, Comparator<Human> comparator, Human target) {
        if (humans == null || humans.length == 0) return Optional.empty();

        Human[] sortedHumans = Arrays.copyOf(humans, humans.length);
        Arrays.sort(sortedHumans, comparator);

        int index = Arrays.binarySearch(sortedHumans, target, comparator);
        return (index >= 0) ? Optional.of(sortedHumans[index]) : Optional.empty();
    }

    public static Optional<Human> searchByGender(Human[] humans, String gender) {
        Human target = new Human.Builder().setGender(gender).build();
        return search(humans, gender, Comparator.comparing(human -> human.getGender().toLowerCase()), target);
    }

    public static Optional<Human> searchByAge(Human[] humans, int age) {
        Human target = new Human.Builder().setAge(age).build();
        return search(humans, age, Comparator.comparing(Human::getAge), target);
    }

    public static Optional<Human> searchByLastName(Human[] humans, String lastName) {
        Human target = new Human.Builder().setLastName(lastName).build();
        return search(humans, lastName, Comparator.comparing(human -> human.getLastName().toLowerCase()), target);
    }
}
