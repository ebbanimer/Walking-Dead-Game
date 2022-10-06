package com.dt181g.project.model.charFactoryMethod;

public abstract class Character {

    private String name;
    private String weapon;
    private String IMG_PATH;
    private int startX;
    private int startY;
    private int score;
    private boolean isDead = false;

    public void setName(String name){
        this.name = name;
    }

    public void setImg(String IMG_PATH){
        this.IMG_PATH = IMG_PATH;
    }

    public void setWeapon(String weapon){
        this.weapon = weapon;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

    public String getName(){
        return name;
    }

    public String getWeapon(){
        return weapon;
    }

    public String getIMG_PATH(){
        return IMG_PATH;
    }

    public Integer getScore(){
        return score;
    }

    public boolean getIsDead(){
        return isDead;
    }

    public void killZombies(){
        System.out.println(getName() + " is killing zombies with " + getWeapon());
    }

    public void getBit(){
        isDead = true;
        System.out.println(getName() + " got bit. Game over!");
    }
}
