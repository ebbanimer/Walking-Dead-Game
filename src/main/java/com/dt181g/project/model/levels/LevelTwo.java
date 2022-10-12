package com.dt181g.project.model.levels;
public class LevelTwo extends LevelMethods{

    @Override
    public void createZombies() throws InterruptedException {
        theModel.createItem("Zombie", 10);
    }
}
