package com.dt181g.project.model;

import com.dt181g.project.Observable;
import com.dt181g.project.Observer;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.model.characters.CharacterFactory;
import com.dt181g.project.model.factories.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Model implements Observable {

    private final ArrayList<Observer> observers = new ArrayList<>();
    private Character newCharacter = null;
    private Deque<Food> foods = new LinkedList<>();
    private Deque<Zombie> zombies = new LinkedList<>();
    private JLabel match;


    public void createCharacter(String character) throws InterruptedException {
        CharacterFactory characterFactory = new CharacterFactory();
        newCharacter = characterFactory.createCharacter(character);
        notifyObserver();
    }

    public void createItem(String item, int amount) throws InterruptedException {
        if ("Food".equals(item)){
            AbstractFactory<Food> abstractFactory = new FoodFactory();
            foods = abstractFactory.create(item, amount);
            System.out.println("Foods after creation: " + foods.size());
        } else if ("Zombie".equals(item)){
            AbstractFactory<Zombie> abstractFactory = new ZombieFactory();
            zombies = abstractFactory.create(item, amount);
            System.out.println("Zombies after creation: " + zombies.size());
        }
    }

    public void detectFoodCollision(JLabel imgLbl, Deque<JLabel> foodLabels) throws InterruptedException {
        match = foodLabels.stream()
                .filter(jLabel -> jLabel.getBounds().intersects(imgLbl.getBounds()))
                .findFirst().orElse(null);
        notifyObserver();
    }

    public void detectZombieCollision(JLabel imgLbl, Deque<JLabel> zombieLabels) throws InterruptedException {
        match = zombieLabels.stream()
                .filter(jLabel -> jLabel.getBounds().intersects(imgLbl.getBounds()))
                .findFirst().orElse(null);
        notifyObserver();
    }

    public Food getFood(){
        System.out.println("Foods when taken: " + foods.size());
        return foods.pollFirst();
    }

    public boolean hasAnotherFood(){
        return !foods.isEmpty();
    }

    public void returnFood(Food food){
        foods.add(food);
        System.out.println("Foods when returning: " + foods.size());
    }

    public Zombie getZombie(){
        System.out.println("Zombies when taken: " + zombies.size());
        return zombies.pollFirst();
    }

    public boolean hasAnotherZombie(){
        return !zombies.isEmpty();
    }

    public void returnZombie(Zombie zombie){
        zombies.add(zombie);
        System.out.println("Zombies when returning: " + zombies.size());
    }

    public Character getNewCharacter(){
        return newCharacter;
    }

    public JLabel getMatch(){
        return match;
    }

    @Override
    public void notifyObserver() throws InterruptedException {
        for (Observer o : observers) {
            o.update();
        }
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }
}
