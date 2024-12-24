package com.example.sort;

import com.example.entity.Barrel.Barrel;
import com.example.entity.Human.Human;

import java.util.Comparator;

public class AgeAndVolComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Human && o2 instanceof Human) {
            Human human1 = (Human) o1;
            Human human2 = (Human) o2;

            if (human1.getAge() % 2 != 0 && human2.getAge() % 2 != 0) {
                return 0;
            } else if (human1.getAge() % 2 != 0) {
                return -1;
            } else if (human2.getAge() % 2 != 0) {
                return 1;
            }

            return Integer.compare(human1.getAge(), human2.getAge()); }
        else if (o1 instanceof Barrel && o2 instanceof Barrel){
            Barrel barrel1 = (Barrel) o1;
            Barrel barrel2 = (Barrel) o2;

            if (barrel1.getVolume() % 2 != 0 && barrel2.getVolume() % 2 != 0) {
                return 0;
            } else if (barrel1.getVolume() % 2 != 0) {
                return -1;
            } else if (barrel2.getVolume() % 2 != 0) {
                return 1;
            }

            return Integer.compare((int) barrel1.getVolume(), (int) barrel2.getVolume()); }

        return 0;
    }
}

