package com.example.strategy.fill;

import java.util.List;


import com.example.getSource.factory.BuildObject;
import com.example.getSource.file.GetDataFromFile;


public class FileArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final String fileName;
    private final BuildObject<T> object;



        public FileArrayFillingStrategy(String fileName, BuildObject<T> object) {
        this.fileName = fileName;
        this.object = object;
    }

    @Override
    public T[] fillArray(int length) {
        T[] array = (T[]) new Object[length];
//
        GetDataFromFile getDataFromFile = new GetDataFromFile(fileName);
        List<String> strings = getDataFromFile.get();

        if (!strings.isEmpty()) {
            for (int i = 0; i <array.length; i++) {
                String[] parts = strings.get(i).split(",");
                String value1 = parts[0];
                String value2 = parts[1];
                String value3 = parts[2];
                array[i] = object.create(value1, value2, value3);
            }
        }
        return array;
    }
}