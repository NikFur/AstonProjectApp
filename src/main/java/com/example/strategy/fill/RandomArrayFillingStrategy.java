package com.example.strategy.fill;
import com.example.entity.Animal.Animal;
import java.util.Random;

public class RandomArrayFillingStrategy implements ArrayFillingStrategy<Animal> {
    private static final String[] SPECIES = {"Cat", "Dog", "Bird"};
    private static final String[] EYE_COLORS = {"Blue", "Green", "Brown"};

    @Override
    public Animal[] fillArray(int length) {
        Random random = new Random();
        Animal[] array = new Animal[length];

        for (int i = 0; i < length; i++) {
            array[i] = new Animal.Builder()
                    .setSpecies(SPECIES[random.nextInt(SPECIES.length)])
                    .setEyeColor(EYE_COLORS[random.nextInt(EYE_COLORS.length)])
                    .setHasFur(random.nextBoolean())
                    .build();
        }

        return array;
    }
}
