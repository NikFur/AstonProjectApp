package com.example.strategy.fill;

import com.example.factory.BuildObject;

import java.util.List;
import java.util.Scanner;


public class ManualArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final BuildObject<T> builderFactory;

    public ManualArrayFillingStrategy(BuildObject<T> builderFactory) {
        this.builderFactory = builderFactory;
    }

    @Override
    public T[] fillArray(int length) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[length];

        List<String> questions = builderFactory.getQuestions();

        for (int i = 0; i < length; i++) {
            String[] answers = new String[questions.size()];
            Scanner scanner = new Scanner(System.in);

            for (int j = 0; j < questions.size(); j++) {
                System.out.print(questions.get(j));
                answers[j] = scanner.nextLine();
            }

            array[i] = builderFactory.create(answers[0], answers[1], answers[2]);
        }

        return array;
    }
}

