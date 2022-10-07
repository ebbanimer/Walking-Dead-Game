package com.dt181g.project.controller;

import com.dt181g.project.model.itemAbstractFactory.Food;
import com.dt181g.project.model.itemAbstractFactory.Zombie;
import com.dt181g.project.view.GameWindow;
import com.dt181g.project.view.View;
import com.dt181g.project.model.Model;
import com.dt181g.project.model.charFactoryMethod.Character;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Tells model to create the characters
 */

public class Controller {

    private static final View theView = new View();
    private static final Model theModel = new Model();
    static Character newCharacter;
    private static GameWindow gameWindow;
    static ArrayList<Food> foods;
    static ArrayList<Zombie> zombies;


    public Controller(){
        theView.addComboListener(new AddCharacterPickListener());
        theView.addStartListener(new AddStartListener());
    }

    static class AddCharacterPickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String characterName = theView.getCharacter();
            theModel.createCharacter(characterName);

            if (theModel.getNewCharacter() != null){
                newCharacter = theModel.getNewCharacter();
                theView.setCharacter(newCharacter.getName(), newCharacter.getWeapon(), newCharacter.getIMG_PATH());
                theModel.getNewCharacter().killZombies();
            }
        }
    }

    static class AddStartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newCharacter = theModel.getNewCharacter();
            if (newCharacter != null){
                theModel.createItem("Food", 5);
                theModel.createItem("Zombie", 5);
                gameWindow = new GameWindow();
                gameWindow.addStartGame(new AddStartGame());
                gameWindow.addKeyGame(new AddKeyGame());
                foods = theModel.getFoods();
                zombies = theModel.getZombies();

                for (Food food : foods) {
                    gameWindow.addFoods(food.getStartX(),
                            food.getStartY(), food.getPath());
                    gameWindow.testAdd(food.getStartX(),
                            food.getStartY(), food.getPath());
                }

                for (Zombie zombie : zombies) {
                    gameWindow.addZombies(zombie.getStartX(),
                            zombie.getStartY(), zombie.getPath());
                }

                //gameWindow.addKeyGame(new AddKeyAdapter());
                Character character = theModel.getNewCharacter();
                gameWindow.addCharacter(character.getIMG_PATH(), character.getScore());
            } else {
                theView.displayError("Please choose a character");
            }
        }
    }

    static class AddStartGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // move the zombies
            // eat apples
        }
    }

    static class AddKeyGame extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            JLabel imgLbl = gameWindow.getImgLabel();

            switch (e.getKeyCode()) {
                case 37 -> imgLbl.setLocation(imgLbl.getX() - 10, imgLbl.getY());
                case 38 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() - 10);
                case 39 -> imgLbl.setLocation(imgLbl.getX() + 10, imgLbl.getY());
                case 40 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() + 10);
                //case 32: kill zombie
            }

            for (JLabel jLabel : gameWindow.getFoodLabels()){
                System.out.println(jLabel.getBounds());
                if (imgLbl.getBounds().intersects(jLabel.getBounds())){
                    System.out.println("For loop: Collision detected!");
                    gameWindow.foodTaken(jLabel);
                    break;
                }
            }

            /*boolean collision = gameWindow.getFoodLabels().stream()
                    .anyMatch(jLabel -> jLabel.getBounds()
                            .intersects(imgLbl.getBounds()));

            if (collision){
                gameWindow.foodTaken();
                System.out.println("Streams: Collision detected!");
            }*/

            //boolean matchX = foods.stream().anyMatch(food -> food.getXInterval().equals(imgLbl.getX()));
            //boolean matchY = foods.stream().anyMatch(food -> food.getYInterval().equals(imgLbl.getY()));

            //if (matchX && matchY){
            //    gameWindow.foodTaken();
                //foods.remove(0);
            //}
        }
    }
}

