package com.dt181g.project.model.startanimation;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Class handling threads.
 * @author Ebba Nimér
 */
public class ThreadSizeManager {
    SizePool pool = SizePool.INSTANCE;
    private final Deque<ProducerMaster> producerRunnables = new LinkedList<>();
    private final Deque<ConsumerMaster> consumerRunnables = new LinkedList<>();
    private final Deque<Thread> producerThreads = new LinkedList<>();
    private final Deque<Thread> consumerThreads = new LinkedList<>();

    /**
     * Initial class by starting threads determined by pool.
     */
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

    /**
     * Provide producer-threads to calling client.
     * @return list of threads.
     */
    public Deque<Thread> getProducerThreads(){
        return producerThreads;
    }

    /**
     * Provide consumer-threads to calling client.
     * @return list of threads
     */
    public Deque<Thread> getConsumerThreads(){
        return consumerThreads;
    }

    /**
     * Terminate consumer and remove thread from list.
     */
    public synchronized void removeConsumer() {

        // Terminating runnable by using Streams API.
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

    /**
     * Add producer.
     */
    public synchronized void addProducer() {
        ProducerMaster producerMaster = new ProducerMaster();
        Thread thread = new Thread(producerMaster);
        thread.start();
        producerThreads.add(thread);
        producerRunnables.add(producerMaster);
    }

    /**
     * Remove producer-thread and terminate producer-runnable.
     */
    public synchronized void removeProducer() {

        // Terminate by using Streams API.
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

    /**
     * Add consumer.
     */
    public synchronized void addConsumer() {
        ConsumerMaster consumerMaster = new ConsumerMaster();
        Thread thread = new Thread(consumerMaster);
        thread.start();
        consumerThreads.add(thread);
        consumerRunnables.add(consumerMaster);
    }
}
