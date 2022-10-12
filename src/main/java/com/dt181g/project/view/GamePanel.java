package com.dt181g.project.view;

import com.dt181g.project.model.factories.Food;
import com.dt181g.project.model.factories.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

public class GamePanel extends JPanel {

    private final int WIDTH = 700;
    private final int HEIGHT = 600;
    static final int CHAR_WIDTH = 60;
    static final int CHAR_HEIGHT = 60;

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    Deque<JLabel> foodLabels = new LinkedList<>();
    Deque<JLabel> zombieLabels = new LinkedList<>();


    public GamePanel(String path){
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(addCharacterLbl(path));
    }

    private JLabel addCharacterLbl(String path){
        charImage = new ImageIcon(path);
        Image otherImg = charImage.getImage();
        Image newImg = otherImg.getScaledInstance(CHAR_WIDTH, CHAR_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        imgLbl.setIcon(newIcon);
        imgLbl.setBounds(0, 0, CHAR_WIDTH, CHAR_HEIGHT);
        return imgLbl;
    }

    public void addFoods(Food food){
        addItems(food.getStartX(), food.getStartY(), food.getPath(), food.getID(), foodLabels);
    }

    public void addZombies(Zombie zombie){
        addItems(zombie.getStartX(), zombie.getStartY(), zombie.getPath(), zombie.getID(), zombieLabels);
    }

    private void addItems(Integer startX, Integer startY, String path, Integer id, Deque<JLabel> labels) {
        JLabel label = new JLabel();
        label.setBounds(startX, startY, 60, 60);
        Image itemImg = new ImageIcon(path).getImage();
        Image modifiedImg = itemImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modifiedImg);
        label.setIcon(newImg);
        labels.add(label);
        this.add(label);
    }

    public void foodTaken(JLabel jLabel){
        this.remove(jLabel);
        this.revalidate();
        this.repaint();
        foodLabels.remove(jLabel);
    }

    public void removeZombies(){
        for (int i = 0; i < zombieLabels.size(); i++){
            this.remove(zombieLabels.removeFirst());
            zombieLabels.removeFirst();
        }
        this.revalidate();
        this.repaint();
    }

    public void removeFoods(){
        for (int i = 0; i < foodLabels.size(); i++){
            this.remove(foodLabels.removeFirst());
            foodLabels.removeFirst();
        }
        this.revalidate();
        this.repaint();
    }

    public JLabel getImgLbl(){
        return imgLbl;
    }

    public Deque<JLabel> getFoodLabels(){
        return foodLabels;
    }

    public Deque<JLabel> getZombieLabels(){ return zombieLabels; }

}

