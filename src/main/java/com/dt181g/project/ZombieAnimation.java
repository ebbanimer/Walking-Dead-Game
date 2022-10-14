package com.dt181g.project;

import com.dt181g.project.controller.GameController;
import com.dt181g.project.model.Constants;

import javax.swing.*;
import java.util.Random;
import java.util.TimerTask;

public class ZombieAnimation extends TimerTask{

    private final GameController controller;
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
        xVelocity = random.nextInt(4 + 2);
        yVelocity = random.nextInt(4 + 2);
    }

    @Override
    public void run() {
        do {
            if (x > Constants.WIDTH - Constants.ICON_WIDTH || x < 0) {
                xVelocity = xVelocity * -1;
            }
            x = x + xVelocity;

            if (y > Constants.HEIGHT - Constants.ICON_HEIGHT || y < 0) {
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
