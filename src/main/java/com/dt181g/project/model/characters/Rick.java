package com.dt181g.project.model.characters;

import static com.dt181g.project.Constants.RICK_PATH;

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
        setPath(RICK_PATH);
        setScore(0);
        setDead(false);
    }
}
