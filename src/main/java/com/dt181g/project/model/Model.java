package com.dt181g.project.model;

import com.dt181g.project.Observables;
import com.dt181g.project.Observers;
import com.dt181g.project.model.charFactoryMethod.Character;
import com.dt181g.project.model.charFactoryMethod.CharacterFactory;
import com.dt181g.project.model.itemAbstractFactory.*;

import java.util.ArrayList;

public class Model implements Observables {

    private Character newCharacter = null;
    private ArrayList<Zombie> zombies;
    private ArrayList<Food> foods;

    public void createCharacter(String character){
        CharacterFactory characterFactory = new CharacterFactory();
        newCharacter = characterFactory.createCharacter(character);
    }

    public void createItem(String item, int amount){
        if ("Food".equals(item)){
            AbstractFactory<Food> abstractFactory = new FoodFactory();
            foods = (ArrayList<Food>) abstractFactory.create(item, amount);
        } else if ("Zombie".equals(item)){
            AbstractFactory<Zombie> abstractFactory = new ZombieFactory();
            zombies = (ArrayList<Zombie>) abstractFactory.create(item, amount);
        }
    }

    public Character getNewCharacter(){
        return newCharacter;
    }

    public ArrayList<Zombie> getZombies(){
        return zombies;
    }

    public ArrayList<Food> getFoods(){
        return foods;
    }


    @Override
    public void update(Observables o, Object arg) {

    }

    @Override
    public void notiFyObserver() {

    }

    @Override
    public void register(Observers o) {

    }

    @Override
    public void unregister(Observers o) {

    }
}
