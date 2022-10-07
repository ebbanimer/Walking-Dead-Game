package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.stream.Stream;

public class GameWindow extends JFrame {

    private final int MAX_FOOD = 5;
    private final int MAX_ZOMBIES = 5;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;

    static final int FOOD_WIDTH = 60;
    static final int ZOMBIE_WIDTH = 60;
    static final int CHAR_HEIGHT = 60;

    static final int FOOD_HEIGHT = 60;
    static final int ZOMBIE_HEIGHT = 60;
    static final int CHAR_WIDTH = 60;

    int currZombies = 5;
    int currFood = 5;
    int score;

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    JLabel timerLbl = new JLabel();
    JLabel scoreLbl = new JLabel("Score: ");
    JLabel scoreCount = new JLabel();
    JLabel foodLbl = new JLabel("Food left: ");
    JLabel foodCount = new JLabel();
    JButton startGameBtn = new JButton("Start game");
    JButton endGameBtn = new JButton("End game");
    JPanel gamePanel = new JPanel();

    ArrayList<JLabel> foodLabels = new ArrayList<>();
    ArrayList<JLabel> zombieLabels = new ArrayList<>();
    boolean collision = false;

    Timer timer;

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

        }
    };
    
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

        timer = new Timer();
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
        foodCount.setText(String.valueOf(currFood));

        statsPanel.add(timerLbl);
        statsPanel.add(scoreLbl);
        statsPanel.add(scoreCount);
        statsPanel.add(foodLbl);
        statsPanel.add(foodCount);
        return statsPanel;
    }

    public JPanel addGamePanel(){
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.setBackground(Color.BLACK);
        imgLbl.setBounds(0, 0, CHAR_WIDTH, CHAR_HEIGHT);

        gamePanel.add(imgLbl);
        return gamePanel;
    }

    public void addFoods(int x, int y, String path){
        addItems(x, y, path, FOOD_WIDTH, FOOD_HEIGHT, foodLabels);
    }

    public void addZombies(int x, int y, String path){
        addItems(x, y, path, ZOMBIE_WIDTH, ZOMBIE_HEIGHT, zombieLabels);
    }

    private void addItems(int x, int y, String path, int width, int height, ArrayList<JLabel> labels) {
        for (int i = 0; i < 5; i++){
            JLabel label = new JLabel();
            label.setBounds(x, y, 60, 60);
            Image itemImg = new ImageIcon(path).getImage();
            Image modifiedImg = itemImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon newImg = new ImageIcon(modifiedImg);
            label.setIcon(newImg);
            labels.add(label);
            gamePanel.add(label);
        }
    }

    public void foodTaken(JLabel jLabel){
        gamePanel.remove(jLabel);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public void zombieCollision(){
        collision = true;
        stopGame();
    }

    public void zombieKilled(JLabel jLabel){
        gamePanel.remove(jLabel);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public void updateStats(){
        score++;
        currFood--;
        scoreCount.setText(String.valueOf(score));
        foodCount.setText(String.valueOf(currFood));
    }

    public void stopGame(){
        if (collision){
            JOptionPane.showMessageDialog(this, "Game over!");
        }
    }

    public void addCharacter(String path, int score){
        this.score = score;
        charImage = new ImageIcon(path);
        Image charOtherImg = charImage.getImage();
        Image modCharImg = charOtherImg.getScaledInstance(CHAR_WIDTH, CHAR_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modCharImg);
        imgLbl.setIcon(newImg);
    }

    public JLabel getImgLabel(){
        return imgLbl;
    }

    public ArrayList<JLabel> getFoodLabels(){
        return foodLabels;
    }

    public ArrayList<JLabel> getZombieLabels(){
        return zombieLabels;
    }

    public void addKeyGame(KeyAdapter listener){
        this.addKeyListener(listener);
    }

    public void addStartGame(ActionListener listener){
        startGameBtn.addActionListener(listener);
    }

}
