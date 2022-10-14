package com.dt181g.project.controller;

import com.dt181g.project.Constants;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.Observer;
import com.dt181g.project.view.StartView;
import com.dt181g.project.model.Model;

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
        this.startView = new StartView(initGui().toArray(new String[0]));
        startView.addComboListener(new AddCharacterPickListener());
        startView.addStartListener(new AddStartListener());
        startView.addInstructionsListener(new AddInstructionsListener());
        theModel.register(this);
    }

    private List<String> initGui(){
        this.theModel.initializeCharacterList();
        List<String> list = theModel.sortCharacterNames();
        list.add(0, "Pick a character...");
        return list;
    }

    class AddCharacterPickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String characterName = startView.getCharacter();
            if (characterName.equals("Pick a character...")) {
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
            } else if (newCharacter.getIsDead()){
                startView.displayDead(Constants.DEAD);
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
    public void update() throws InterruptedException {
        newCharacter = theModel.getNewCharacter();
    }
}

