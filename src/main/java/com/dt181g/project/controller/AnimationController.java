package com.dt181g.project.controller;

import com.dt181g.project.animation.Animation;
import com.dt181g.project.animation.AnimationObserver;
import com.dt181g.project.animation.Consumer;
import com.dt181g.project.animation.Producer;
import com.dt181g.project.view.AnimationPanel;
import com.dt181g.project.view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

public class AnimationController implements AnimationObserver {

    Animation animation = Animation.INSTANCE;
    AnimationPanel animationPanel;
    GameFrame gameFrame;
    private final Deque<Producer> producerThreads = new LinkedList<>();
    private final Deque<Consumer> consumerThreads = new LinkedList<>();
    private int score;

    public AnimationController(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        animation.register(this);
        startThreads();
        startTimer();
    }

    public void startThreads(){

        for (int i = 1; i <= animation.getProducers(); i++){
            Producer producer = new Producer();
            Thread thread = new Thread(producer);
            thread.start();
            producerThreads.add(producer);
        }

        for (int i = 1; i <= animation.getConsumers(); i++){
            Consumer consumer= new Consumer();
            Thread thread = new Thread(consumer);
            thread.start();
            consumerThreads.add(consumer);
        }
    }

    public void startTimer(){
        Timer timer = new Timer(1000, e -> {
            animationPanel = new AnimationPanel(score);
            gameFrame.add(animationPanel, BorderLayout.EAST);
            gameFrame.revalidate();
        });
        timer.start();
    }

    @Override
    public void update() throws InterruptedException {
        this.score = animation.getScore();

        if (score < 40) {
            for (int i = 0; i < consumerThreads.size() / 2; i++) {
                if (2 <= consumerThreads.size()) {

                    consumerThreads.stream()
                            .findFirst().ifPresent(consumer -> consumer.shouldFinish = true);
                    consumerThreads.pop();

                }
                if (producerThreads.size() <= 19) {
                    Producer producer = new Producer();
                    Thread thread = new Thread(producer);
                    thread.start();
                    producerThreads.add(producer);
                }
            }
        } else if (score >= 150) {
            for (int i = 0; i <= producerThreads.size() / 2; i++) {
                if (2 <= producerThreads.size()) {
                    producerThreads.stream()
                            .findFirst().ifPresent(producer -> producer.shouldFinish = true);
                    producerThreads.pop();
                }

                if (consumerThreads.size() <= 19) {
                    Consumer consumer = new Consumer();
                    Thread thread = new Thread(consumer);
                    thread.start();
                    consumerThreads.add(consumer);
                }
            }
        }
    }
}

