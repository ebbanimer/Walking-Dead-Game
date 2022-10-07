package com.dt181g.project.model.itemAbstractFactory;

import java.util.Random;

public class Food implements FoodInterface {

    private int id;
    Random rand;
    private final int WIDTH = 700;
    private final int HEIGHT = 600;
    int x = 60;
    int y = 60;

    public Food(int id){
        this.id = id;
        rand = new Random();
    }

    @Override
    public String getPath() {
        return "src\\main\\java\\com\\dt181g\\project\\images\\food.png";
    }

    public Integer getStartX(){
        return rand.nextInt((WIDTH - x) + 1);
    }

    public Integer getStartY(){
        return rand.nextInt((HEIGHT - y) + 1);
    }

    @Override
    public Boolean getCollision() {
        return false;
    }

}
