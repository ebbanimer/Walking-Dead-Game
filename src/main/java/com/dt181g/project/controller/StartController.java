package com.dt181g.project.controller;

import com.dt181g.project.model.Constants;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.Observer;
import com.dt181g.project.view.StartView;
import com.dt181g.project.model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Start-controller in program. Implemented as an observer to get updates from the observable.
 * @author Ebba Nimér
 */
public class StartController implements Observer {

    private final StartView startView;
    private final Model theModel;
    private Character gameCharacter;
    static Character newCharacter;

    /**
     * Initialize controller with starting model and view, adding listeners to view.
     */
    public StartController() throws IOException {
        this.theModel = new Model();
        // Initialize start-view with list of characters to be displayed, and animation image with size.
        this.startView = new StartView(initGui().toArray(new String[0]), 50, Constants.MASTER_ZOMBIE_PATH);
        startView.addComboListener(new AddCharacterPickListener());
        startView.addStartListener(new AddStartListener());
        startView.addInstructionsListener(new AddInstructionsListener());
        new SizeController(startView);      // initialize the zombie-graphics in separate controller.
        theModel.register(this);
    }

    /**
     * Initialize character-list from list in model, to be displayed in GUI.
     * @return list of character names.
     */
    private List<String> initGui(){
        this.theModel.initializeCharacterList();
        List<String> list = theModel.sortCharacterNames();
        list.add(0, Constants.PICK_CHARACTER);
        return list;
    }

    /**
     * Inner class representing actionsListener for choosing a character in view.
     * @author Ebba Nimér
     */
    class AddCharacterPickListener implements ActionListener {

        /**
         * Retrieve character name and verify that the character is not yet dead, and it is a valid
         * character.
         * @param e action event
         */
        @Override public void actionPerformed(ActionEvent e) {
            String characterName = startView.getCharacter();
            if (characterName.equals(Constants.PICK_CHARACTER)) {
                startView.displayError(Constants.ERROR);
            } else {
                try {
                    theModel.createCharacter(characterName);     // create character based from name
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            if (newCharacter != null && !newCharacter.getIsDead()){
                gameCharacter = newCharacter;    // if the creation was successful, assign new character to gamecharacter
                try {
                    startView.setCharacter(newCharacter.getName(), theModel.getWeapon(), newCharacter.getPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                assert newCharacter != null;
                if (newCharacter.getIsDead()){
                    startView.displayDead(characterName + Constants.DEAD_START_VIEW);
                }
            }
        }
    }

    /**
     * Inner class representing action listener for starting the game.
     * @author Ebba Nimér
     */
    class AddStartListener implements ActionListener {

        /**
         * If the character is created when starting the game, initialize the controller that handles the
         * game simulation, passing the character and model
         * @param e action event
         */
        @Override public void actionPerformed(ActionEvent e) {
            if (gameCharacter != null){
                try {
                    new GameController(gameCharacter, theModel);   // intialize controller for game
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                startView.displayError(Constants.ERROR);
            }
        }
    }

    /**
     * Inner class representing action listener for displaying instructions.
     */
    class AddInstructionsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            startView.showInstructions(Constants.INSTRUCTION_MESSAGE);
        }
    }

    /**
     * Update character based on notification from model.
     */
    @Override
    public void update() {
        newCharacter = theModel.getNewCharacter();
    }
}

