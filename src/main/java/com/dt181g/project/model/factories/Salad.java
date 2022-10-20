package com.dt181g.project.model.factories;

import com.dt181g.project.model.Constants;

import java.util.Random;

/**
 * Class representing salad, implementing food.
 * @author Ebba Nimér
 */
public class Salad implements Food{
    Random random;
    int id;

    /**
     * Initialize salad with given id and intialize random instance.
     * @param id id number
     */
    public Salad(int id){
        this.id = id;
        random = new Random();
    }

    /**
     * Return image path of salad to calling client.
     * @return image path.
     */
    @Override public String getPath() {
        return "src\\main\\java\\com\\dt181g\\project\\images\\salad.png";
    }

    /**
     * Return start randomized x-coordinate of salad to calling client.
     * @return x-coordinate
     */
    @Override public Integer getStartX() {
        return random.nextInt((Constants.WIDTH - Constants.ICON_WIDTH) + 1);
    }

    /**
     * Return start randomized y-coordinate of salad to calling client.
     * @return y-coordinate
     */
    @Override public Integer getStartY() {
        return random.nextInt((Constants.HEIGHT - Constants.ICON_HEIGHT) + 1);
    }
}
