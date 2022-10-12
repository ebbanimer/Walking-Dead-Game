package com.dt181g.project.model.factories;

import java.util.Deque;
import java.util.LinkedList;

public class ZombieFactory implements AbstractFactory<Zombie> {

    Deque<Zombie> zombies = new LinkedList<>();

    @Override
    public Deque<Zombie> create(String choice, int amount) {
        for (int i = 0; i < amount; i++){
            zombies.add(new Zombie(i));
        }
        return zombies;
    }
}
