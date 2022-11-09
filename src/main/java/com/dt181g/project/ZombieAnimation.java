package com.dt181g.project;
import com.dt181g.project.controller.GameController;

import javax.swing.*;
import java.util.Random;
import java.util.TimerTask;

/**
 * Class representing a timer-task for zombie-animation.
 * @author Ebba Nimér
 */
public class ZombieAnimation extends TimerTask{

    private final GameController controller;
    JLabel zombie;
    int x;
    int y;
    int xVelocity;
    int yVelocity;
    Random random;

    /**
     * Initialize class with zombie-label and game-controller.
     * @param zombie zombie label
     * @param controller game controller
     */
    public ZombieAnimation(JLabel zombie, GameController controller){
        this.controller = controller;
        this.zombie = zombie;

        // get x & y coordinates, and initialize random velocities.
        this.x = zombie.getX();
        this.y = zombie.getY();
        random = new Random();
        xVelocity = random.nextInt(4 + 2);
        yVelocity = random.nextInt(4 + 2);
    }

    /**
     * Run-method for moving zombies.
     */
    @Override public void run() {

        // While game is not over, move zombies based on current coordinates and velocities.
        do {
            if (x > Constants.WIDTH - Constants.ICON_WIDTH || x < 0) {
                xVelocity = xVelocity * -1;     // if zombie collides with border of panel, change direction.
            }
            x = x + xVelocity;

            if (y > Constants.HEIGHT - Constants.ICON_HEIGHT || y < 0) {
                yVelocity = yVelocity * -1;
            }
            y = y + yVelocity;

            zombie.setLocation(x, y);     // set new location
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!controller.getGameOver());
    }
}
