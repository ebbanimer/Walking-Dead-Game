package com.dt181g.project.controller;

import com.dt181g.project.view.GamePanel;
import com.dt181g.project.view.GameWindow;
import com.dt181g.project.view.View;
import com.dt181g.project.model.Model;
import com.dt181g.project.model.characters.Character;

import java.awt.event.*;

/**
 * Tells model to create the characters
 */

public class Controller {

    private static final View theView = new View();
    private static final Model theModel = new Model();
    static Character newCharacter;
    private static GameWindow gameWindow;

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
                //gameWindow.addKeyGame(new AddKeyAdapter());
                Character character = theModel.getNewCharacter();
                gameWindow.setCharacter(character.getIMG_PATH());
            } else {
                theView.displayError("Please choose a character");
            }
        }
    }

    /*static class AddKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            System.out.println("You pressed key " + e.getKeyCode());
        }
    }*/
}

