package com.dt181g.project;

import javax.swing.*;
import java.util.Random;

public class ZombieRunnable implements Runnable{

    JLabel lbl;
    int x;
    int y;
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;
    int randomXDirection;
    int randomYDirection;

    // Each zombie has a thread
    public ZombieRunnable(){
        this.lbl = lbl;
        x = lbl.getX();
        y = lbl.getY();
        random = new Random();
    }

    @Override
    public void run() {
        System.out.println("Runnable is running");
        randomXDirection = random.nextInt(2);

        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection = random.nextInt(2);

        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);

        move();

    }

    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }

    public void move(){
        System.out.println("Location for " + Thread.currentThread() + " is " + x + ", " + y);
        x += xVelocity;
        y += yVelocity;
        System.out.println("New location for " + Thread.currentThread() + " is " + x + ", " + y);
        lbl.setLocation(x, y);
    }
}
