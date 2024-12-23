package com.example.getSource.console;

import com.example.getSource.GetData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GetDataManuallyHuman implements GetData {
    @Override
    public List<String> get() {
        List<String> listHuman= null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите данные для человека :");
            System.out.print("Пол: ");
            String value1 = reader.readLine();
            listHuman.add(value1);
            System.out.print("Возраст: ");
            String value2 = reader.readLine();
            listHuman.add(value2);
            System.out.print("Фамилия: ");
            String value3 = reader.readLine();
            listHuman.add(value3);
        }catch (IOException e){}

        return listHuman;
    }
}
