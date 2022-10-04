package com.dt181g.project.model;

import com.dt181g.project.model.characters.*;
import com.dt181g.project.model.characters.Character;

/**
 * Factory method pattern
 */
public class CharacterFactory {

    public Character makeCharacter(String character){

        return switch (character) {
            case "Rick" -> new Rick();
            case "Michonne" -> new Michonne();
            case "Eugene" -> new Eugene();
            default -> new Daryl();
        };
    }

}
