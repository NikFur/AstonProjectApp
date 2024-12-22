package com.example.context;

import com.example.strategy.fill.ArrayFillingStrategy;

public class ArrayFillingContext<T> {
    private ArrayFillingStrategy<T> strategy;

    public void setStrategy(ArrayFillingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public T[] executeFill(int length) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия заполнения не установлена.");
        }
        return strategy.fillArray(length);
    }
}

