package com.example.strategy.fill;

import com.example.getSource.factory.BuildAnimal;
import com.example.getSource.factory.BuildBarrel;
import com.example.getSource.factory.BuildHuman;
import com.example.getSource.factory.BuildObject;

import java.util.Random;

public class RandomArrayFillingStrategy<T> implements ArrayFillingStrategy<T> {
    private final BuildObject<T> builder;

    public RandomArrayFillingStrategy(BuildObject<T> builder) {
        this.builder = builder;
    }
    @Override
    public T[] fillArray(int length) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            String value1 = getRandomValue(builder, 0, random);
            String value2 = getRandomValue(builder, 1, random);
            String value3 = getRandomValue(builder, 2, random);

            array[i] = builder.create(value1, value2, value3);
        }

        return array;
    }

    private String getRandomValue(BuildObject<T> builder, int index, Random random) {
        switch (index) {
            case 0 -> {
                if (builder instanceof BuildAnimal) {
                    String[] species = {"Собака", "Кошка", "Лошадь", "Рыба", "Корова", "Овца", "Курица", "Утка", "Верблюд", "Слон", "Тигр", "Лев", "Волк", "Лиса", "Заяц", "Тушкан"};
                    return species[random.nextInt(species.length)];
                } else if (builder instanceof BuildHuman) {
                    String[] genders = {"Мужчина", "Женщина"};
                    return genders[random.nextInt(genders.length)];
                } else if (builder instanceof BuildBarrel) {
                    return String.valueOf(1 + (Math.round(99 * random.nextDouble())));
                }
            }
            case 1 -> {
                if (builder instanceof BuildAnimal) {
                    String[] eyeColors = {"Карие", "Голубые", "Зелёные", "Серые", "Чёрные", "Жёлтые"};
                    return eyeColors[random.nextInt(eyeColors.length)];
                } else if (builder instanceof BuildHuman) {
                    return String.valueOf(1 + random.nextInt(90));
                } else if (builder instanceof BuildBarrel) {
                    String[] materials = {"Вода", "Масло", "Зерно", "Вино", "Мёд", "Нефть", "Бензин", "Сок", "Пиво", "Водочка"};
                    return materials[random.nextInt(materials.length)];
                }
            }
            case 2 -> {
                if (builder instanceof BuildAnimal) {
                    String[] hasFur = {"true", "false"};
                    return hasFur[random.nextInt(hasFur.length)];
                } else if (builder instanceof BuildHuman) {
                    String[] lastNames = {"Ангел", "Герц", "Бронте", "Ларсен", "Маршалл", "Маршак", "Мишель", "Перси", "Ривера", "Илон",};
                    return lastNames[random.nextInt(lastNames.length)];
                } else if (builder instanceof BuildBarrel) {
                    String[] barrelMaterials = {"Дерево", "Металл", "Пластик", "Керамика"};
                    return barrelMaterials[random.nextInt(barrelMaterials.length)];
                }
            }
        }
        return "";
    }
}
