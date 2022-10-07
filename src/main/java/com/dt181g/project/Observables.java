package com.dt181g.project;

public interface Observables {
    void update(Observables o, Object arg);

    void notiFyObserver();

    void register(Observers o);

    void unregister(Observers o);
}
