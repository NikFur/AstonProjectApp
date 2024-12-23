package com.example.getSource.console;

import com.example.getSource.GetData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GetDataManuallyBarrel implements GetData {
    @Override
    public List<String> get() {
        List<String> listBarrel= null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите данные для бочки :");
            System.out.print("Объем: ");
            String value1 = reader.readLine();
            listBarrel.add(value1);
            System.out.print("Хранимый материал}: ");
            String value2 = reader.readLine();
            listBarrel.add(value2);
            System.out.print("Материал из которого изготовлена: ");
            String value3 = reader.readLine();
            listBarrel.add(value3);
        }catch (IOException e){}

        return listBarrel;
    }
}
