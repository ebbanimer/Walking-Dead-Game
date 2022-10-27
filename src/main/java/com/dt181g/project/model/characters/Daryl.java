package com.dt181g.project.model.characters;

import static com.dt181g.project.model.Constants.DARYL_PATH;

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
        setPath(DARYL_PATH);
        setWeapon("Bow");
        setScore(0);
        setDead(false);
    }
}
