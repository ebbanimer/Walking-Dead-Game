package com.dt181g.project.model.levels;

import com.dt181g.project.model.Model;

public abstract class LevelMethods {

    Model theModel;

    public final void initLevel(Model theModel) throws InterruptedException {
        this.theModel = theModel;
        createZombies();
        createFood();
        moveZombies();
    }

    public abstract void createZombies() throws InterruptedException;

    private void createFood() throws InterruptedException {
        theModel.createItem("Food", 5);
    }

    private void moveZombies(){

    }
}