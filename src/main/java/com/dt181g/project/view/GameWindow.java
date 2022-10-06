package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener{

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    JLabel timerLbl = new JLabel();
    JButton endGameBtn = new JButton("End game");

    public GameWindow(){
        this.setTitle("Game");
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setLayout(new BorderLayout());
        this.add(addBtnPanel(), BorderLayout.WEST);
        this.add(addGamePanel(), BorderLayout.EAST);
        this.pack();

        //this.add(new GamePanel2(path), BorderLayout.EAST);
    }

    private JPanel addBtnPanel(){
        JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(150, 600));
        btnPanel.setBackground(Color.DARK_GRAY);
        btnPanel.add(endGameBtn);

        return btnPanel;
    }

    private JPanel addStatsPanel(){
        JPanel statsPanel = new JPanel();
        statsPanel.add(timerLbl);
        return statsPanel;
    }

    public JPanel addGamePanel(){
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(700, 600));
        gamePanel.setBackground(Color.BLACK);

        imgLbl.setBounds(0, 0, 50, 50);

        gamePanel.add(imgLbl);
        return gamePanel;
    }

    public void addZombie(int amount){

    }

    public void setCharacter(String path){
        charImage = new ImageIcon(path);
        imgLbl.setIcon(charImage);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Move the image-label of character
        switch (e.getKeyCode()) {
            case 37 -> imgLbl.setLocation(imgLbl.getX() - 10, imgLbl.getY());
            case 38 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() - 10);
            case 39 -> imgLbl.setLocation(imgLbl.getX() + 10, imgLbl.getY());
            case 40 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() + 10);
            //case 32: shoot zombie
        }
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //public void addKeyGame(KeyAdapter listener){
    //    this.addKeyListener(listener);
    //}

}
