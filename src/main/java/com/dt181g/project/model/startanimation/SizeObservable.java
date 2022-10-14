package com.dt181g.project.model.startanimation;

public interface SizeObservable {
    void notifyObserver() throws Exception;
    void register(SizeObserver o);
    void unregister(SizeObserver o);
}
