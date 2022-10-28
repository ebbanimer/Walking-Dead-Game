package com.dt181g.project.model.weaponfactory;

/**
 * Class representing Sword. Implementing Weapon, part of factory method.
 * @author Ebba Nimér
 */
public class Sword implements Weapon{

    /**
     * Setting the weapon type for character.
     * @return Name of weapon
     */
    @Override
    public String setCharWeapon() {
        return this.getClass().getSimpleName();
    }
}
