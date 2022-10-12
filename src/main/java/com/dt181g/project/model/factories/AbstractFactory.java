package com.dt181g.project.model.factories;
import java.util.Deque;

public interface AbstractFactory<Object> {
    Deque<Object> create(String choice, int id);
}
