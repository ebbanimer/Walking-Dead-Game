package com.dt181g.project.model.characters;

/**
 * Class representing factory of characters. Part of Factory Method pattern.
 * @author Ebba Nimér
 */
public class CharacterFactory {

    /**
     * Create character based on name of character.
     * @param character name of character.
     * @return character
     */
    public Character createCharacter(String character){
        return switch (character) {
            case "Rick" -> new Rick();
            case "Michonne" -> new Michonne();
            case "Eugene" -> new Eugene();
            case "Daryl" -> new Daryl();
            default -> null;
        };
    }
}
