package com.dt181g.project.model.startanimation;

import java.util.Random;

public class ProducerMaster implements Runnable{

    private final int delay = new Random().nextInt(5) + 1;
    private final int amount = new Random().nextInt(10) + 1;
    public volatile boolean shouldFinish = false;

    @Override
    public void run() {
        while(!shouldFinish){
            try {
                SizePool.INSTANCE.addSize(amount);
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
