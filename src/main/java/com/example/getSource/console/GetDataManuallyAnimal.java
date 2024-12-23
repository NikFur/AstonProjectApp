package com.example.getSource.console;

import com.example.getSource.GetData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GetDataManuallyAnimal implements GetData {

    @Override
    public List<String> get() {
        List<String> listAnimal = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите данные для животного :");
            System.out.print("Вид: ");
            String value1 = reader.readLine();
            listAnimal.add(value1);
            System.out.print("Цвет глаз: ");
            String value2 = reader.readLine();
            listAnimal.add(value2);
            System.out.print("Есть ли шерсть (true/false): ");
            String value3 = reader.readLine();
            listAnimal.add(value3);
        }catch (IOException e){}

        return listAnimal;
    }
}
