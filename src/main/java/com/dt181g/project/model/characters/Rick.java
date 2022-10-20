package com.dt181g.project.model.characters;

/**
 * Class representing Rick, extending character class.
 * @author Ebba Nimér.
 */
public class Rick extends Character{

    /**
     * Initialize Rick with start-values.
     */
    public Rick(){
        setName("Rick");
        setPath("src\\main\\java\\com\\dt181g\\project\\images\\rick.png");
        setWeapon("Pistol");
        setScore(0);
        setDead(false);
    }
}
