package com.dt181g.project.model.characters;

public abstract class Character {

    private String name;
    private String path;
    private String weapon;
    private boolean isDead;
    private int score;

    protected void setName(String name){
        this.name = name;
    }

    protected void setPath(String path){
        this.path = path;
    }

    protected void setWeapon(String weapon){
        this.weapon = weapon;
    }

    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

    public void setScore(int score){
        this.score = this.score + score;
    }

    public String getName(){
        return name;
    }

    public String getPath(){
        return path;
    }

    public String getWeapon(){return weapon;}

    public int getScore(){
        return score;
    }

    public boolean getIsDead(){ return isDead; }

}
