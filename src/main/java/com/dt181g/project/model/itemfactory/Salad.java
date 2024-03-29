package com.dt181g.project.model.itemfactory;

import com.dt181g.project.Constants;

import java.util.Random;

import static com.dt181g.project.Constants.SALAD_PATH;

/**
 * Class representing salad, implementing food.
 * @author Ebba Nimér
 */
public class Salad implements Food{
    Random random;
    int id;

    /**
     * Initialize salad with given id and initialize random instance.
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
        return SALAD_PATH;
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
