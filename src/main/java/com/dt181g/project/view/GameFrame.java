package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class GameFrame extends JFrame {

    GamePanel gamePanel;
    StatsPanel statsPanel;
    ButtonPanel btnPanel;
    //AnimationPanel animationPanel;

    public GameFrame(String path, int score){

        gamePanel = new GamePanel(path);
        statsPanel = new StatsPanel(score, 5, 5);
        btnPanel = new ButtonPanel();
        //animationPanel = new AnimationPanel(score);

        this.setTitle("Game");
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.add(btnPanel, BorderLayout.WEST);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(statsPanel, BorderLayout.SOUTH);
        //this.add(animationPanel, BorderLayout.EAST);
        //this.add(animationPanel, BorderLayout.EAST);
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

    //public AnimationPanel getAnimationPanel(){ return animationPanel; }


    public void displayKilled(String message){
        JOptionPane.showMessageDialog(this, displayMsg(message));
    }

    public void displayTimesUpMsg(String msg){
        JOptionPane.showMessageDialog(this,
                displayMsg(msg));
    }

    public Integer displayWinMsg(String message){
        String[] options = new String[] {"Next level", "Cancel"};
        return JOptionPane.showOptionDialog(this, displayMsg(message), "Winning!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    private JPanel displayMsg(String message) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.setBackground(Color.decode("#0f4c5c"));
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        label.setForeground(Color.WHITE);
        panel.add(label);

        UIManager.put("OptionPane.background",Color.decode("#0f4c5c"));
        UIManager.put("Panel.background",Color.decode("#0f4c5c"));
        return panel;
    }

    public void addKeyGame(KeyAdapter listener){
        this.addKeyListener(listener);
    }
}
