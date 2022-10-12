package com.dt181g.project.model.factories;


import java.util.Random;

public class Food implements FoodInterface {
    Random random;
    final String path;
    final int id;
    int x = 60;
    int y = 60;

    public Food(int id){
        this.id = id;
        random = new Random();
        path = "src\\main\\java\\com\\dt181g\\project\\images\\food.png";
    }

    public String getPath(){
        return path;
    }

    public Integer getID(){
        return id;
    }

    public Integer getStartX(){
        return random.nextInt((700 - x) + 1);
    }

    public Integer getStartY(){
        return random.nextInt((600 - y) + 1);
    }
}
