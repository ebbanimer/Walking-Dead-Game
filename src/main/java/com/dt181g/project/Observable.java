package com.dt181g.project;

public interface Observable {
    void update(Observable o, Object arg);

    void notiFyObserver();

    void register(Observer o);

    void unregister(Observer o);
}
