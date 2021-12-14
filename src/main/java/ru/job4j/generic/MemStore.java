package ru.job4j.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T>  {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(String id, T model) {
        storage.putIfAbsent(id, model);
    }

    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, model) != null;
    }

    @Override
    public boolean delete(String id) {
        return storage.remove(id, findById(id));
    }

    @Override
    public T findById(String id) {
        /*int i = Integer.parseInt(id);*/
        return storage.get(id);
    }
}
