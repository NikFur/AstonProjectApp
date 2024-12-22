package com.example.factory;


import com.example.entity.Human.Human;

public class BuildHuman implements BuildObject<Human>{
    @Override
    public Human create(String value1, String value2, String value3) {
        Human.Builder builder = new Human.Builder();
        return builder.setGender(value1).setAge(Integer.valueOf(value2)).setLastName(value3).build();
    }
}
