package com.example.factory;

import java.util.List;

public interface BuildObject<T> {

    T create(String value1, String value2, String value3);
    List<String> getQuestions();
}
