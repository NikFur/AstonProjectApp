package com.example.buildClass;

import com.example.Barrel.Barrel;

public class BuildBarrel implements BuildObject<Barrel> {
    @Override
    public Barrel buildNewObject(String value1, String value2, String value3) {
        Barrel.Builder builder = new Barrel.Builder();
        Barrel barrel = builder.setVolume(Double.valueOf(value1)).setMaterial(value2).setStoredMaterial(value3).build();
        return barrel;
    }
}
