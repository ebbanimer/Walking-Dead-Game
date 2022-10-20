package com.dt181g.project.model.startanimation;

/**
 * Interface for observable. Used for observer pattern for updating subscriber when size changes.
 * @author Ebba Nimér
 */
public interface SizeObservable {
    void notifyObserver() throws Exception;
    void register(SizeObserver o);
    void unregister(SizeObserver o);
}
