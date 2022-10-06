package com.dt181g.project.model.itemAbstractFactory;

import java.util.List;

public interface AbstractFactory<Object> {
    List<Object> create(String choice, int id);
}
