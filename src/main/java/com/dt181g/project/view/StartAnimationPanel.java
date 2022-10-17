package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;

public class StartAnimationPanel extends JPanel {

    private final int WIDTH = 250;
    private final int HEIGHT = 400;
    private final ImageIcon zombieImg;
    private final int MASTER_ZOMBIE_WIDTH;
    private final int MASTER_ZOMBIE_HEIGHT;

    public StartAnimationPanel(int size, String path){
        zombieImg = new ImageIcon(path);
        this.MASTER_ZOMBIE_WIDTH = size;
        this.MASTER_ZOMBIE_HEIGHT = size;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paint(Graphics g){

        double x = (WIDTH * .5) - (MASTER_ZOMBIE_WIDTH * .5);
        double y = (HEIGHT * .5) - (MASTER_ZOMBIE_HEIGHT * .5);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, WIDTH, HEIGHT);

        g2D.drawImage(zombieImg.getImage(), (int)x, (int)y, MASTER_ZOMBIE_WIDTH, MASTER_ZOMBIE_HEIGHT, null);
    }
}
