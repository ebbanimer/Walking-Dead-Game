package com.dt181g.project.model;

import com.dt181g.project.Observable;
import com.dt181g.project.Observer;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.model.characters.CharacterFactory;
import com.dt181g.project.model.factories.*;

import javax.swing.*;
import java.util.*;

public class Model implements Observable {

    AbstractFactory<Food> foodAbstractFactory;
    AbstractFactory<Zombie> zombieAbstractFactory;
    CharacterFactory characterFactory;

    private final ArrayList<Observer> observers = new ArrayList<>();
    private Character gameCharacter;
    private Deque<Food> foods = new LinkedList<>();
    private Deque<Zombie> zombies = new LinkedList<>();
    private Deque<Character> characters = new LinkedList<>();
    List<String> characterNames = new ArrayList<>();
    private JLabel match;

    public Model(){
        foodAbstractFactory = new FoodFactory();
        zombieAbstractFactory = new ZombieFactory();
        characterFactory = new CharacterFactory();
    }

    public void initializeCharacterList(){
        characters.add(characterFactory.createCharacter("Rick"));
        characters.add(characterFactory.createCharacter("Daryl"));
        characters.add(characterFactory.createCharacter("Michonne"));
        characters.add(characterFactory.createCharacter("Eugene"));
    }

    public List<String> sortCharacterNames(){
        List<Character> sortedCharList = characters.stream()
                .sorted(Comparator.comparing(Character::getName)).toList();

        for (Character character : sortedCharList){
            characterNames.add(character.getName());
        }
        return characterNames;
    }

    public void createCharacter(String nameOfCharacter) throws InterruptedException {
        gameCharacter = characters.stream()
                .filter(character -> character.getName().equals(nameOfCharacter))
                .findFirst().orElse(null);
        notifyObserver();
    }

    public void createItem(String item, int amount) {
        if ("Carrot".equals(item)){
            foods = foodAbstractFactory.create(item, amount);
        } else if ("Salad".equals(item)){
            foods = foodAbstractFactory.create(item, amount);
        } else if ("ZombieOne".equals(item)){
            zombies = zombieAbstractFactory.create(item, amount);
        } else if ("ZombieTwo".equals(item)){
            System.out.println("From model: " + zombies.size() + " level two, before creating zombie two");
            zombies = zombieAbstractFactory.create(item, amount);
            System.out.println("From model: " + zombies.size() + " level two, after creating zombie two");
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
        return foods.pollFirst();
    }

    public boolean hasAnotherFood(){
        return !foods.isEmpty();
    }

    public void returnFood(Food food){
        foods.add(food);
    }

    public Zombie getZombie(){
        return zombies.pollFirst();
    }

    public boolean hasAnotherZombie(){
        return !zombies.isEmpty();
    }

    public void returnZombie(Zombie zombie){
        zombies.add(zombie);
        System.out.println("Model, returning zombies. Size: " + zombies.size());
    }

    public Character getNewCharacter(){
        return gameCharacter;
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
