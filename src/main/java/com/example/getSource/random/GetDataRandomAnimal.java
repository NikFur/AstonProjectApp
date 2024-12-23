package com.example.getSource.random;

import com.example.getSource.GetData;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GetDataRandomAnimal implements GetData {

    private Faker faker = new Faker(new Locale("ru"));
    private Random random = new Random();

    @Override
    public List<String> get() {

        List<String> listAnimal = null;

        String species = String.valueOf(faker.animal());
        listAnimal.add(species);
        String colour = String.valueOf(faker.color());
        listAnimal.add(colour);
        String fur = String.valueOf(random.nextBoolean());
        listAnimal.add(fur);


        return listAnimal;
    }
}
