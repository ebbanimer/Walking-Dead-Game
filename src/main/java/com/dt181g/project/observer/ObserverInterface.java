package com.dt181g.project.observer;

/**
 *
 */
interface observer {
    void register();
    void unregister();
    void notifyObservers();
}

interface observable {
    void update();
}
