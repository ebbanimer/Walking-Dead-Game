package com.dt181g.project.model.startanimation;

import java.util.Random;

/**
 * Class representing consumer of producer/consumer pattern. Withdrawing size amount, implemented as
 * runnable.
 * @author Ebba Nimér
 */
public class ConsumerMaster implements Runnable{

    public volatile boolean shouldFinish = false;
    SizePool pool;

    public ConsumerMaster(SizePool pool){
        this.pool = pool;
    }

    /**
     * While the application is running, withdraw random amount from pool at random interval.
     */
    @Override public void run(){
        int amount = new Random().nextInt(20) + 1;
        int delay = new Random().nextInt(3) + 1;
        while(!shouldFinish){
            try {
                pool.removeSize(amount);
                Thread.sleep(delay + 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
