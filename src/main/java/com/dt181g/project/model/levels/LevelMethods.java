package com.dt181g.project.model.levels;

import com.dt181g.project.model.Model;

/**
 * Template Method class representing levels.
 * @author Ebba Nimér.
 */
public abstract class LevelMethods {
    Model model;

    /**
     * Initialize level by assigning model class, creating zombies and creating food.
     * @param model model
     */
    public final void initLevel(Model model){
        this.model = model;
        createZombies();
        createFood();
    }

    /**
     * To be defined by level classes.
     */
    public abstract void createZombies();

    /**
     * Create instances of salad and carrot.
     */
    public void createFood(){
        model.createItem("Salad", 3);
        model.createItem("Carrot", 2);
    }
}