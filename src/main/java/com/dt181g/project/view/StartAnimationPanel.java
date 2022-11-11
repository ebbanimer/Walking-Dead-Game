package com.dt181g.project.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Panel representing animated zombie in start-view.
 * @author Ebba Nimér
 */
public class StartAnimationPanel extends JPanel {

    private final int WIDTH = 250;
    private final int HEIGHT = 400;
    private final ImageIcon zombieImg;
    private final int MASTER_ZOMBIE_WIDTH;
    private final int MASTER_ZOMBIE_HEIGHT;

    /**
     * Initialize panel with size and image path.
     * @param size size.
     * @param path image path.
     */
    public StartAnimationPanel(int size, String path) throws IOException {
        ClassLoader cldr = this.getClass().getClassLoader();
        java.net.URL imageURL   = cldr.getResource(path);
        assert imageURL != null;
        zombieImg = new ImageIcon(imageURL);

        this.MASTER_ZOMBIE_WIDTH = size;
        this.MASTER_ZOMBIE_HEIGHT = size;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Paint image.
     * @param g graphics
     */
    public void paint(Graphics g){

        double x = (WIDTH * .5) - (MASTER_ZOMBIE_WIDTH * .5);
        double y = (HEIGHT * .5) - (MASTER_ZOMBIE_HEIGHT * .5);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, WIDTH, HEIGHT);     // paint background

        // draw image
        g2D.drawImage(zombieImg.getImage(), (int)x, (int)y, MASTER_ZOMBIE_WIDTH, MASTER_ZOMBIE_HEIGHT,
                null);
    }
}
