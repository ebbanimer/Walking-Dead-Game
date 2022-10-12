package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
public class StatsPanel extends JPanel {

    JLabel timerLbl = new JLabel("Time left: ");
    JLabel timerCount = new JLabel();
    JLabel scoreLbl = new JLabel("Score: ");
    JLabel scoreCount = new JLabel();
    JLabel foodLbl = new JLabel("Food left: ");
    JLabel foodCount = new JLabel();

    int currFood = 5;
    int score;

    public StatsPanel(int score){

        this.setPreferredSize(new Dimension(700, 100));
        this.setBackground(Color.decode("#0f4c5c"));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        addLabels();
        updateScore(score);

        this.add(timerLbl);
        this.add(timerCount);
        this.add(scoreLbl);
        this.add(scoreCount);
        this.add(foodLbl);
        this.add(foodCount);
    }

    private void addLabels(){
        scoreCount.setText(String.valueOf(score));
        foodCount.setText(String.valueOf(currFood));


        timerCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        timerCount.setForeground(Color.decode("#e3a587"));
        scoreCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        scoreCount.setForeground(Color.decode("#de3a587"));
        foodCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        foodCount.setForeground(Color.decode("#e3a587"));


        timerLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        timerLbl.setForeground(Color.WHITE);
        foodLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        foodLbl.setForeground(Color.WHITE);
        scoreLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        scoreLbl.setForeground(Color.WHITE);

    }

    public void updateScore(int score){
        this.score = score;
        scoreCount.setText(String.valueOf(score));
    }

    public void updateFoodCount(int count){
        this.currFood = currFood - count;
        foodCount.setText(String.valueOf(currFood));
    }

    public JLabel getTimerCount(){
        return timerCount;
    }

    public JLabel getScoreCount(){
        return scoreCount;
    }

    public JLabel getFoodCount(){
        return foodCount;
    }
}
