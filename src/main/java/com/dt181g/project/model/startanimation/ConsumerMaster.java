package com.dt181g.project.model.startanimation;

import java.util.Random;

public class ConsumerMaster implements Runnable{

    private final int amount = new Random().nextInt(20) + 1;
    private final int delay = new Random().nextInt(5) + 1;

    public volatile boolean shouldFinish = false;

    @Override
    public void run(){
        while(!shouldFinish){
            try {
                SizePool.INSTANCE.removeSize(amount);
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
