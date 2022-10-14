package com.dt181g.project.view;

import com.dt181g.project.Constants;

import javax.swing.*;
import java.awt.*;
public class StatsPanel extends JPanel {

    JLabel timerLbl = new JLabel("Time left: ");
    JLabel timerCount = new JLabel();
    JLabel scoreLbl = new JLabel("Score: ");
    JLabel scoreCount = new JLabel();
    JLabel foodLbl = new JLabel("Food left: ");
    JLabel foodCount = new JLabel();
    JLabel zombieLbl = new JLabel("Zombies left: ");
    JLabel zombieCount = new JLabel();

    int currFood;
    int score;
    int currZombies;

    public StatsPanel(int score, int currFood, int currZombies){
        this.currZombies = currZombies;
        this.currFood = currFood;
        this.score = score;

        this.setPreferredSize(new Dimension(Constants.WIDTH, 50));
        this.setBackground(Color.decode("#0f4c5c"));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        addLabels();

        this.add(timerLbl);
        this.add(timerCount);
        this.add(scoreLbl);
        this.add(scoreCount);
        this.add(foodLbl);
        this.add(foodCount);
        this.add(zombieLbl);
        this.add(zombieCount);
    }

    private void addLabels(){
        scoreCount.setText(String.valueOf(score));
        foodCount.setText(String.valueOf(currFood));
        zombieCount.setText(String.valueOf(currZombies));

        timerCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        timerCount.setForeground(Color.decode("#e3a587"));
        scoreCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        scoreCount.setForeground(Color.decode("#de3a587"));
        foodCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        foodCount.setForeground(Color.decode("#e3a587"));
        zombieCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        zombieCount.setForeground(Color.decode("#e3a587"));

        timerLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        timerLbl.setForeground(Color.WHITE);
        foodLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        foodLbl.setForeground(Color.WHITE);
        scoreLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        scoreLbl.setForeground(Color.WHITE);

        zombieLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        zombieLbl.setForeground(Color.WHITE);
    }

    public void updateScore(int score){
        this.score = score;
        scoreCount.setText(String.valueOf(score));
    }

    public void updateFoodCount(int count){
        this.currFood = count;
        foodCount.setText(String.valueOf(currFood));
    }

    public void updateZombieCount(int count){
        this.currZombies = count;
        zombieCount.setText(String.valueOf(currZombies));
    }

    public JLabel getTimerCount(){
        return timerCount;
    }

}
