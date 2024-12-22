package com.example.factory;

public interface BuildObject<T> {

    T create(String value1, String value2, String value3);
}
