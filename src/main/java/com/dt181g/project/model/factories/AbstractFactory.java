package com.dt181g.project.model.factories;
import java.util.Deque;

/**
 * Abstract factory for zombies/food.
 * @param <Object> zombie/food object.
 */
public interface AbstractFactory<Object> {
    Deque<Object> create(String choice, int id);
}
