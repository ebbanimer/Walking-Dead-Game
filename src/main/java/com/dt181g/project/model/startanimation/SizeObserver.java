package com.dt181g.project.model.startanimation;

/**
 * Interface for observer. Used for observer pattern for updating value when size changes in observable.
 * @author Ebba Nimér
 */
public interface SizeObserver {
    void update() throws InterruptedException;
}
