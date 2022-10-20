package com.dt181g.project.model.characters;

/**
 * Class representing Daryl, extending character class.
 * @author Ebba Nimér.
 */
public class Daryl extends Character{

    /**
     * Initialize Daryl with start-values.
     */
    public Daryl(){
        setName("Daryl");
        setPath("src\\main\\java\\com\\dt181g\\project\\images\\daryl.png");
        setWeapon("Bow");
        setScore(0);
        setDead(false);
    }
}
