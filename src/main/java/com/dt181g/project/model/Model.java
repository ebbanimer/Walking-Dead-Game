package com.dt181g.project.model;

import com.dt181g.project.Observer;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.model.characters.CharacterFactory;
import com.dt181g.project.model.itemfactory.*;
import com.dt181g.project.model.weaponfactory.WeaponFactory;
import com.dt181g.project.model.levels.LevelMethods;
import com.dt181g.project.model.levels.LevelOne;
import com.dt181g.project.model.levels.LevelTwo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing the model, which handles data. Implemented as an observable. Distributes food and zombie objects
 * according to object pool patterns, and requesting factories for factory method and abstract factory.
 * @author Ebba Nimér
 */
public class Model implements Observable {

    private final ArrayList<Observer> observers = new ArrayList<>();

    private final AbstractFactory foodAbstractFactory;
    private final AbstractFactory zombieAbstractFactory;
    private final CharacterFactory characterFactory;
    private final WeaponFactory weaponFactory;

    private Deque<Object> foods = new LinkedList<>();
    private Deque<Object> zombies = new LinkedList<>();

    private final List<Character> characters = new LinkedList<>();
    private final List<String> characterNames = new ArrayList<>();
    private Character gameCharacter;
    private String weaponName;

    /**
     * Initialize model with initializing the factories for characters, food, and zombies.
     */
    public Model(){
        foodAbstractFactory = GetItemFactory.getFactory("Food");
        zombieAbstractFactory = GetItemFactory.getFactory("Zombie");
        characterFactory = new CharacterFactory();
        weaponFactory = new WeaponFactory();
    }

    /**
     * Make a list of characters using a factory.
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
                .sorted(Comparator.comparing(Character::getName))
                .collect(Collectors.toList());

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

        // Get weapon that belongs to the character, using factory method.
        weaponName = weaponFactory.createWeapon(gameCharacter).setCharWeapon();

        notifyObserver();     // notify observer when filtering is done.
    }

    /**
     * Initialize game by starting desired level. Part of template method pattern.
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
     * Return food to client, part of object pool distributor.
     * @return food object.
     */
    public Food getFood(){
        return (Food) foods.pollFirst();
    }

    /**
     * Return food-list to calling client.
     * @return food-list
     */
    public Deque<Object> getFoods(){ return foods; }

    /**
     * Clear food-list.
     */
    public void clearFoods(){
        foods.clear();
    }

    /**
     * Verify that food-list is not empty, part of object pool distributor.
     * @return boolean whether list is empty or not.
     */
    public boolean hasAnotherFood(){
        return !foods.isEmpty();
    }

    /**
     * Return food to list, part of object pool distributor.
     * @param food food object.
     */
    public void returnFood(Food food){
        foods.add(food);
    }

    /**
     * Return zombie to client, part of object pool distributor.
     * @return zombie-object.
     */
    public Zombie getZombie(){
        return (Zombie) zombies.pollFirst();
    }

    /**
     * Verify if list of zombies is empty or not, part of object pool distributor.
     * @return boolean whether list is empty or not.
     */
    public boolean hasAnotherZombie(){
        return !zombies.isEmpty();
    }

    /**
     * Return zombie to list, part of object pool distributor.
     * @param zombie zombie to be returned.
     */
    public void returnZombie(Zombie zombie){
        zombies.add(zombie);
    }

    /**
     * Return character object to calling client.
     * @return character
     */
    public Character getNewCharacter(){
        return gameCharacter;
    }

    /**
     * Return weapon to calling client.
     * @return name of weapon.
     */
    public String getWeapon(){
        return weaponName;
    }

    /**
     * Notify observer when changes happen.
     * @throws InterruptedException exception if interrupted.
     */
    @Override public void notifyObserver() throws InterruptedException {
        for (Observer o : observers) {
            o.update();
        }
    }

    /**
     * Register the observer.
     * @param o observer
     */
    @Override public void register(Observer o) {
        observers.add(o);
    }

    /**
     * Unregister the observer.
     * @param o observer.
     */
    @Override public void unregister(Observer o) {
        observers.remove(o);
    }
}
