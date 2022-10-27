package com.dt181g.project.controller;

import com.dt181g.project.model.Constants;
import com.dt181g.project.model.startanimation.*;
import com.dt181g.project.view.StartAnimationPanel;
import com.dt181g.project.view.StartView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Class representing a controller for the animation in start-frame, observing the changes of size.
 * @author Ebba Nimér
 */
public class SizeController implements SizeObserver {

    SizePool pool = SizePool.INSTANCE;
    StartAnimationPanel startAnimationPanel;
    StartView startView;
    ThreadSizeManager threadSizeManager;
    private int size;

    /**
     * Initialize size-controller by creating thread-size manager, starting threads and timer.
     * @param startView start-frame.
     */
    public SizeController(StartView startView){
        threadSizeManager = new ThreadSizeManager();
        this.startView = startView;
        pool.register(this);
        startThreads();
        startTimer();
    }

    /**
     * Start producer and consumer threads, retrieved from manager.
     */
    private void startThreads(){
        for (Thread thread : threadSizeManager.getProducerThreads()){
            thread.start();
        }

        for (Thread thread : threadSizeManager.getConsumerThreads()){
            thread.start();
        }
    }

    /**
     * Start timer and assign runnable to be performed each 100 ms.
     * Update animation with new size and add to start-frame.
     */
    public void startTimer(){
        Timer timer = new Timer(100, e -> {
            try {
                startAnimationPanel = new StartAnimationPanel(size, Constants.MASTER_ZOMBIE_PATH);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            startView.add(startAnimationPanel, BorderLayout.EAST);
            startView.revalidate();
        });
        timer.start();
    }

    /**
     * When there's an update in size-pool, update size value.
     */
    @Override public void update()  {
        this.size = pool.getSize();
        if (size < 40){                        // if size is less than 40, remove consumer and add producers.
            threadSizeManager.removeConsumer();
            threadSizeManager.addProducer();
        } else if (size >= 250) {              // if size is more than 250, do opposite.
            threadSizeManager.removeProducer();
            threadSizeManager.addConsumer();
        }
    }
}
