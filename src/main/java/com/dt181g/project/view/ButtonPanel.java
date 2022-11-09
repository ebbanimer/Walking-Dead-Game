package com.dt181g.project.view;
import com.dt181g.project.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * JPanel for buttons in game-frame.
 * @author Ebba Nimér
 */
public class ButtonPanel extends JPanel {

    JButton exitBtn = new JButton("Exit");
    JButton endBtn = new JButton("End game");
    SpringLayout layout;

    /**
     * Initialize button-panel by adding components and layout manager.
     */
    public ButtonPanel(){
        this.setFocusable(false);
        layout = new SpringLayout();
        this.setPreferredSize(new Dimension(170, Constants.HEIGHT));
        this.setBackground(Color.decode("#0f4c5c"));
        this.setLayout(layout);

        addButtons();

        this.add(endBtn);
        this.add(exitBtn);
    }

    /**
     * Design and add buttons to JPanel, using SpringLayout.
     */
    public void addButtons(){

        layout.putConstraint(SpringLayout.WEST, endBtn,
                10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, endBtn,
                190, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, exitBtn,
                10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, exitBtn,
                260, SpringLayout.NORTH, this);


        endBtn.setPreferredSize(new Dimension(150,40));
        endBtn.setBackground(Color.decode("#cbf3f0"));
        exitBtn.setPreferredSize(new Dimension(150,40));
        exitBtn.setBackground(Color.decode("#cbf3f0"));
    }

    /**
     * Adding listener when user presses exit game-button.
     * @param listener action-listener
     */
    public void addExitGame(ActionListener listener){ exitBtn.addActionListener(listener);}

    /**
     * Adding listener when user presses end game-button.
     * @param listener action listener
     */
    public void addEndGame(ActionListener listener) {
        endBtn.addActionListener(listener);
    }

}
