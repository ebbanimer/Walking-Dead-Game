package com.dt181g.project;

public interface Observable {
    void notifyObserver() throws InterruptedException;
    void register(Observer o);
    void unregister(Observer o);
}
