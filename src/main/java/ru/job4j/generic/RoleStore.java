package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public class RoleStore implements Store<Role> {
    private final Map<String, Role> roleStorage = new HashMap<>();

    @Override
    public void add(String id, Role model) {
        roleStorage.putIfAbsent(id, model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return roleStorage.replace(id, model) != null;
    }

    @Override
    public boolean delete(String id) {
        return roleStorage.remove(id, findById(id));
    }

    @Override
    public Role findById(String id) {
        return roleStorage.get(id);
    }
}
