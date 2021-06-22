package com.bootcamp.ditex3.dao;

import java.util.List;

public interface DAO <T> {

    T get(int msgId);

    T getFirst();

    T getLast();

    List<T> getAll();

    void save(T t);
}
