package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

import static com.dt181g.project.view.StartView.getjPanel;

/**
 * Primary game-frame which contains the panels used for game-simulation.
 * @author Ebba Nimér
 */
public class GameFrame extends JFrame {

    GamePanel gamePanel;
    StatsPanel statsPanel;
    ButtonPanel btnPanel;

    /**
     * Initialize game-frame with initializing panels with start-values.
     * @param path image path
     * @param score amount of score
     */
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

    /**
     * Provide game-panel to calling client.
     * @return game-panel.
     */
    public GamePanel getGamePanel(){
        return gamePanel;
    }

    /**
     * Provide stats-panel to calling client.
     * @return stats-panel.
     */
    public StatsPanel getStatsPanel(){
        return statsPanel;
    }

    /**
     * Provide button-panel to calling client.
     * @return button-panel.
     */
    public ButtonPanel getBtnPanel(){
        return btnPanel;
    }

    /**
     * When character is killed, display passed message.
     * @param message message to be displayed
     */
    public void displayKilled(String message){
        JOptionPane.showMessageDialog(this,
                displayMsg(message));
    }

    /**
     * When time is up, display passed message.
     * @param msg message to be displayed
     */
    public void displayTimesUpMsg(String msg){
        JOptionPane.showMessageDialog(this,
                displayMsg(msg));
    }

    /**
     * When character wins, display passed message.
     * @param message message to be displayed.
     */
    public void displayWinTwoMsg(String message){
        JOptionPane.showMessageDialog(this,
                displayMsg(message));
    }

    /**
     * When character wins, display message and give option to continue to next level.
     * @param message message to display.
     * @return users choice.
     */
    public Integer displayWinMsg(String message){
        String[] options = new String[] {"Next level", "Cancel"};
        return JOptionPane.showOptionDialog(this,
                displayMsg(message), "Winning!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
    }

    /**
     * Design for JOptionPane.
     * @param message message to be displayed
     * @return JPanel containing layout.
     */
    private JPanel displayMsg(String message) {
        return getjPanel(message);
    }

    /**
     * Add key-listener to game.
     * @param listener key listener.
     */
    public void addKeyGame(KeyAdapter listener){
        this.addKeyListener(listener);
    }
}
