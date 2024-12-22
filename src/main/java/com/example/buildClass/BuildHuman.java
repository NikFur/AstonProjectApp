package com.example.buildClass;

import com.example.Human.Human;

public class BuildHuman implements BuildObject<Human> {
    @Override
    public Human buildNewObject(String value1, String value2, String value3) {
        Human.Builder builder = new Human.Builder();
        Human human = builder.setGender(value1).setAge(Integer.valueOf(value2)).setLastName(value3).build();
        return human;
    }
}
