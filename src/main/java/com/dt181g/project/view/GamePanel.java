package com.dt181g.project.view;

import com.dt181g.project.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * JPanel representing the game.
 * @author Ebba Nimér
 */
public class GamePanel extends JPanel {

    private final int WIDTH = Constants.WIDTH;
    private final int HEIGHT = Constants.HEIGHT;
    private final int CHAR_WIDTH = Constants.ICON_WIDTH;
    static final int CHAR_HEIGHT = Constants.ICON_HEIGHT;

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    Deque<JLabel> foodLabels = new LinkedList<>();
    Deque<JLabel> zombieLabels = new LinkedList<>();

    /**
     * Initialize GamePanel by setting values.
     * @param path image path.
     */
    public GamePanel(String path){
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(addCharacterLbl(path));
    }

    /**
     * Add JLabel containing character image.
     * @param path image path
     * @return JLabel with image.
     */
    private JLabel addCharacterLbl(String path){
        charImage = new ImageIcon (ClassLoader.getSystemResource(path)); // create image-icon from path
        Image otherImg = charImage.getImage();    // get image to change size
        Image newImg = otherImg.getScaledInstance(CHAR_WIDTH, CHAR_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        imgLbl.setIcon(newIcon);
        imgLbl.setBounds(0, 0, CHAR_WIDTH, CHAR_HEIGHT);   // place character in top left corner.
        return imgLbl;
    }

    /**
     * Add food-labels by passing values to dedicated method.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param path image path
     */
    public void addFoods(int x, int y, String path){
        addItems(x, y, path, foodLabels);
    }

    /**
     * Add zombie-labels by passing values to dedicated method.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param path image path
     */
    public void addZombies(int x, int y, String path){
        addItems(x, y, path, zombieLabels);
    }

    /**
     * Add JLabel with type of item.
     * @param startX x-coordinate
     * @param startY y-coordinate
     * @param path image-path
     * @param labels type of labels
     */
    private void addItems(Integer startX, Integer startY, String path, Deque<JLabel> labels) {
        JLabel label = new JLabel();
        label.setBounds(startX, startY, 60, 60);
        Image itemImg = new ImageIcon (ClassLoader.getSystemResource(path)).getImage();
        Image modifiedImg = itemImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(modifiedImg);
        label.setIcon(newImg);
        labels.add(label);
        this.add(label);
    }

    /**
     * If character hit food, remove food from panel.
     * @param jLabel food label.
     */
    public void foodTaken(JLabel jLabel){
        this.remove(jLabel);
        this.revalidate();
        this.repaint();
        foodLabels.remove(jLabel);
    }

    /**
     * Remove zombies from list and from panel.
     */
    public void removeZombies(){
        for (JLabel zombieLabel : zombieLabels){
            this.remove(zombieLabel);
        }
        zombieLabels.clear();
        this.revalidate();
        this.repaint();
    }

    /**
     * Remove food from list and from panel.
     */
    public void removeFoods(){
        for (JLabel foodLabel : foodLabels){
            this.remove(foodLabel);
        }
        foodLabels.clear();
        this.revalidate();
        this.repaint();
    }

    /**
     * Provide image-label to calling client.
     * @return JLabel containing image.
     */
    public JLabel getImgLbl(){
        return imgLbl;
    }

    /**
     * Provide food-labels to calling client.
     * @return list containing food-labels.
     */
    public Deque<JLabel> getFoodLabels(){
        return foodLabels;
    }

    /**
     * Provide zombie-labels to calling client.
     * @return list containing zombie-labels.
     */
    public Deque<JLabel> getZombieLabels(){ return zombieLabels; }

}

