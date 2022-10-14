package com.dt181g.project.model.factories;

import com.dt181g.project.Constants;

import java.util.Random;

public class Salad implements Food{
    Random random;
    int id;

    public Salad(int id){
        this.id = id;
        random = new Random();
    }

    @Override
    public String getPath() {
        return "src\\main\\java\\com\\dt181g\\project\\images\\salad.png";
    }

    @Override
    public Integer getStartX() {
        return random.nextInt((Constants.WIDTH - Constants.ICON_WIDTH) + 1);
    }

    @Override
    public Integer getStartY() {
        return random.nextInt((Constants.HEIGHT - Constants.ICON_HEIGHT) + 1);
    }
}
