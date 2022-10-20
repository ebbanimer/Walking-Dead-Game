package com.dt181g.project.controller;

import com.dt181g.project.model.Constants;
import com.dt181g.project.model.Observer;
import com.dt181g.project.model.Model;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.model.factories.Food;
import com.dt181g.project.model.factories.Zombie;
import com.dt181g.project.view.ButtonPanel;
import com.dt181g.project.view.GameFrame;
import com.dt181g.project.view.GamePanel;
import com.dt181g.project.view.StatsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Deque;
import java.util.LinkedList;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class representing the controller of game simulation, observing model
 * @author Ebba Nimér
 */
public class GameController implements Observer {

    private final Model theModel;
    private final Character gameCharacter;
    private final Deque<Food> foods = new LinkedList<>();
    private final Deque<Zombie> zombies = new LinkedList<>();
    volatile boolean gameOver = false;
    volatile boolean levelOne = false;
    volatile boolean levelTwo = false;
    Timer timer;

    private final GameFrame gameFrame;
    private final StatsPanel statsPanel;
    private final GamePanel gamePanel;
    private final ButtonPanel buttonPanel;
    private AddKeyGame keyGame;

    private JLabel imgLbl;
    private JLabel timeCount;
    private JLabel match;

    /**
     * Initialize controller by creating the game-frame with character values, and adding listeners.
     * @param newCharacter character in game.
     * @param model model.
     * @throws InterruptedException exception if interrupted.
     */
    public GameController(Character newCharacter, Model model) throws InterruptedException {

        gameCharacter = newCharacter;
        theModel = model;     // assign model that was created in start-controller, to avoid creating new model losing values.
        theModel.register(this);

        gameFrame = new GameFrame(gameCharacter.getPath(), gameCharacter.getScore());
        statsPanel = gameFrame.getStatsPanel();
        gamePanel = gameFrame.getGamePanel();
        buttonPanel = gameFrame.getBtnPanel();

        initializeGame();  // intialize game

        buttonPanel.addExitGame(new AddExitButton());
        buttonPanel.addEndGame(new AddEndGameButton());
    }

    /**
     * Initializing game in level one, creating labels and starting timer.
     */
    private void initializeGame() {
        levelOne = true;
        levelTwo = false;
        keyGame = new AddKeyGame();

        theModel.initGame("LevelOne");

        createFoodLabels();
        createZombieLabels();

        imgLbl = gamePanel.getImgLbl();
        gameFrame.addKeyGame(keyGame);
        startTimer();
    }

    /**
     * Create food labels, using object pool pattern.
     */
    private void createFoodLabels(){
        // while model has food, create pass food to view to create label.
        while (theModel.hasAnotherFood()){
            Food food = theModel.getFood();
            foods.add(food);
            gamePanel.addFoods(food);
        }
    }

    /**
     * Create zombie labels, using object pool pattern.
     */
    private void createZombieLabels(){
        while (theModel.hasAnotherZombie()){
            Zombie zombie = theModel.getZombie();
            zombies.add(zombie);
            gamePanel.addZombies(zombie);
        }

        // initialize animation for each zombie.
        for (JLabel zombieLbl : gamePanel.getZombieLabels()){
            TimerTask task = new ZombieAnimation(zombieLbl, this);
            Timer timer = new Timer();
            timer.schedule(task, 100);
        }
    }

    class AddInstructionsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonPanel.displayInstructionMessage(Constants.INSTRUCTION_MESSAGE);
            gameFrame.setFocusable(true);
        }
    }

    /**
     * Inner class representing game for moving character.
     */
    class AddKeyGame extends KeyAdapter {
        Deque<JLabel> foodLabels = gamePanel.getFoodLabels();

        @Override
        public void keyPressed(KeyEvent e){
            if (foodLabels.isEmpty()){    // if foodlabels is empty, the character has won.
                if (levelOne){
                    int result = gameFrame.displayWinMsg(Constants.WIN_MESSAGE_1); // give character option to start next level.
                    switch (result){
                        case 0 -> {
                            try {
                                nextLevel();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        case 1 -> {
                            stopGame();
                            gameFrame.dispose();
                        }
                    }
                } else if (levelTwo){
                    gameFrame.displayWinTwoMsg(Constants.WIN_MESSAGE_2);
                    stopGame();
                }
            }
            // if the game is yet not over, move character and verify if there was a collision.
            if (!gameOver){
                moveCharacter(e.getKeyCode());
                try {
                    detectFoodCollision();
                    detectZombieCollision();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        /**
         * Move character based on key.
         * @param keyCode key that was pressed
         */
        private void moveCharacter(int keyCode){
            // change coordinates based on which key
            switch (keyCode) {
                case 37 -> imgLbl.setLocation(imgLbl.getX() - 10, imgLbl.getY());
                case 38 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() - 10);
                case 39 -> imgLbl.setLocation(imgLbl.getX() + 10, imgLbl.getY());
                case 40 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() + 10);
            }
        }

        /**
         * Verify if the character hit a food element using Streams API.
         * @throws InterruptedException exception if interrupted
         */
        private void detectFoodCollision() throws InterruptedException {
            // find if there's a match in location.
            match = foodLabels.stream()
                    .filter(jLabel -> jLabel.getBounds().intersects(imgLbl.getBounds()))
                    .findFirst().orElse(null);

            // if there's a match..
            if (match != null){
                gamePanel.foodTaken(match);   // remove label from game panel using dedicated method in view
                theModel.returnFood(foods.poll());   // return food
                foodLabels = gamePanel.getFoodLabels();   // retrieve new list of existing foodlabels
                gameCharacter.setScore(1);      // increase score by one
                statsPanel.updateScore(gameCharacter.getScore());                    // update values in view
                statsPanel.updateFoodCount(gamePanel.getFoodLabels().size());
            }
        }

        /**
         * Verify if the character hit a zombie, using Streams API.
         * @throws InterruptedException exception if interrupted.
         */
        private void detectZombieCollision() throws InterruptedException {
            Deque<JLabel> zombieLabels = gamePanel.getZombieLabels();
            match = zombieLabels.stream()
                    .filter(jLabel -> jLabel.getBounds().intersects(imgLbl.getBounds()))
                    .findFirst().orElse(null);

            if (match != null){
                gameCharacter.setDead(true);        // mark character as dead
                gameFrame.displayKilled(Constants.DEAD);
                stopGame();
            }
        }
    }

    /**
     * Inner class representing action listener for exiting game.
     */
    class AddExitButton implements ActionListener {

        /**
         * Stop game and dispose frame.
         * @param e action event.
         */
        @Override public void actionPerformed(ActionEvent e) {
            stopGame();
            gameFrame.dispose();
        }
    }

    /**
     * Inner class representing action listener for ending game.
     */
    class AddEndGameButton implements ActionListener {

        /**
         * Stop game.
         * @param e action event.
         */
        @Override public void actionPerformed(ActionEvent e) {
            stopGame();
        }
    }

    /**
     * Stop the game by cancelling timer, resetting values, and removing key-listener.
     */
    private void stopGame(){
        timer.cancel();
        int score = gameCharacter.getScore();
        gameCharacter.setScore(-score);
        gameOver = true;
        gameFrame.removeKeyListener(keyGame);
    }

    /**
     * If character wishes to go to next level, initialize level two.
     * @throws InterruptedException exceptioin if interrupted.
     */
    private void nextLevel() throws InterruptedException {
        levelOne = false;
        levelTwo = true;
        timer.cancel();

        // Return all foods and zombies to model.
        foods.forEach(theModel::returnFood);
        zombies.forEach(theModel::returnZombie);

        // Remove foods and labels in game panel.
        gamePanel.removeZombies();
        gamePanel.removeFoods();

        theModel.initGame("LevelTwo");

        // Create new labels.
        createFoodLabels();
        createZombieLabels();

        imgLbl.setLocation(0, 0);

        // Restart values.
        int score = gameCharacter.getScore();
        gameCharacter.setScore(-score);

        // update all values in stats
        statsPanel.updateScore(gameCharacter.getScore());
        statsPanel.updateFoodCount(gamePanel.getFoodLabels().size());
        statsPanel.updateZombieCount(gamePanel.getZombieLabels().size());
        startTimer();
    }

    /**
     * Start timer for game.
     */
    private void startTimer(){
        timer = new Timer();
        timeCount = statsPanel.getTimerCount();
        TimerTask task = new TimerTask() {
            int counter = 30;
            @Override
            public void run() {
                if (counter >= 0){
                    timeCount.setText(counter + " seconds");  // display seconds left of game
                    counter--;
                }
                else {
                    timer.cancel();
                    stopGame();    // when time ran out, stop game.
                    gameFrame.displayTimesUpMsg(Constants.TIMES_UP_MESSAGE);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * Return whether the game is over or not.
     * @return boolean if game over.
     */
    public boolean getGameOver(){
        return gameOver;
    }

    @Override
    public void update() {
        //match = theModel.getMatch();
    }

}

