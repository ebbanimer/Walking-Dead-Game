package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;

public class ZombieView extends JPanel {

    ImageIcon zombieImg = new ImageIcon();
    JLabel imgLbl = new JLabel(zombieImg);

    public ZombieView(){
        this.add(setImg());
    }

    private JLabel setImg(){
        zombieImg = new ImageIcon("src\\main\\java\\com\\dt181g\\project\\images\\zombie.PNG");
        Image img = zombieImg.getImage();
        Image modImg = img.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        zombieImg.setImage(modImg);
        imgLbl.setIcon(zombieImg);
        return imgLbl;
    }
}
