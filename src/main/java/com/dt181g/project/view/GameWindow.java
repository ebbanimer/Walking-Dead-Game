package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.stream.Stream;

public class GameWindow extends JFrame implements ActionListener{

    private final int MAX_FOOD = 5;
    private final int MAX_ZOMBIES = 5;
    private final int WIDTH = 700;
    private final int HEIGHT = 600;
    int x = 60;
    int y = 60;
    int currZombies = 5;
    int currFood = 5;
    int score;

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    JLabel timerLbl = new JLabel();
    JLabel scoreLbl = new JLabel("Score: ");
    JLabel scoreCount = new JLabel();
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

        this.setTitle("Game");
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());
        timer = new Timer(1000, this);
        timer.start();
        this.add(addGamePanel(), BorderLayout.EAST);
        this.add(addStatsPanel(), BorderLayout.SOUTH);
        this.pack();

    }

    private JPanel addStatsPanel(){
        JPanel statsPanel = new JPanel();
        statsPanel.setPreferredSize(new Dimension(WIDTH, 100));
        statsPanel.setLayout(new FlowLayout());

        scoreCount.setText(String.valueOf(score));

        statsPanel.add(timerLbl);
        statsPanel.add(scoreLbl);
        statsPanel.add(scoreCount);
        return statsPanel;
    }

    public JPanel addGamePanel(){
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.setBackground(Color.BLACK);
        //addFood();
        //addZombie();

        imgLbl.setBounds(0, 0, 60, 60);

        gamePanel.add(imgLbl);
        return gamePanel;
    }

    public void addFoodTest(int x, int y, String path){
        JLabel label = new JLabel();
        label.setBounds(x, y, 60, 60);

        Image foodImage = new ImageIcon(path).getImage();
        Image modFoodImg = foodImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modFoodImg);
        label.setIcon(newImg);
        gamePanel.add(label);
    }

    public void addZombieTest(int x, int y, String path){
        JLabel label = new JLabel();
        label.setBounds(x, y, 60, 60);

        Image zombieImage = new ImageIcon(path).getImage();
        Image modZombieImg = zombieImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modZombieImg);
        label.setIcon(newImg);
        gamePanel.add(label);
    }



    /*public void addFood(){
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
    }*/

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

    public void checkFood(){
        score++;
        scoreCount.setText(String.valueOf(score));
    }

    /*public void moveZombie(JLabel lblImg){
        x = lblImg.getX();
        y = lblImg.getY();
        coord.toList().add(new int[]{x, y});
        System.out.println(coord);
    }*/


    public void setCharacter(String path, int score){
        this.score = score;
        charImage = new ImageIcon(path);
        Image charOtherImg = charImage.getImage();
        Image modCharImg = charOtherImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modCharImg);
        imgLbl.setIcon(newImg);
    }

    public JLabel getImgLabel(){
        return imgLbl;
    }

    public void addKeyGame(KeyAdapter listener){
        this.addKeyListener(listener);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
