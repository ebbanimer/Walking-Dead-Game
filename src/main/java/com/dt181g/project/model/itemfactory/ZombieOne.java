package com.dt181g.project.model.itemfactory;

import com.dt181g.project.Constants;

import java.util.Random;

import static com.dt181g.project.Constants.ZOMBIE_ONE_PATH;

/**
 * Class representing zombie one, implementing zombie.
 * @author Ebba Nimér
 */
public class ZombieOne implements Zombie {
    Random random;
    int id;

    /**
     * Initialize zombie one with id and generate random instance.
     * @param id id of zombie
     */
    public ZombieOne(int id){
        this.id = id;
        random = new Random();
    }

    /**
     * Return image path of zombie one to calling client.
     * @return image path.
     */
    @Override public String getPath() {
        return ZOMBIE_ONE_PATH;
    }

    /**
     * Return start randomized x-coordinate of zombie one to calling client.
     * @return x-coordinate
     */
    @Override public Integer getStartX() {
        return random.nextInt((Constants.WIDTH - Constants.ICON_WIDTH) + 1);
    }

    /**
     * Return start randomized y-coordinate of zombie one to calling client.
     * @return y-coordinate
     */
    @Override public Integer getStartY() {
        return random.nextInt((Constants.HEIGHT - Constants.ICON_HEIGHT) + 1);
    }

}
