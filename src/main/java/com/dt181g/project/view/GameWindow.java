package com.dt181g.project.view;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class GameWindow extends JFrame implements KeyListener, ActionListener{

    private final int MAX_FOOD = 5;
    private final int MAX_ZOMBIES = 5;
    private final int WIDTH = 700;
    private final int HEIGHT = 600;
    int x = 60;
    int y = 60;
    int currZombies = 5;
    int currFood = 5;

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    JLabel timerLbl = new JLabel();
    JButton endGameBtn = new JButton("End game");
    ImageIcon foodImage = new ImageIcon("src\\main\\java\\com\\dt181g\\project\\images\\food.png");
    ImageIcon zombieImg = new ImageIcon("src\\main\\java\\com\\dt181g\\project\\images\\zombie.png");
    JPanel gamePanel = new JPanel();
    Random rand = new Random();
    Timer timer;
    int[][] coordinates;
    Stream<int[]> coord = Stream.empty();
    
    int xVelocity = 1;
    int yVelocity = 1;

    public GameWindow(){
        timer = new Timer(1000, this);
        timer.start();
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
        btnPanel.setPreferredSize(new Dimension(150, HEIGHT));
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
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.setBackground(Color.BLACK);
        addFood();
        addZombie();

        imgLbl.setBounds(0, 0, 60, 60);

        gamePanel.add(imgLbl);
        return gamePanel;
    }

    public void addFood(){
        for (int i = 0; i < MAX_FOOD; i++){
            JLabel label = new JLabel();
            int randX = rand.nextInt((WIDTH - x) + 1) + 1;
            int randY = rand.nextInt((HEIGHT - y) + 1) + 1;

            label.setBounds(randX, randY, 60, 60);
            Image charOtherImg = foodImage.getImage();
            Image modFoodImg = charOtherImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon newImg = new ImageIcon(modFoodImg);
            label.setIcon(newImg);
            gamePanel.add(label);
        }
    }

    public void addZombie(){
        for (int i = 0; i < MAX_ZOMBIES; i++){
            JLabel label = new JLabel();
            int randX = rand.nextInt((WIDTH - x) + 1) + 1;
            int randY = rand.nextInt((HEIGHT - y) + 1) + 1;
            label.setBounds(randX, randY, 60, 60);
            Image charOtherImg = zombieImg.getImage();
            Image modZombieImg = charOtherImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon newImg = new ImageIcon(modZombieImg);
            label.setIcon(newImg);
            //moveZombie(label);
            gamePanel.add(label);
        }
    }

    /*public void moveZombie(JLabel lblImg){
        x = lblImg.getX();
        y = lblImg.getY();
        coord.toList().add(new int[]{x, y});
        System.out.println(coord);
    }*/


    public void setCharacter(String path){
        charImage = new ImageIcon(path);
        Image charOtherImg = charImage.getImage();
        Image modCharImg = charOtherImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modCharImg);
        imgLbl.setIcon(newImg);
    }
    
    public JLabel getImgLabel(){
        return imgLbl;
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
            //case 32: kill zombie
        }
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //public void addKeyGame(KeyAdapter listener){
    //    this.addKeyListener(listener);
    //}

}
