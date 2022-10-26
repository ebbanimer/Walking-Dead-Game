package com.dt181g.project.model.characters;

/**
 * Class representing Michonne, extending character class.
 * @author Ebba Nimér.
 */
public class Michonne extends Character{

    /**
     * Initializing Michonne with start-values.
     */
    public Michonne(){
        setName("Michonne");
        setPath("src\\main\\resources\\images\\michonne.png");
        setWeapon("Sword");
        setScore(0);
        setDead(false);
    }

}
