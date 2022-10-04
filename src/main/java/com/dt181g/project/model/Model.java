package com.dt181g.project.model;

import com.dt181g.project.model.characters.Character;

public class Model {

    private Character newCharacter = null;

    public void createCharacter(String character){

        CharacterFactory characterFactory = new CharacterFactory();
        newCharacter = characterFactory.makeCharacter(character);
    }

    public Character getNewCharacter(){
        return newCharacter;
    }
}
