package com.dt181g.project.controller;

import com.dt181g.project.View;
import com.dt181g.project.model.Model;
import com.dt181g.project.model.characters.Character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tells model to create the characters
 */

public class Controller {

    private static final View theView = new View();
    private static final Model theModel = new Model();
    static Character newCharacter;

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
                theView.setCharacter(newCharacter.getName(), newCharacter.getIMG_PATH());
                theModel.getNewCharacter().killZombies();
            }
        }
    }

    static class AddStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

