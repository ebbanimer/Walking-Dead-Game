package com.dt181g.project.model.factories;

import java.util.Random;

public class Zombie implements ZombieInterface {
    Random random;
    final String path;
    final int id;
    int x = 80;
    int y = 80;
    int startX;
    int startY;


    public Zombie(int id) {
        this.id = id;
        random = new Random();
        startX = random.nextInt((700 - x) + 1);
        startY = random.nextInt((600 - y) + 1);
        path = "src\\main\\java\\com\\dt181g\\project\\images\\zombie.png";
    }

    public String getPath(){
        return path;
    }

    public Integer getID(){
        return id;
    }

    public Integer getStartX(){
        return startX;
    }

    public Integer getStartY(){
        return startY;
    }

    /*

    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;
    int randomXDirection;
    int randomYDirection;

    @Override
    public void run() {
        while(true){
            System.out.println("zombie is moving");
            if(randomXDirection == 0)
                randomXDirection--;
            setXDirection(randomXDirection*initialSpeed);

            int randomYDirection = random.nextInt(2);

            if(randomYDirection == 0)
                randomYDirection--;
            setYDirection(randomYDirection*initialSpeed);

            move();
        }
    }

    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }

    public void move(){
        System.out.println("Location for " + Thread.currentThread() + " is " + startX + ", " + startY);
        startX += xVelocity;
        startY += yVelocity;
        System.out.println("New location for " + Thread.currentThread() + " is " + startX + ", " + startY);
        //lbl.setLocation(x, y);
    }

    public Integer getNewX(){
        return startX;
    }

    public Integer getNewY(){
        return startY;
    }*/

}
