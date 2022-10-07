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
                gameWindow = new GameWindow();
                createFoods();
                createZombies();
                gameWindow.addStartGame(new AddStartGame());
                gameWindow.addKeyGame(new AddKeyGame());

                Character character = theModel.getNewCharacter();
                gameWindow.addCharacter(character.getIMG_PATH(), character.getScore());
            } else {
                theView.displayError("Please choose a character");
            }
        }

        private void createFoods(){
            theModel.createItem("Food", 5);
            foods = theModel.getFoods();
            for (Food food : foods) {
                gameWindow.addFoods(food.getStartX(),
                        food.getStartY(), food.getPath());
            }
        }

        private void createZombies(){
            theModel.createItem("Zombie", 5);
            zombies = theModel.getZombies();
            for (Zombie zombie : zombies) {
                gameWindow.addZombies(zombie.getStartX(),
                        zombie.getStartY(), zombie.getPath());
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
            moveCharacter(imgLbl, e.getKeyCode());
            detectCollision(imgLbl);

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

        private void moveCharacter(JLabel imgLbl, int keyCode){
            switch (keyCode) {
                case 37 -> imgLbl.setLocation(imgLbl.getX() - 10, imgLbl.getY());
                case 38 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() - 10);
                case 39 -> imgLbl.setLocation(imgLbl.getX() + 10, imgLbl.getY());
                case 40 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() + 10);
                //case 32: kill zombie
            }
        }

        private void detectCollision(JLabel imgLbl){
            ArrayList<JLabel> foodLabels = gameWindow.getFoodLabels();

            for (JLabel jLabel : foodLabels){
                if (imgLbl.getBounds().intersects(jLabel.getBounds())){
                    System.out.println("For loop: Collision detected!");
                    foodLabels.remove(jLabel);
                    gameWindow.foodTaken(jLabel);
                    break;
                } else {
                    System.out.println("No collision");
                }
            }
        }
    }
}

