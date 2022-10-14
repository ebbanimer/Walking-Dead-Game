package com.dt181g.project.controller;

import com.dt181g.project.model.Constants;
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

    private Model theModel;
    private Character gameCharacter;
    private final Deque<Food> foods = new LinkedList<>();
    private final Deque<Zombie> zombies = new LinkedList<>();
    volatile boolean gameOver = false;
    Timer timer;

    private GameFrame gameFrame;
    private StatsPanel statsPanel;
    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;
    private AddKeyGame keyGame;

    private JLabel imgLbl;
    private JLabel timeCount;
    private JLabel match;

    public GameController(Character newCharacter, Model model) throws InterruptedException {
        gameCharacter = newCharacter;
        theModel = model;
        theModel.register(this);

        gameFrame = new GameFrame(gameCharacter.getPath(), gameCharacter.getScore());
        statsPanel = gameFrame.getStatsPanel();
        gamePanel = gameFrame.getGamePanel();
        buttonPanel = gameFrame.getBtnPanel();

        initializeGame();

        buttonPanel.addInstructionButton(new AddInstructionsButton());
        buttonPanel.addEndGame(new AddEndGameButton());
    }

    private void initializeGame() {
        keyGame = new AddKeyGame();

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

    class AddInstructionsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonPanel.displayInstructionMessage(Constants.INSTRUCTION_MESSAGE);
            gameFrame.setFocusable(true);
        }
    }

    class AddKeyGame extends KeyAdapter {
        Deque<JLabel> foodLabels = gamePanel.getFoodLabels();
        @Override
        public void keyPressed(KeyEvent e){
            if (foodLabels.isEmpty()){
                int result = gameFrame.displayWinMsg(Constants.WIN_MESSAGE);
                switch (result) {
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
            }
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
            }
        }

        private void detectFoodCollision() throws InterruptedException {
            theModel.detectFoodCollision(imgLbl, foodLabels);

            if (match != null){
                gamePanel.foodTaken(match);
                theModel.returnFood(foods.poll());
                foodLabels.remove(match);
                gameCharacter.setScore(1);
                statsPanel.updateScore(gameCharacter.getScore());
                statsPanel.updateFoodCount(gamePanel.getFoodLabels().size());
            }
        }

        private void detectZombieCollision() throws InterruptedException {
            Deque<JLabel> zombieLabels = gamePanel.getZombieLabels();
            theModel.detectZombieCollision(imgLbl, zombieLabels);

            if (match != null){
                gameCharacter.setDead(true);
                gameFrame.displayKilled(Constants.DEAD);
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
        statsPanel.updateFoodCount(gamePanel.getFoodLabels().size());
        statsPanel.updateZombieCount(gamePanel.getZombieLabels().size());
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
                    gameFrame.displayTimesUpMsg(Constants.TIMES_UP_MESSAGE);
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
    public void update() {
        match = theModel.getMatch();
    }

}

