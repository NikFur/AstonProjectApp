package com.example.fillArray;

import com.example.Human.Human;
import com.example.MyArray;
import com.example.buildClass.BuildHuman;
import com.example.buildClass.BuildObject;
import com.sun.tools.javac.code.Attribute;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FillArrayFromFile<T> implements FillArray<T> {
    @Override
    public MyArray<T> fill(int length) {
        MyArray<T> array = new MyArray<>(length);

        List<T> list = getListFromFile("source_human.txt");

        for (int i = 0; i < length; i++) {
            array.setArr(i, list.get(i));
        }

        return array;
    }

    private List<T> getListFromFile() throws ClassNotFoundException {
        String fileAddress = null;
        String clazz = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Введите адрес файла");
            fileAddress = reader.readLine();
            System.out.println("Выберите класс массива:");
            System.out.println("Human");
            System.out.println("Animal");
            System.out.println("Barrel");
            clazz = reader.readLine();
        }catch (IOException e){

        }


        switch (clazz){
            case "Human": Class.forName("com.example.Human.Human");
        }


        List<String> listStrings = null;
        List<T> listT = new ArrayList<>();

        try {
            listStrings = Files.readAllLines(Paths.get(fileAddress));
        } catch (IOException exception) {
            System.out.println("File not found");
        }

        for (String value : listStrings) {
            String[] lines = value.split(" ");
            String value1 = lines[0];
            String value2 = lines[2];
            String value3 = lines[3];
            T object = getObject(value1, value2, value3);
            listT.add(object);
        }

        return listT;
    }

    private T getObject(String clazz, String value1, String value2, String value3) {
        BuildObject<clazz> object = new BuildHuman();

        return (T) object.buildNewObject(value1, value2, value3);
    }
}
