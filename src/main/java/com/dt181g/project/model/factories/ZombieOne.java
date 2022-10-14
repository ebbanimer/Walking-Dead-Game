package com.dt181g.project.model.factories;

import com.dt181g.project.model.Constants;

import java.util.Random;

public class ZombieOne implements Zombie {
    Random random;
    final int HEIGHT = Constants.HEIGHT;
    final int WIDTH = Constants.WIDTH;
    final int ZOMBIE_WIDTH = Constants.ICON_WIDTH;
    final int ZOMBIE_HEIGHT = Constants.ICON_HEIGHT;
    int id;
    int x;
    int y;

    public ZombieOne(int id){
        this.id = id;
        random = new Random();
    }

    @Override
    public String getPath() {
        return "src\\main\\java\\com\\dt181g\\project\\images\\zombie1.png";
    }

    @Override
    public Integer getStartX() {
        x = random.nextInt((WIDTH - ZOMBIE_WIDTH) + 1);
        return x;
    }

    @Override
    public Integer getStartY() {
        y = random.nextInt((HEIGHT - ZOMBIE_HEIGHT) + 1);
        return y;
    }

}
