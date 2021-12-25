package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements  Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T i : set) {
            if (Objects.equals(value, i)) {
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
