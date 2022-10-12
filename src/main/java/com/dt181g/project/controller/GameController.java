package com.dt181g.project.controller;

import com.dt181g.project.Observer;
import com.dt181g.project.ZombieAnimation;
import com.dt181g.project.model.Model;
import com.dt181g.project.model.characters.Character;
import com.dt181g.project.model.factories.Food;
import com.dt181g.project.model.factories.Zombie;
import com.dt181g.project.model.levels.LevelMethods;
import com.dt181g.project.model.levels.LevelOne;
import com.dt181g.project.model.levels.LevelTwo;
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

public class GameController implements Observer {

    private Character gameCharacter;
    private GameFrame gameFrame;
    private StatsPanel statsPanel;
    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;
    private AddKeyGame keyGame;
    private JLabel imgLbl;
    private Model theModel;
    private JLabel timeCount;
    boolean gameOver = false;
    private final Deque<Food> foods = new LinkedList<>();
    private final Deque<Zombie> zombies = new LinkedList<>();
    private JLabel match;
    Timer timer;

    public GameController(Character newCharacter, Model model) throws InterruptedException {
        gameCharacter = newCharacter;
        theModel = model;
        theModel.register(this);

        gameFrame = GameFrame.createGameFrame(gameCharacter);
        statsPanel = gameFrame.getStatsPanel();
        gamePanel = gameFrame.getGamePanel();
        buttonPanel = gameFrame.getBtnPanel();

        initializeGame();

        /*keyGame = new AddKeyGame();

        new AnimationController(gameFrame);

        LevelMethods level = new LevelOne();
        level.initLevel(theModel);

        createFoodLabels();
        createZombieLabels();

        imgLbl = gamePanel.getImgLbl();
        gameFrame.addKeyGame(keyGame);
        startTimer();*/
        buttonPanel.addInstructionButton(new AddInstructionsButton());
        buttonPanel.addEndGame(new AddEndGameButton());
        buttonPanel.addRestartBtn(new AddRestartButton());
    }

    private void initializeGame() throws InterruptedException {
        keyGame = new AddKeyGame();
        new AnimationController(gameFrame);

        LevelMethods level = new LevelOne();
        level.initLevel(theModel);

        createFoodLabels();
        createZombieLabels();

        imgLbl = gamePanel.getImgLbl();
        gameFrame.addKeyGame(keyGame);
        startTimer();
    }

    private void createFoodLabels(){
        while (theModel.hasAnotherFood()){
            Food food = theModel.getFood();
            foods.add(food);
            gamePanel.addFoods(food);
        }
    }

    private void createZombieLabels(){
        while (theModel.hasAnotherZombie()){
            Zombie zombie = theModel.getZombie();
            zombies.add(zombie);
            gamePanel.addZombies(zombie);
        }

        for (JLabel zombieLbl : gamePanel.getZombieLabels()){
            TimerTask task = new ZombieAnimation(zombieLbl, this);
            Timer timer = new Timer();
            timer.schedule(task, 100);
        }
    }

    class AddRestartButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                initializeGame();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    class AddInstructionsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "<html>Collect as much food as you can<br> without getting caught by the zombies.<br> " +
                    "You have 30 seconds on you, and for each<br> food you collect, you get one point. If you<br> " +
                    "get bit, you die.<br> " +
                    "Good luck, and don't get bit!</html>";

            buttonPanel.displayInstructionMessage(message);
            gameFrame.setFocusable(true);
            /*switch (result) {
                case 0 -> {
                    startTimer();
                    gameFrame.setFocusable(true);
                    gameFrame.addKeyGame(new AddKeyGame());
                }
                case 1 -> gameFrame.dispose();
            }*/
        }
    }

    class AddKeyGame extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            if (!gameOver){
                moveCharacter(imgLbl, e.getKeyCode());
                try {
                    detectFoodCollision();
                    detectZombieCollision();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void moveCharacter(JLabel imgLbl, int keyCode){
            switch (keyCode) {
                case 37 -> imgLbl.setLocation(imgLbl.getX() - 10, imgLbl.getY());
                case 38 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() - 10);
                case 39 -> imgLbl.setLocation(imgLbl.getX() + 10, imgLbl.getY());
                case 40 -> imgLbl.setLocation(imgLbl.getX(), imgLbl.getY() + 10);
                //case 32 -> detectZombieCollision(keyCode, imgLbl);
            }
        }

        private void detectFoodCollision() throws InterruptedException {
            Deque<JLabel> foodLabels = gamePanel.getFoodLabels();

            if(foodLabels.isEmpty()){
                // stop game, give option to level two
                int result = gameFrame.displayWinMsg("You have won!");
                switch (result) {
                    case 0 -> nextLevel();
                    case 1 -> {
                        stopGame();
                        gameFrame.dispose();
                    }
                }
            }

            theModel.detectFoodCollision(imgLbl, foodLabels);

            if (match != null){
                foodLabels.remove(match);
                theModel.returnFood(foods.poll());
                gameCharacter.setScore(1);
                statsPanel.updateScore(gameCharacter.getScore());
                statsPanel.updateFoodCount(1);
                gamePanel.foodTaken(match);
            }
        }

        private void detectZombieCollision() throws InterruptedException {
            Deque<JLabel> zombieLabels = gamePanel.getZombieLabels();

            theModel.detectZombieCollision(imgLbl, zombieLabels);

            if (match != null){
                gameCharacter.setDead(true);
                gameFrame.displayKilled("Character killed...");
                stopGame();
            }
        }
    }

    class AddEndGameButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            stopGame();
            gameFrame.dispose();
        }
    }


    private void stopGame(){
        int score = gameCharacter.getScore();
        gameCharacter.setScore(-score);
        gameOver = true;
        timer.cancel();
        gameFrame.removeKeyListener(keyGame);
    }

    private void nextLevel() throws InterruptedException {
        timer.cancel();

        foods.forEach(food -> theModel.returnFood(food));
        zombies.forEach(zombie -> theModel.returnZombie(zombie));

        gamePanel.removeZombies();
        gamePanel.removeFoods();

        LevelMethods levelTwo = new LevelTwo();
        levelTwo.initLevel(theModel);

        createFoodLabels();
        createZombieLabels();

        imgLbl.setLocation(0, 0);

        int score = gameCharacter.getScore();
        gameCharacter.setScore(-score);

        statsPanel.updateScore(gameCharacter.getScore());
        statsPanel.updateFoodCount(-5);
        startTimer();
    }


    private void startTimer(){
        timer = new Timer();
        timeCount = statsPanel.getTimerCount();
        TimerTask task = new TimerTask() {
            int counter = 30;
            @Override
            public void run() {
                if (counter >= 0){
                    timeCount.setText(counter + " seconds");
                    counter--;
                }
                else {
                    gameFrame.displayTimesUpMsg("Times up!");
                    stopGame();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public boolean getGameOver(){
        return gameOver;
    }

    @Override
    public void update() throws InterruptedException {
        match = theModel.getMatch();
    }

}

