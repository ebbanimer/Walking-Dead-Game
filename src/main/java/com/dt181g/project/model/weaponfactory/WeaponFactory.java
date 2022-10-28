package com.dt181g.project.model.weaponfactory;
import com.dt181g.project.model.characters.Character;

/**
 * Factory for Weapon. Part of Factory method.
 * @author Ebba Nimér
 */
public class WeaponFactory {

    public Weapon chreateWeapon(Character character){
        if (character == null)
            return null;
        return switch (character.getClass().getSimpleName()) {
            case "Rick" -> new Gun();
            case "Eugene" -> new Intelligence();
            case "Daryl" -> new Bow();
            case "Michonne" -> new Sword();
            default -> throw new IllegalArgumentException("Invalid character");
        };
    }
}
