package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {

    int width = 100;
    int height = 600;
    int score;

    public AnimationPanel(int score){
        this.score = score;
        this.setPreferredSize(new Dimension(width, height));
    }

    public void paint(Graphics g){

        double x = (width * .5) - (score * .5);
        double y = (height * .5) - ( score * .5);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, width, height);

        if (150 < score){
            g2D.setPaint(Color.BLUE);
        } else if (100 <= score){
            g2D.setPaint(Color.GREEN);
        } else if (50 <= score){
            g2D.setPaint(Color.YELLOW);
        } else {
            g2D.setPaint(Color.RED);
        }

        g2D.fillOval((int)x, (int)y, score, score);

    }


}
