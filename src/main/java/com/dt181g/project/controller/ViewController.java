package com.dt181g.project.controller;

import com.dt181g.project.model.characters.Character;
import com.dt181g.project.Observer;
import com.dt181g.project.view.View;
import com.dt181g.project.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController implements Observer {

    private static final View theView = new View();
    private static final Model theModel = new Model();
    static Character newCharacter;

    public ViewController(){
        theView.addComboListener(new AddCharacterPickListener());
        theView.addStartListener(new AddStartListener());
        theView.addInstructionsListener(new AddInstructionsListener());
        theModel.register(this);
    }

    class AddCharacterPickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String characterName = theView.getCharacter();

            try {
                theModel.createCharacter(characterName);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (newCharacter != null){
                theView.setCharacter(newCharacter.getName(), newCharacter.getWeapon(), newCharacter.getPath());
            }
        }
    }

    class AddStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (newCharacter != null){
                try {
                    new GameControllerBuilder().setNewCharacter(newCharacter).setModel(theModel).createGameController();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                theView.displayError("Please choose a character");
            }
        }
    }

    class AddInstructionsListener implements ActionListener{

        String message = "<html>Collect as much food as you can<br> without getting caught by the zombies.<br> " +
                "You have 30 seconds on you, and for each<br> food you collect, you get one point. If you<br> " +
                "get bit, you die.<br> " +
                "Good luck, and don't get bit!</html>";

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.showInstructions(message);
        }
    }

    @Override
    public void update() throws InterruptedException {
        newCharacter = theModel.getNewCharacter();
    }
}

