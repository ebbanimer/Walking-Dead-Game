package com.dt181g.project.model.characters;

import static com.dt181g.project.model.Constants.MICHONNE_PATH;

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
        setPath(MICHONNE_PATH);
        setScore(0);
        setDead(false);
    }

}
