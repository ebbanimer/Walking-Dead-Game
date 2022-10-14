package com.dt181g.project.view;

import com.dt181g.project.model.Constants;
import com.dt181g.project.model.factories.Food;
import com.dt181g.project.model.factories.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

public class GamePanel extends JPanel {

    private final int WIDTH = Constants.WIDTH;
    private final int HEIGHT = Constants.HEIGHT;
    private final int CHAR_WIDTH = Constants.ICON_WIDTH;
    static final int CHAR_HEIGHT = Constants.ICON_HEIGHT;

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
        addItems(food.getStartX(), food.getStartY(), food.getPath(), foodLabels);
    }

    public void addZombies(Zombie zombie){
        addItems(zombie.getStartX(), zombie.getStartY(), zombie.getPath(), zombieLabels);
    }

    private void addItems(Integer startX, Integer startY, String path, Deque<JLabel> labels) {
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
        for (JLabel zombieLabel : zombieLabels){
            this.remove(zombieLabel);
        }
        zombieLabels.clear();
        this.revalidate();
        this.repaint();
    }

    public void removeFoods(){
        for (JLabel foodLabel : foodLabels){
            this.remove(foodLabel);
        }
        foodLabels.clear();
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

