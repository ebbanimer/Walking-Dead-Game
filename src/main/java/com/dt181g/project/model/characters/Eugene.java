package com.dt181g.project.model.characters;

import static com.dt181g.project.model.Constants.EUGENE_PATH;

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
        setPath(EUGENE_PATH);
        setWeapon("Intelligence");
        setScore(0);
        setDead(false);
    }
}
