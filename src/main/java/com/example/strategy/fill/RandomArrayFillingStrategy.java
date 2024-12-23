package com.example.strategy.fill;

import com.example.factory.BuildObject;
import com.example.getSource.GetData;

import java.util.List;

public class RandomArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final BuildObject<T> object;
    private final GetData getData;

    public RandomArrayFillingStrategy(BuildObject<T> object, GetData getData) {
        this.object = object;
        this.getData = getData;
    }

    @Override
    public T[] fillArray(int length) {
        
        T[] array = (T[]) new Object[length];

        for (int i = 0; i < length; i++) {

            List<String> list = getData.get();
            String value1 = list.get(0);
            String value2 = list.get(0);
            String value3 = list.get(0);

            array[i] = object.create(value1, value2, value3);
        }

        return array;
    }
}