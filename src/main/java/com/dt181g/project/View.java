package com.dt181g.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    JLabel characterLbl = new JLabel("Pick a character");
    String[] characterList = {"Rick", "Michonne", "Daryl", "Eugene"};
    JComboBox<String> characterCmb = new JComboBox<>(characterList);
    JButton startBtn = new JButton("Start game");
    JLabel nameLabel = new JLabel();
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
        pickPanel.setBackground(Color.BLUE);
        pickPanel.setPreferredSize(new Dimension(600, 150));

        characterLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        characterLbl.setForeground(Color.WHITE);

        characterCmb.setMaximumSize(new Dimension(200, 30));

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
        startPanel.setBackground(Color.GRAY);

        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        nameLabel.setForeground(Color.WHITE);

        c.insets = new Insets(20,10,20,10);
        c.gridx = 0;
        c.gridy = 1;
        startPanel.add(nameLabel,c);
        c.gridx = 0;
        c.gridy = 2;
        startPanel.add(startBtn,c);

        return startPanel;
    }

    public JPanel addImgPanel(){
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new GridBagLayout());
        imgPanel.setPreferredSize(new Dimension(300, 400));
        imgPanel.setBackground(Color.BLACK);
        imgPanel.add(imgLbl);
        return imgPanel;
    }

    public String getCharacter(){
        return (String) characterCmb.getSelectedItem();
    }

    public void setCharacter(String character, String imgPath){
        charImage = new ImageIcon(imgPath);
        //Image img = charImage.getImage();
        //Image modImg = img.getScaledInstance(150,300, Image.SCALE_SMOOTH);
        //charImage.setImage(modImg);
        imgLbl.setIcon(charImage);
        nameLabel.setText("You have chosen character " + character);
    }

    public void addComboListener(ActionListener listener){
        characterCmb.addActionListener(listener);
    }

    public void addStartListener(ActionListener listener){
        startBtn.addActionListener(listener);
    }
}
