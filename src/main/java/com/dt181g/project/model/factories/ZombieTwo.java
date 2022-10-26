package com.dt181g.project.model.factories;

import com.dt181g.project.model.Constants;

import java.util.Random;
/**
 * Class representing zombie two, implementing zombie.
 * @author Ebba Nimér
 */
public class ZombieTwo implements Zombie{
    Random random;
    int id;

    /**
     * Initialize zombie two with id and generate random instance.
     * @param id id of zombie
     */
    public ZombieTwo(int id){
        this.id = id;
        random = new Random();
    }

    /**
     * Return image path of zombie two to calling client.
     * @return image path.
     */
    @Override public String getPath() {
        return "src\\main\\resources\\images\\zombie2.png";
    }

    /**
     * Return start randomized x-coordinate of zombie two to calling client.
     * @return x-coordinate
     */
    @Override public Integer getStartX() {
        return random.nextInt((Constants.WIDTH - Constants.ICON_WIDTH) + 1);
    }

    /**
     * Return start randomized y-coordinate of zombie two to calling client.
     * @return y-coordinate
     */
    @Override public Integer getStartY() {
        return random.nextInt((Constants.HEIGHT - Constants.ICON_HEIGHT) + 1);
    }

}
