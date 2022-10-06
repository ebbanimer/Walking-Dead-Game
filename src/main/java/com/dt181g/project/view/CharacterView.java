package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;

public class CharacterView extends JLabel{

    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);

    public CharacterView(String imgPath){
        this.add(setImg(imgPath));
    }

    private JLabel setImg(String path){
        charImage = new ImageIcon(path);
        Image img = charImage.getImage();
        Image modImg = img.getScaledInstance(75,75, Image.SCALE_SMOOTH);
        charImage.setImage(modImg);
        imgLbl.setIcon(charImage);
        return imgLbl;
    }
}
