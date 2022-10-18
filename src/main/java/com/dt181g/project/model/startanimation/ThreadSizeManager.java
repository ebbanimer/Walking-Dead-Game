package com.dt181g.project.model.startanimation;

import java.util.Deque;
import java.util.LinkedList;

public class ThreadSizeManager {
    SizePool pool = SizePool.INSTANCE;
    private final Deque<ProducerMaster> producerRunnables = new LinkedList<>();
    private final Deque<ConsumerMaster> consumerRunnables = new LinkedList<>();
    private final Deque<Thread> producerThreads = new LinkedList<>();
    private final Deque<Thread> consumerThreads = new LinkedList<>();

    public ThreadSizeManager(){
        for (int i = 0; i < pool.getProducers(); i++){
            ProducerMaster producerMaster = new ProducerMaster();
            Thread thread = new Thread(producerMaster);
            producerThreads.add(thread);
            producerRunnables.add(producerMaster);
        }

        for (int i = 0; i < pool.getConsumers(); i++){
            ConsumerMaster consumerMaster = new ConsumerMaster();
            Thread thread = new Thread(consumerMaster);
            consumerThreads.add(thread);
            consumerRunnables.add(consumerMaster);
        }
    }

    public Deque<Thread> getProducerThreads(){
        return producerThreads;
    }

    public Deque<Thread> getConsumerThreads(){
        return consumerThreads;
    }

    public void removeConsumer() {
        if (!consumerRunnables.isEmpty()){
            consumerRunnables.stream()
                    .findFirst()
                    .ifPresent(consumerMaster -> consumerMaster.shouldFinish = true);
            consumerRunnables.removeFirst();
        }

        if (!consumerThreads.isEmpty()){
            consumerThreads.removeFirst();
        }
    }

    public void addProducer() {
        ProducerMaster producerMaster = new ProducerMaster();
        Thread thread = new Thread(producerMaster);
        producerThreads.add(thread);
        producerRunnables.add(producerMaster);
    }

    public void removeProducer() {
        if (!producerRunnables.isEmpty()){
            producerRunnables.stream()
                    .findFirst()
                    .ifPresent(producerMaster -> producerMaster.shouldFinish = true);
            producerRunnables.removeFirst();
        }

        if (!producerThreads.isEmpty()){
            producerThreads.removeFirst();
        }
    }

    public void addConsumer() {
        ConsumerMaster consumerMaster = new ConsumerMaster();
        Thread thread = new Thread(consumerMaster);
        consumerThreads.add(thread);
        consumerRunnables.add(consumerMaster);
    }
}
