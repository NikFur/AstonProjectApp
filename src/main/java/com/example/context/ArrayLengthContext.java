package com.example.context;

import com.example.strategy.length.ArrayLengthStrategy;

public class ArrayLengthContext {
    private ArrayLengthStrategy strategy;

    public void setStrategy(ArrayLengthStrategy strategy) {
        this.strategy = strategy;
    }

    public int getLength() {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия длины массива не установлена.");
        }
        return strategy.getLength();
    }
}
