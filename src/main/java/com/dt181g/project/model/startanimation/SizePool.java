package com.dt181g.project.model.startanimation;

import java.util.ArrayList;

public class SizePool implements SizeObservable {
    public static final SizePool INSTANCE = new SizePool();

    private final ArrayList<SizeObserver> observers;
    private final int producers;
    private final int consumers;
    private volatile int size;
    
    private SizePool(){
        observers = new ArrayList<>();
        producers = 7;
        consumers = 1;
        size = 150;
    }

    public synchronized void removeSize(int amount) throws Exception {
        while (size < amount) {
            wait();
        }
        notify();
        size = size - amount;
        notifyObserver();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void addSize(int amount) throws Exception {
        size = size + amount;
        notifyObserver();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getProducers() {
        return producers;
    }

    public int getConsumers() {
        return consumers;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void notifyObserver() throws Exception {
        for (SizeObserver o : observers) {
            o.update();
        }
    }

    @Override
    public void register(SizeObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(SizeObserver o) {
        observers.remove(o);
    }
}
