package com.dt181g.project.model.startanimation;

import java.util.Random;

/**
 * Class representing producer in producer/consumer pattern. Adding to size-pool, implemented as runnable.
 * @author Ebba Nimér
 */
public class ProducerMaster implements Runnable{

    public volatile boolean shouldFinish = false;
    SizePool pool;

    public ProducerMaster(SizePool pool){
        this.pool = pool;
    }

    /**
     * While animation should run, assign random value at random interval to pool.
     */
    @Override public void run() {
        int delay = new Random().nextInt(3) + 1;
        int amount = new Random().nextInt(10) + 1;
        while(!shouldFinish){
            try {
                pool.addSize(amount);
                Thread.sleep(delay + 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
