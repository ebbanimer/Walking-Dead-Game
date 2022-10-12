package com.dt181g.project.animation;

import java.util.ArrayList;

public class Animation implements AnimationObservable{

    public static final Animation INSTANCE = new Animation();
    private final ArrayList<AnimationObserver> observers;
    private final int producers;
    private final int consumers;
    private volatile int score;

    private Animation(){
        observers = new ArrayList<>();
        producers = 6;
        consumers = 5;
        score = 50;
    }

    public synchronized void addScore(int amount) throws Exception {
        score = score + amount;
        notifyObserver();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeScore(int amount) throws Exception {
        while (score < amount) {
            wait();
        }
        notify();
        score = score - amount;
        notifyObserver();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getProducers() {
        return producers;
    }

    public int getConsumers() {
        return consumers;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void notifyObserver() throws Exception {
        for (AnimationObserver o : observers) {
            o.update();
        }
    }

    @Override
    public void register(AnimationObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(AnimationObserver o) {
        observers.remove(o);
    }
}

