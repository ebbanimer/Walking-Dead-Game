package com.dt181g.project.controller;

import com.dt181g.project.model.Constants;
import com.dt181g.project.model.startanimation.ConsumerMaster;
import com.dt181g.project.model.startanimation.ProducerMaster;
import com.dt181g.project.model.startanimation.SizeObserver;
import com.dt181g.project.model.startanimation.SizePool;
import com.dt181g.project.view.StartAnimationPanel;
import com.dt181g.project.view.StartView;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

public class SizeController implements SizeObserver {

    SizePool pool = SizePool.INSTANCE;
    StartAnimationPanel startAnimationPanel;
    StartView startView;

    private final Deque<ProducerMaster> producerThreads = new LinkedList<>();
    private final Deque<ConsumerMaster> consumerThreads = new LinkedList<>();
    private int size;

    public SizeController(StartView startView){
        this.startView = startView;
        pool.register(this);
        startThreads();
        startTimer();
    }

    public void startThreads(){
        for (int i = 1; i <= pool.getProducers(); i++){
            ProducerMaster producer = new ProducerMaster();
            Thread thread = new Thread(producer);
            thread.start();
            producerThreads.add(producer);
        }

        for (int i = 1; i <= pool.getConsumers(); i++){
            ConsumerMaster consumer= new ConsumerMaster();
            Thread thread = new Thread(consumer);
            thread.start();
            consumerThreads.add(consumer);
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

        if (size < 40) {
            for (int i = 0; i < consumerThreads.size() / 2; i++) {
                if (2 <= consumerThreads.size()) {

                    consumerThreads.stream()
                            .findFirst().ifPresent(consumer -> consumer.shouldFinish = true);
                    consumerThreads.pop();

                }
                if (producerThreads.size() <= 19) {
                    ProducerMaster producer = new ProducerMaster();
                    Thread thread = new Thread(producer);
                    thread.start();
                    producerThreads.add(producer);
                }
            }
        } else if (size >= 150) {
            for (int i = 0; i <= producerThreads.size() / 2; i++) {
                if (2 <= producerThreads.size()) {
                    producerThreads.stream()
                            .findFirst().ifPresent(producer -> producer.shouldFinish = true);
                    producerThreads.pop();
                }

                if (consumerThreads.size() <= 19) {
                    ConsumerMaster consumer= new ConsumerMaster();
                    Thread thread = new Thread(consumer);
                    thread.start();
                    consumerThreads.add(consumer);
                }
            }
        }
    }
}
