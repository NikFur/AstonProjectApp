package com.example.getSource.random;

import com.example.getSource.GetData;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GetDataRandomHuman implements GetData {
    private Faker faker = new Faker(new Locale("ru"));
    private Random random = new Random();

    private static final String[] GENDER= {"w", "m"};

    @Override
    public List<String> get() {

        List<String> listHuman = null;


        String gender = GENDER[random.nextInt(GENDER.length)];
        listHuman.add(gender);
        String age = String.valueOf(random.nextInt(100));
        listHuman.add(age);
        String surname = String.valueOf(faker.name().lastName());
        listHuman.add(surname);


        return listHuman;
    }
}
