package com.dt181g.project.animation;

import java.util.Random;

public class Consumer implements Runnable{

    private final int amount = new Random().nextInt(20) + 1;
    private final int delay = new Random().nextInt(5) + 1;

    public volatile boolean shouldFinish = false;

    @Override
    public void run(){
        while(!shouldFinish){
            try {
                Animation.INSTANCE.removeScore(amount);
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
