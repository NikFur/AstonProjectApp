package com.example.getSource.random;

import com.example.getSource.GetData;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GetDataRandomBarrel implements GetData {
    private Faker faker = new Faker(new Locale("ru"));
    private Random random = new Random();

    private static final String[] MATERIAL= {"iron", "plastic", "wood"};

    @Override
    public List<String> get() {

        List<String> listBarrel = null;

        String volume = String.valueOf(random.nextInt(100));
        listBarrel.add(volume);
        String liquid = String.valueOf(faker.beer());
        listBarrel.add(liquid);
        String material = MATERIAL[random.nextInt(MATERIAL.length)];
        listBarrel.add(material);


        return listBarrel;
    }
}
