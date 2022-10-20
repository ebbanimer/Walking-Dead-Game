package com.dt181g.project.model.levels;

/**
 * Class representing level two, deriving from template method class.
 * @author Ebba Nimér
 */
public class LevelTwo extends LevelMethods{

    /**
     * Create 5 instances of zombie two.
     */
    @Override public void createZombies() {
        model.createItem("ZombieTwo", 5);
    }
}
