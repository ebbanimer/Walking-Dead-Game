package com.dt181g.project.model.factories;

public class MasterZombie implements Zombie{

    public MasterZombie(int id){

    }

    @Override
    public String getPath() {
        return "src\\main\\java\\com\\dt181g\\project\\images\\transparent-masterzombie.png";
    }

    @Override
    public Integer getStartX() {
        return null;
    }

    @Override
    public Integer getStartY() {
        return null;
    }
}
