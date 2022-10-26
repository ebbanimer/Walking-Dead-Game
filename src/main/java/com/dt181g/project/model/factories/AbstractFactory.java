package com.dt181g.project.model.factories;
import java.util.Deque;

/**
 * Abstract factory for zombies/food. Part of Abstract Factory pattern
 * @param <Object> zombie/food object.
 * @author Ebba Nimér
 */
public interface AbstractFactory<Object> {
    Deque<Object> create(String choice, int id);
}
