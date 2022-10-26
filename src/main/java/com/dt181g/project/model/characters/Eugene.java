package com.dt181g.project.model.characters;

/**
 * Class representing Eugene, extending character class.
 * @author Ebba Nimér.
 */
public class Eugene extends Character{

    /**
     * Initialize Eugene with start-values.
     */
    public Eugene(){
        setName("Eugene");
        setPath("src\\main\\resources\\images\\eugene.png");
        setWeapon("Intelligence");
        setScore(0);
        setDead(false);
    }
}
