package com.dt181g.project.model;

import com.dt181g.project.Observer;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.model.characters.CharacterFactory;
import com.dt181g.project.model.factories.*;
import com.dt181g.project.model.levels.LevelMethods;
import com.dt181g.project.model.levels.LevelOne;
import com.dt181g.project.model.levels.LevelTwo;

import java.util.*;

/**
 * Class representing the model, which handles data. Implemented as an observable.
 * @author Ebba Nimér
 */
public class Model implements com.dt181g.project.Observable {

    AbstractFactory<Food> foodAbstractFactory;
    AbstractFactory<Zombie> zombieAbstractFactory;
    CharacterFactory characterFactory;

    private final ArrayList<com.dt181g.project.Observer> observers = new ArrayList<>();
    private Character gameCharacter;
    private Deque<Food> foods = new LinkedList<>();
    private Deque<Zombie> zombies = new LinkedList<>();
    private final List<Character> characters = new LinkedList<>();
    List<String> characterNames = new ArrayList<>();

    /**
     * Initialize model with initializing the factories for characters, food, and zombies.
     */
    public Model(){
        foodAbstractFactory = new FoodFactory();
        zombieAbstractFactory = new ZombieFactory();
        characterFactory = new CharacterFactory();
    }

    /**
     * Make a list of characters using factory method.
     */
    public void initializeCharacterList(){
        characters.add(characterFactory.createCharacter("Rick"));
        characters.add(characterFactory.createCharacter("Daryl"));
        characters.add(characterFactory.createCharacter("Michonne"));
        characters.add(characterFactory.createCharacter("Eugene"));
    }

    /**
     * Sort the list to A-Z, using Streams API.
     * @return list of character names.
     */
    public List<String> sortCharacterNames(){

        // Create a new list with sorted characters.
        List<Character> sortedCharList = characters.stream()
                .sorted(Comparator.comparing(Character::getName)).toList();

        // Add the names of characters in list.
        for (Character character : sortedCharList){
            characterNames.add(character.getName());
        }
        return characterNames;
    }

    /**
     * Retrieve character-object based on string, using Streams API.
     * @param nameOfCharacter name of character
     * @throws InterruptedException exception if interrupted
     */
    public void createCharacter(String nameOfCharacter) throws InterruptedException {
        // Filter through the list, finding the character based on name.
        gameCharacter = characters.stream()
                .filter(character -> character.getName().equals(nameOfCharacter))
                .findFirst().orElse(null);
        notifyObserver();     // notify observer when filtering is done.
    }

    /**
     * Initialize game by starting desired level.
     * @param level level to be started
     */
    public void initGame(String level){
        if (level.equals("LevelOne")){
            LevelMethods levelOne = new LevelOne();
            levelOne.initLevel(this);     // pass model to level
        } else if (level.equals("LevelTwo")){
            LevelMethods levelTwo = new LevelTwo();
            levelTwo.initLevel(this);
        }
    }

    /**
     * Create food or zombie items, based on passed name and amount, using abstract factory method.
     * @param item item to be created
     * @param amount number of items
     */
    public void createItem(String item, int amount) {
        if ("Carrot".equals(item) || "Salad".equals(item))
            foods = foodAbstractFactory.create(item, amount);
        else if ("ZombieOne".equals(item) || "ZombieTwo".equals(item))
            zombies = zombieAbstractFactory.create(item, amount);

    }

    /**
     * Return food to client.
     * @return food object.
     */
    public Food getFood(){
        return foods.pollFirst();
    }

    /**
     * Verify that food-list is not empty.
     * @return boolean whether list is empty or not.
     */
    public boolean hasAnotherFood(){
        return !foods.isEmpty();
    }

    /**
     * Return food to list.
     * @param food food object.
     */
    public void returnFood(Food food){
        foods.add(food);
    }

    /**
     * Return zombie to client.
     * @return zombie-object.
     */
    public Zombie getZombie(){
        return zombies.pollFirst();
    }

    /**
     * Verify if list of zombies is empty or not.
     * @return boolean whether list is empty or not.
     */
    public boolean hasAnotherZombie(){
        return !zombies.isEmpty();
    }

    /**
     * Return zombie to list.
     * @param zombie zombie to be returned.
     */
    public void returnZombie(Zombie zombie){
        zombies.add(zombie);
    }

    /**
     * Return character object to client.
     * @return character
     */
    public Character getNewCharacter(){
        return gameCharacter;
    }

    /**
     * Notify observer when changes happen.
     * @throws InterruptedException exception if interrupted.
     */
    @Override public void notifyObserver() throws InterruptedException {
        for (com.dt181g.project.Observer o : observers) {
            o.update();
        }
    }

    /**
     * register the observer.
     * @param o observer
     */
    @Override public void register(com.dt181g.project.Observer o) {
        observers.add(o);
    }

    /**
     * unregister the observer.
     * @param o observer.
     */
    @Override public void unregister(Observer o) {
        observers.remove(o);
    }
}
