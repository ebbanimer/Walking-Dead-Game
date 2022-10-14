package com.dt181g.project.model.levels;

import com.dt181g.project.model.Model;

public abstract class LevelMethods {
    Model model;

    public final void initLevel(Model model){
        this.model = model;
        createZombies();
        createFood();
    }

    public abstract void createZombies();

    public void createFood(){
        model.createItem("Salad", 3);
        model.createItem("Carrot", 2);
    }
}