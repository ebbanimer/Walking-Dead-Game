package com.dt181g.project.controller;

import com.dt181g.project.model.Constants;
import com.dt181g.project.model.startanimation.*;
import com.dt181g.project.view.StartAnimationPanel;
import com.dt181g.project.view.StartView;

import javax.swing.*;
import java.awt.*;

public class SizeController implements SizeObserver {

    SizePool pool = SizePool.INSTANCE;
    StartAnimationPanel startAnimationPanel;
    StartView startView;
    ThreadSizeManager threadSizeManager;
    private int size;

    public SizeController(StartView startView){
        threadSizeManager = new ThreadSizeManager();
        this.startView = startView;
        pool.register(this);
        startThreads();
        startTimer();
    }

    public void startThreads(){
        for (Thread thread : threadSizeManager.getProducerThreads()){
            if (!thread.isAlive())
                thread.start();
        }

        for (Thread thread : threadSizeManager.getConsumerThreads()){
            if (!thread.isAlive())
                thread.start();
        }
    }

    public void startTimer(){
        Timer timer = new Timer(1000, e -> {
            startAnimationPanel = new StartAnimationPanel(size, Constants.MASTER_ZOMBIE_PATH);
            startView.add(startAnimationPanel, BorderLayout.EAST);
            startView.revalidate();
        });
        timer.start();
    }

    @Override
    public void update()  {
        this.size = pool.getSize();
        if (size < 40){
            threadSizeManager.removeConsumer();
            threadSizeManager.addProducer();
            startThreads();
        } else if (size >= 250) {
            threadSizeManager.removeProducer();
            threadSizeManager.addConsumer();
            startThreads();
        }
    }
}
