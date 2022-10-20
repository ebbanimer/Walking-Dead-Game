package com.dt181g.project.model.startanimation;

import java.util.ArrayList;

/**
 * Class representing pool that handles size. Implemented as observable.
 * @author Ebba Nimér
 */
public class SizePool implements SizeObservable {
    public static final SizePool INSTANCE = new SizePool();

    private final ArrayList<SizeObserver> observers;
    private final int producers;
    private final int consumers;
    private volatile int size;

    /**
     * Initialize pool with amount of consumers/producers, and initial size.
     */
    private SizePool(){
        observers = new ArrayList<>();
        producers = 7;
        consumers = 1;
        size = 150;
    }

    /**
     * Remove amount of size in atomic fashion.
     * @param amount amount to be removed.
     * @throws Exception exception
     */
    public synchronized void removeSize(int amount) throws Exception {
        while (size < amount) {
            wait();           // if amount is bigger than size-pool, wait until size is larger.
        }
        notify();
        size = size - amount;
        notifyObserver();
    }

    /**
     * Add amount of size in atomic fashion.
     * @param amount amount to be added.
     * @throws Exception exception
     */
    public synchronized void addSize(int amount) throws Exception {
        size = size + amount;
        notifyObserver();
    }

    /**
     * Provide amount of producers to calling client.
     * @return amount of producers.
     */
    public int getProducers() {
        return producers;
    }

    /**
     * Provide amount of consumers to calling client.
     * @return amount of consumers.
     */
    public int getConsumers() {
        return consumers;
    }

    /**
     * Provide amount of size to calling client.
     * @return amount of size.
     */
    public int getSize() {
        return size;
    }

    /**
     * When changes have occured, update observer.
     * @throws Exception exception
     */
    @Override public void notifyObserver() throws Exception {
        for (SizeObserver o : observers) {
            o.update();
        }
    }

    /**
     * Register observer.
     * @param o observer
     */
    @Override public void register(SizeObserver o) {
        observers.add(o);
    }

    /**
     * Unregister observer.
     * @param o observer
     */
    @Override public void unregister(SizeObserver o) {
        observers.remove(o);
    }
}
