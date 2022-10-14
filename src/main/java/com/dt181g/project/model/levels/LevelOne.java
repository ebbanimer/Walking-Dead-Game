package com.dt181g.project.model.levels;

public class LevelOne extends LevelMethods{
    @Override
    public void createZombies() {
        model.createItem("ZombieOne", 5);
    }
}
