package com.dt181g.project.controller;

import com.dt181g.project.model.Constants;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.model.Observer;
import com.dt181g.project.view.StartView;
import com.dt181g.project.model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StartController implements Observer {

    private final StartView startView;
    private final Model theModel;
    private List<Character> characters;
    private Character gameCharacter;
    static Character newCharacter;

    public StartController(){
        this.theModel = new Model();
        this.startView = new StartView(initGui().toArray(new String[0]), 50, Constants.MASTER_ZOMBIE_PATH);
        startView.addComboListener(new AddCharacterPickListener());
        startView.addStartListener(new AddStartListener());
        startView.addInstructionsListener(new AddInstructionsListener());
        new SizeController(startView);
        theModel.register(this);
    }

    private List<String> initGui(){
        this.theModel.initializeCharacterList();
        List<String> list = theModel.sortCharacterNames();
        list.add(0, Constants.PICK_CHARACTER);
        return list;
    }

    class AddCharacterPickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String characterName = startView.getCharacter();
            if (characterName.equals(Constants.PICK_CHARACTER)) {
                startView.displayError(Constants.ERROR);
            } else {
                try {
                    theModel.createCharacter(characterName);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            if (newCharacter != null && !newCharacter.getIsDead()){
                gameCharacter = newCharacter;
                startView.setCharacter(newCharacter.getName(), newCharacter.getWeapon(), newCharacter.getPath());
            } else {
                assert newCharacter != null;
                if (newCharacter.getIsDead()){
                    startView.displayDead(characterName + Constants.DEAD_START_VIEW);
                }
            }
        }
    }

    class AddStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameCharacter != null){
                try {
                    new GameController(gameCharacter, theModel);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                startView.displayError(Constants.ERROR);
            }
        }
    }

    class AddInstructionsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            startView.showInstructions(Constants.INSTRUCTION_MESSAGE);
        }
    }

    @Override
    public void update() {
        newCharacter = theModel.getNewCharacter();
    }
}

