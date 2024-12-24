package com.example.strategy.fill;

import com.example.getSource.factory.BuildObject;

public class ManualArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final BuildObject<T> builderFactory;

    public ManualArrayFillingStrategy(BuildObject<T> builderFactory) {
        this.builderFactory = builderFactory;
    }

    @Override
    public T[] fillArray(int length) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[length];

        for (int i = 0; i < length; i++) {

            array[i] = builderFactory.create(null, null, null);
        }

        return array;
    }
}
