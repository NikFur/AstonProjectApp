package com.example.factory;

import com.example.entity.Animal.Animal;
import com.example.entity.Barrel.Barrel;

public class BuildBarrel implements BuildObject<Barrel>{
    @Override
    public Barrel create(String value1, String value2, String value3) {
        Barrel.Builder builder = new Barrel.Builder();
        return builder.setVolume(Double.valueOf(value1)).setStoredMaterial(value2).setMaterial(value3).build();
    }
}
