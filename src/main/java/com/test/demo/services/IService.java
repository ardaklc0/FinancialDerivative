package com.test.demo.services;

import java.util.List;

public interface IService<T, K, L> {
    public T save(K request);
    public void update(L request, Long id);
    public void delete(Long id);
    public List<T> findAll();
    public T findById(Long id);
}
