package com.dt181g.project.model.itemfactory;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Class representing zombie-factory, implementing abstract factory.
 * @author Ebba Nimér
 */
public class ZombieFactory implements AbstractFactory<Zombie>{
    Deque<Zombie> zombies = new LinkedList<>();

    /**
     * Create zombie-type based on string, and add to list.
     * @param choice type of zombie.
     * @param amount amount of zombies.
     * @return list of zombies.
     */
    @Override public Deque<Zombie> create(String choice, int amount) {
        if ("ZombieOne".equals(choice)){
            for (int i = 0; i < amount; i++){
                zombies.add(new ZombieOne(i));
            }
        } else if ("ZombieTwo".equals(choice)){
            for (int i = 0; i < amount; i++){
                zombies.add(new ZombieTwo(i));
            }
        }
        return zombies;
    }
}
