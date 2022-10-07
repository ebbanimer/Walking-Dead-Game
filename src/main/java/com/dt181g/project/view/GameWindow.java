package com.dt181g.project.view;

import com.dt181g.project.Observables;
import com.dt181g.project.Observers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class GameWindow extends JFrame implements Observers {

    private final int MAX_FOOD = 5;
    private final int MAX_ZOMBIES = 5;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;

    int currZombies = 5;
    int currFood = 5;
    int score;

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    JLabel timerLbl = new JLabel();
    JLabel scoreLbl = new JLabel("Score: ");
    JLabel scoreCount = new JLabel();
    JButton startGameBtn = new JButton("Start game");
    JButton endGameBtn = new JButton("End game");
    JPanel gamePanel = new JPanel();

    static final int FOOD_DIAMETER = 60;
    static final int ZOMBIE_DIAMETER = 60;
    static final int CHAR_DIAMETER = 60;

    static final int FOOD_HEIGHT = 60;
    static final int ZOMBIE_HEIGHT = 60;
    static final int CHAR_HEIGHT = 60;

    ArrayList<JLabel> foodLabels = new ArrayList<>();

    Thread gameThread;

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
        this.add(addBtnPanel(), BorderLayout.WEST);
        this.add(addGamePanel(), BorderLayout.EAST);
        this.add(addStatsPanel(), BorderLayout.SOUTH);
        this.pack();
        //gameThread = new Thread(new GameRunnable());
        //gameThread.start();
    }

    private JPanel addBtnPanel(){
        JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(125, HEIGHT));
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        btnPanel.add(startGameBtn);
        btnPanel.add(endGameBtn);
        return btnPanel;
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


    public void testAdd(int x, int y, String path){
        JLabel label = new JLabel();
        label.setBounds(x, y, 60, 60);

        Image img = new ImageIcon(path).getImage();
        Image img2 = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(img2);
        label.setIcon(newImg);
        foodLabels.add(label);
    }

    public ArrayList<JLabel> getFoodLabels(){
        return foodLabels;
    }

    public void addFoods(int x, int y, String path){
        addItems(x, y, path);
    }

    public void addZombies(int x, int y, String path){
        addItems(x, y, path);
    }

    private void addItems(int x, int y, String path) {
        JLabel label = new JLabel();
        label.setBounds(x, y, 60, 60);

        Image itemImg = new ImageIcon(path).getImage();
        Image modifiedImg = itemImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modifiedImg);
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

    /*public void addZombie(){
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
    }*/

    public void foodTaken(JLabel jLabel){
        gamePanel.remove(jLabel);
        gamePanel.revalidate();
        gamePanel.repaint();
        score++;
        currFood--;
        scoreCount.setText(String.valueOf(score));
    }

    /*public void moveZombie(JLabel lblImg){
        x = lblImg.getX();
        y = lblImg.getY();
        coord.toList().add(new int[]{x, y});
        System.out.println(coord);
    }*/


    public void addCharacter(String path, int score){
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

    public void addStartGame(ActionListener listener){
        startGameBtn.addActionListener(listener);
    }

    @Override
    public void update(Observables observables) {

    }
}
