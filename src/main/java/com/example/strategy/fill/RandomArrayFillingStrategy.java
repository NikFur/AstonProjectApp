package com.example.strategy.fill;

import com.example.factory.BuildObject;

import java.util.Random;

public class RandomArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private static final String[] SPECIES = {"Cat", "Dog", "Bird"};
    private static final String[] EYE_COLORS = {"Blue", "Green", "Brown"};

    private final BuildObject<T> object;

    public RandomArrayFillingStrategy(BuildObject<T> object) {
        this.object = object;
    }


    @Override
    public T[] fillArray(int length) {
        Random random = new Random();
        T[] array = (T[]) new Object[length];
        for (int i = 0; i < length; i++) {
            array[i] = object.create(SPECIES[random.nextInt(SPECIES.length)]
                    , EYE_COLORS[random.nextInt(EYE_COLORS.length)]
            , String.valueOf(random.nextInt(50)));
        }
        return array;
    }
}