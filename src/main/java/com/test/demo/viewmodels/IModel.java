package com.test.demo.viewmodels;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class IModel<T, K> {
    @Autowired
    public K service;
    public final T model;

    public IModel(T model, K service) {
        this.model = model;
        this.service = service;
    }

    public Map<String, Object> calculateAllValues() {
        return new HashMap<>();
    }

    public T getModel() {
        return model;
    }
}
