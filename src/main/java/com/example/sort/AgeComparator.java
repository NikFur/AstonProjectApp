package com.example.sort;

import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.util.Comparator;

public class AgeComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        if (o1.getAge() % 2 != 0 && o2.getAge() % 2 != 0) {
            return 0;
        } else if (o1.getAge() % 2 != 0) {
            return -1;
        } else if (o2.getAge() % 2 != 0) {
            return 1;
        }
        // сортировка объектов с четным возрастом в натуральном порядке
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}

