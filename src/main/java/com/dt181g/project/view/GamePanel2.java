package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel2 extends JPanel implements KeyListener {

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);

    public GamePanel2(String path){
        this.setPreferredSize(new Dimension(700, 600));
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.add(setImg(path));

        this.setLayout(null);
    }

    private JLabel setImg(String path){
        charImage = new ImageIcon(path);
        Image img = charImage.getImage();
        Image modImg = img.getScaledInstance(75,75, Image.SCALE_SMOOTH);
        charImage.setImage(modImg);
        imgLbl.setIcon(charImage);

        return imgLbl;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released key char: " + e.getKeyChar());
    }
}
