package com.dt181g.project.model;

public interface Observable {
    void notifyObserver() throws InterruptedException;
    void register(Observer o);
    void unregister(Observer o);
}
