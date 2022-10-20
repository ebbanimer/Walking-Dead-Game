package com.dt181g.project.view;

import com.dt181g.project.model.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel representing the stats in the game.
 * @author Ebba Nimér
 */
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

    /**
     * Initialize panel with provided values, using FlowLayout-manager.
     * @param score amount of score
     * @param currFood current food amount
     * @param currZombies current zombie amount
     */
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

    /**
     * Add labels to be displayed for stats.
     */
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

    /**
     * Update score.
     * @param score new amount
     */
    public void updateScore(int score){
        this.score = score;
        scoreCount.setText(String.valueOf(score));
    }

    /**
     * Update amount of food.
     * @param count new amount
     */
    public void updateFoodCount(int count){
        this.currFood = count;
        foodCount.setText(String.valueOf(currFood));
    }

    /**
     * Update amount of zombies.
     * @param count new amount
     */
    public void updateZombieCount(int count){
        this.currZombies = count;
        zombieCount.setText(String.valueOf(currZombies));
    }

    /**
     * Provide JLabel containing timer to calling client.
     * @return timer-label
     */
    public JLabel getTimerCount(){
        return timerCount;
    }

}
