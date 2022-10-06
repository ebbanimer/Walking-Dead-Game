package com.dt181g.project.model.itemAbstractFactory;

import java.util.ArrayList;
import java.util.List;

public class FoodFactory implements AbstractFactory<Food> {

    List<Food> foods = new ArrayList<>();

    @Override
    public List<Food> create(String choice, int amount) {
        for (int i = 0; i < amount; i++){
            foods.add(new Food(i));
        }
        return foods;
    }
}
