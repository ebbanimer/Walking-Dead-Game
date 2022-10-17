package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

import static com.dt181g.project.view.StartView.getjPanel;

public class GameFrame extends JFrame {

    GamePanel gamePanel;
    StatsPanel statsPanel;
    ButtonPanel btnPanel;

    public GameFrame(String path, int score){

        gamePanel = new GamePanel(path);
        statsPanel = new StatsPanel(score, 5, 5);
        btnPanel = new ButtonPanel();

        this.setTitle("Game");
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.add(btnPanel, BorderLayout.WEST);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(statsPanel, BorderLayout.SOUTH);
        this.pack();
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public StatsPanel getStatsPanel(){
        return statsPanel;
    }

    public ButtonPanel getBtnPanel(){
        return btnPanel;
    }
    
    public void displayKilled(String message){
        JOptionPane.showMessageDialog(this, displayMsg(message));
    }

    public void displayTimesUpMsg(String msg){
        JOptionPane.showMessageDialog(this,
                displayMsg(msg));
    }

    public void displayWinTwoMsg(String message){
        JOptionPane.showMessageDialog(this, displayMsg(message));
    }

    public Integer displayWinMsg(String message){
        String[] options = new String[] {"Next level", "Cancel"};
        return JOptionPane.showOptionDialog(this, displayMsg(message), "Winning!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    private JPanel displayMsg(String message) {
        return getjPanel(message);
    }

    public void addKeyGame(KeyAdapter listener){
        this.addKeyListener(listener);
    }
}
