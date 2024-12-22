package com.example.buildClass;

import com.example.Animal.Animal;

public class BuildAnimal implements BuildObject<Animal> {
    @Override
    public Animal buildNewObject(String value1, String value2, String value3) {
        Animal.Builder builder = new Animal.Builder();
        Animal animal = builder.setSpecies(value1).setEyeColor(value2).setHasFur(Boolean.valueOf(value3)).build();
        return animal;
    }
}
