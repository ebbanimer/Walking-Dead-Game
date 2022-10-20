package com.dt181g.project.model;

/**
 * Interface representing observable.
 * @author Ebba Nimér
 */
public interface Observable {
    void notifyObserver() throws InterruptedException;
    void register(Observer o);
    void unregister(Observer o);
}
