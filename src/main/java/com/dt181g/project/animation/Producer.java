package com.dt181g.project.animation;

import java.util.Random;

public class Producer implements Runnable{

    private int delay = new Random().nextInt(5) + 1;
    private int amount = new Random().nextInt(10) + 1;
    public volatile boolean shouldFinish = false;

    @Override
    public void run() {
        while(!shouldFinish){
            try {
                Animation.INSTANCE.addScore(amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
