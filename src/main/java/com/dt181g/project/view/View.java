package com.dt181g.project.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    JLabel characterLbl = new JLabel("Pick a character");
    String[] characterList = {"Choose character...", "Rick", "Michonne", "Daryl", "Eugene"};
    JComboBox<String> characterCmb = new JComboBox<>(characterList);
    JButton startBtn = new JButton("Start game");
    JButton instructionBtn = new JButton("Instructions");
    JTextArea instructionText;
    JLabel nameLabel = new JLabel();
    JLabel weaponLabel = new JLabel();
    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    GridBagConstraints c = new GridBagConstraints();

    public View(){
        this.setTitle("The walking dead");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        this.add(addStartPanel(), BorderLayout.EAST);
        this.add(addImgPanel(), BorderLayout.WEST);
        this.add(addPickPanel(), BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    public JPanel addPickPanel(){
        JPanel pickPanel = new JPanel();
        pickPanel.setLayout(new GridBagLayout());
        pickPanel.setBackground(Color.decode("#0f4c5c"));
        pickPanel.setPreferredSize(new Dimension(600, 150));

        characterLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        characterLbl.setForeground(Color.WHITE);

        characterCmb.setPreferredSize(new Dimension(250, 40));
        characterCmb.setBackground(Color.decode("#cbf3f0"));

        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 1;
        pickPanel.add(characterLbl,c);
        c.gridx = 0;
        c.gridy = 2;
        pickPanel.add(characterCmb,c);
        return pickPanel;
    }

    public JPanel addStartPanel(){

        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridBagLayout());
        startPanel.setPreferredSize(new Dimension(300, 400));
        startPanel.setBackground(Color.decode("#0f4c5c"));
        startBtn.setPreferredSize(new Dimension(150,40));
        startBtn.setBackground(Color.decode("#cbf3f0"));
        instructionBtn.setPreferredSize(new Dimension(150,40));
        instructionBtn.setBackground(Color.decode("#cbf3f0"));

        addFontStyle(startPanel, nameLabel, nameLabel);
        c.gridy = 3;
        startPanel.add(startBtn,c);

        c.gridy = 4;
        startPanel.add(instructionBtn,c);

        return startPanel;
    }

    public JPanel addImgPanel(){
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new GridBagLayout());
        imgPanel.setPreferredSize(new Dimension(300, 400));
        imgPanel.setBackground(Color.decode("#277da1"));

        addFontStyle(imgPanel, weaponLabel, imgLbl);
        c.gridy = 2;
        imgPanel.add(weaponLabel,c);
        return imgPanel;
    }

    private void addFontStyle(JPanel imgPanel, JLabel weaponLabel, JLabel imgLbl) {
        weaponLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        weaponLabel.setForeground(Color.WHITE);

        c.insets = new Insets(20,10,20,10);
        c.gridx = 0;
        c.gridy = 1;
        imgPanel.add(imgLbl,c);
        c.gridx = 0;
    }

    public String getCharacter(){
        return (String) characterCmb.getSelectedItem();
    }

    public void setCharacter(String character, String weapon, String imgPath){
        charImage = new ImageIcon(imgPath);
        imgLbl.setIcon(charImage);
        nameLabel.setText("You have chosen character " + character);
        weaponLabel.setText("Weapon: " + weapon);
    }

    public void showInstructions(String message){
        JOptionPane.showMessageDialog(this, displayMsg(message), "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayError(String msg){
        JOptionPane.showMessageDialog(this, displayMsg(msg), "Error", JOptionPane.INFORMATION_MESSAGE);
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


    public void addInstructionsListener(ActionListener listener){ instructionBtn.addActionListener(listener);}

    public void addComboListener(ActionListener listener){
        characterCmb.addActionListener(listener);
    }

    public void addStartListener(ActionListener listener){
        startBtn.addActionListener(listener);
    }


}

