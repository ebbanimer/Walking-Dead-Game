package com.dt181g.project.model;

import com.dt181g.project.model.charFactoryMethod.Character;
import com.dt181g.project.model.charFactoryMethod.CharacterFactory;
import com.dt181g.project.model.itemAbstractFactory.*;

import java.util.ArrayList;

public class Model {

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
}
