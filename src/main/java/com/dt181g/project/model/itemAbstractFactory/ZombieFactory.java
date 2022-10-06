package com.dt181g.project.model.itemAbstractFactory;

import java.util.ArrayList;
import java.util.List;

public class ZombieFactory implements AbstractFactory<Zombie> {

    List<Zombie> zombies = new ArrayList<>();

    @Override
    public List<Zombie> create(String choice, int amount) {
        for (int i = 0; i < amount; i++){
            zombies.add(new Zombie(i));
        }
        return zombies;
    }
}
