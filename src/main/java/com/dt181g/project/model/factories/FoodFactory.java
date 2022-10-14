package com.dt181g.project.model.factories;

import java.util.Deque;
import java.util.LinkedList;

public class FoodFactory implements AbstractFactory<Food> {

    Deque<Food> foods = new LinkedList<>();

    @Override
    public Deque<Food> create(String choice, int amount) {
        if ("Salad".equals(choice)){
            for (int i = 0; i < amount; i++){
                foods.add(new Salad(i));
            }
        } else if ("Carrot".equals(choice)){
            for (int i = 0; i < amount; i++){
                foods.add(new Carrot(i));
            }
        }
        return foods;
    }
}
