package com.dt181g.project.model.levels;

/**
 * Class representing level one, deriving from template method class.
 * @author Ebba Nimér
 */
public class LevelOne extends LevelMethods{

    /**
     * Create 5 zombie one.
     */
    @Override public void createZombies() {
        model.createItem("ZombieOne", 5);
    }
}
