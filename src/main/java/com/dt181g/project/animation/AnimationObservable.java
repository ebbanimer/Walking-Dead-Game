package com.dt181g.project.animation;

public interface AnimationObservable {
    void notifyObserver() throws Exception;
    void register(AnimationObserver o);
    void unregister(AnimationObserver o);
}

