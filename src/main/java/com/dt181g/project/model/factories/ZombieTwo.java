package com.dt181g.project.model.factories;

import com.dt181g.project.Constants;

import java.util.Random;

public class ZombieTwo implements Zombie{
    Random random;
    final int HEIGHT = Constants.HEIGHT;
    final int WIDTH = Constants.WIDTH;
    final int ZOMBIE_WIDTH = Constants.ICON_WIDTH;
    final int ZOMBIE_HEIGHT = Constants.ICON_HEIGHT;
    int id;

    public ZombieTwo(int id){
        this.id = id;
        random = new Random();
    }

    @Override
    public String getPath() {
        return "src\\main\\java\\com\\dt181g\\project\\images\\zombie2.png";
    }

    @Override
    public Integer getStartX() {
        return random.nextInt((WIDTH - ZOMBIE_WIDTH) + 1);
    }

    @Override
    public Integer getStartY() {
        return random.nextInt((HEIGHT - ZOMBIE_HEIGHT) + 1);
    }

}
