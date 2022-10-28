package com.dt181g.project.model.characters;

/**
 * Abstract class representing characters.
 * @author Ebba Nimér
 */


public abstract class Character {

    protected String name;
    protected String path;
    protected boolean isDead;
    protected int score;

    /**
     * Set character-name.
     * @param name name of character.
*/
    protected void setName(String name){
        this.name = name;
    }

/**
     * Set character image path.
     * @param path image path.
*/
    protected void setPath(String path){
        this.path = path;
    }

/**
     * Set alive-status of character.
     * @param isDead whether character is dead/alive.
*/
    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

/**
     * Set amount of score.
     * @param score amount of score.
*/
    public void setScore(int score){
        this.score = this.score + score;
    }

/**
     * Return character-name to client.
     * @return name
*/
    public String getName(){
        return name;
    }

/**
     * Return image path to client.
     * @return image path
*/
   public String getPath(){
        return path;
   }

/**
     * Return score to client.
     * @return amount of score.
*/
    public int getScore(){
        return score;
    }

/**
     * Return boolean if character is dead or not to client.
     * @return boolean if dead.
*/
    public boolean getIsDead(){ return isDead; }
}
