package com.dt181g.project;

import com.dt181g.project.controller.GameController;

import javax.swing.*;
import java.util.Random;
import java.util.TimerTask;

public class ZombieAnimation extends TimerTask{

    private final int WIDTH = 700;
    private final int HEIGHT = 600;
    private final int zombieWidth = 60;
    private final int zombieHeight = 60;
    public volatile boolean gameOver = false;
    private GameController controller;
    JLabel zombie;
    int x;
    int y;
    int xVelocity;
    int yVelocity;
    Random random;

    public ZombieAnimation(JLabel zombie, GameController controller){
        this.controller = controller;
        this.zombie = zombie;
        this.x = zombie.getX();
        this.y = zombie.getY();
        random = new Random();
        xVelocity = random.nextInt(3 + 1);
        yVelocity = random.nextInt(3 +1);
    }

    @Override
    public void run() {
        do {
            if (x > WIDTH - zombieWidth || x < 0) {
                xVelocity = xVelocity * -1;
            }
            x = x + xVelocity;

            if (y > HEIGHT - zombieHeight || y < 0) {
                yVelocity = yVelocity * -1;
            }
            y = y + yVelocity;

            zombie.setLocation(x, y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!controller.getGameOver());
    }
}
