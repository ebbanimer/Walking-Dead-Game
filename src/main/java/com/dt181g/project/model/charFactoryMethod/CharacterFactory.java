package com.dt181g.project.model.charFactoryMethod;

/**
 * Factory method pattern
 */
public class CharacterFactory {

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
