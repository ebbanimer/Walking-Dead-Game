package com.dt181g.project.model.factories;

import com.dt181g.project.model.Constants;

import java.util.Random;

/**
 * Class representing carrot, implementing food.
 * @author Ebba Nimér
 */
public class Carrot implements Food{
    Random random;
    int id;

    /**
     * Initialize carrot with id and generate random instance.
     * @param id id of carrot
     */
    public Carrot(int id){
        this.id = id;
        random = new Random();
    }

    /**
     * Return image path of carrot to calling client.
     * @return image path.
     */
    @Override public String getPath() {
        return "src\\main\\resources\\images\\carrot.png";
    }

    /**
     * Return start randomized x-coordinate of carrot to calling client.
     * @return x-coordinate
     */
    @Override public Integer getStartX() {
        return random.nextInt((Constants.WIDTH - Constants.ICON_WIDTH) + 1);
    }

    /**
     * Return start randomized y-coordinate of carrot to calling client.
     * @return y-coordinate
     */
    @Override public Integer getStartY() {
        return random.nextInt((Constants.HEIGHT - Constants.ICON_HEIGHT) + 1);
    }
}
