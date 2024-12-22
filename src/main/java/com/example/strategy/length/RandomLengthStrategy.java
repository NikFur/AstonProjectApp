package com.example.strategy.length;

import java.util.Random;

public class RandomLengthStrategy implements ArrayLengthStrategy {
    private final int min;
    private final int max;

    public RandomLengthStrategy(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int getLength() {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
