package com.example.factory;

import com.example.entity.Animal.Animal;
import com.example.entity.Human.Human;

public class BuildAnimal implements BuildObject<Animal> {
    @Override
    public Animal create(String value1, String value2, String value3) {
        Animal.Builder builder = new Animal.Builder();
        return builder.setSpecies(value1).setEyeColor(value2).setHasFur(Boolean.valueOf(value3)).build();
    }
}
