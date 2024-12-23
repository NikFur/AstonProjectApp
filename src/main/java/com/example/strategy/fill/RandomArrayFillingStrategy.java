package com.example.strategy.fill;

import com.example.factory.BuildObject;

import java.util.Random;

public class RandomArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final BuildObject<T> builder;

    public RandomArrayFillingStrategy(BuildObject<T> builder) {
        this.builder = builder;
    }

    @Override
    public T[] fillArray(int length) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            String value1 = getRandomValue(builder, 0, random);
            String value2 = getRandomValue(builder, 1, random);
            String value3 = getRandomValue(builder, 2, random);

            array[i] = builder.create(value1, value2, value3);
        }

        return array;
    }

    private String getRandomValue(BuildObject<T> builder, int index, Random random) {
        switch (index) {
            case 0 -> {
                if (builder instanceof com.example.factory.BuildAnimal) {
                    String[] species = {"Cat", "Dog", "Bird"};
                    return species[random.nextInt(species.length)];
                } else if (builder instanceof com.example.factory.BuildHuman) {
                    String[] genders = {"Male", "Female"};
                    return genders[random.nextInt(genders.length)];
                } else if (builder instanceof com.example.factory.BuildBarrel) {
                    String[] volumes = {"50", "100", "200"};
                    return volumes[random.nextInt(volumes.length)];
                }
            }
            case 1 -> {
                if (builder instanceof com.example.factory.BuildAnimal) {
                    String[] eyeColors = {"Blue", "Green", "Brown"};
                    return eyeColors[random.nextInt(eyeColors.length)];
                } else if (builder instanceof com.example.factory.BuildHuman) {
                    String[] ages = {"20", "30", "40", "50"};
                    return ages[random.nextInt(ages.length)];
                } else if (builder instanceof com.example.factory.BuildBarrel) {
                    String[] materials = {"Water", "Oil", "Grain"};
                    return materials[random.nextInt(materials.length)];
                }
            }
            case 2 -> {
                if (builder instanceof com.example.factory.BuildAnimal) {
                    String[] hasFur = {"true", "false"};
                    return hasFur[random.nextInt(hasFur.length)];
                } else if (builder instanceof com.example.factory.BuildHuman) {
                    String[] lastNames = {"Smith", "Johnson", "Brown"};
                    return lastNames[random.nextInt(lastNames.length)];
                } else if (builder instanceof com.example.factory.BuildBarrel) {
                    String[] barrelMaterials = {"Wood", "Metal", "Plastic"};
                    return barrelMaterials[random.nextInt(barrelMaterials.length)];
                }
            }
        }
        return "";
    }
}
