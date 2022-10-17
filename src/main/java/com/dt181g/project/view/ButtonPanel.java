package com.dt181g.project.view;
import com.dt181g.project.controller.GameController;
import com.dt181g.project.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.dt181g.project.view.StartView.getjPanel;

public class ButtonPanel extends JPanel {

    JButton instructionBtn = new JButton("Instructions");
    JButton exitBtn = new JButton("Exit");
    JButton endBtn = new JButton("End game");
    SpringLayout layout;

    public ButtonPanel(){
        this.setFocusable(false);
        layout = new SpringLayout();
        this.setPreferredSize(new Dimension(170, Constants.HEIGHT));
        this.setBackground(Color.decode("#0f4c5c"));
        this.setLayout(layout);

        addButtons();

        this.add(instructionBtn);
        this.add(endBtn);
        this.add(exitBtn);
    }

    public void addButtons(){

        layout.putConstraint(SpringLayout.WEST, instructionBtn,
                10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, instructionBtn,
                120, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, endBtn,
                10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, endBtn,
                190, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, exitBtn,
                10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, exitBtn,
                260, SpringLayout.NORTH, this);


        instructionBtn.setPreferredSize(new Dimension(150,40));
        instructionBtn.setBackground(Color.decode("#cbf3f0"));
        endBtn.setPreferredSize(new Dimension(150,40));
        endBtn.setBackground(Color.decode("#cbf3f0"));
        exitBtn.setPreferredSize(new Dimension(150,40));
        exitBtn.setBackground(Color.decode("#cbf3f0"));
    }

    public void addExitGame(ActionListener listener){ exitBtn.addActionListener(listener);}

    public void addInstructionButton(ActionListener listener){
        instructionBtn.addActionListener(listener);
    }

    public void addEndGame(ActionListener listener) {
        endBtn.addActionListener(listener);
    }

    public void displayInstructionMessage(String msg){
        JOptionPane.showMessageDialog(this, displayMsg(msg));
        this.setFocusable(false);
    }

    private JPanel displayMsg(String message) {
        return getjPanel(message);
    }

}
