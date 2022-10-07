package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);

    public GamePanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.add(addImg());
    }

    public JLabel addImg(){
        imgLbl.setBounds(0, 0, 60, 60);
        return imgLbl;
    }

}
