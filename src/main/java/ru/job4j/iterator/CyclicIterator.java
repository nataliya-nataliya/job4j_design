package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {
    private final List<T> data;
    private Iterator<T> cursor;
    private int count = 0;

    public CyclicIterator(List<T> data) {
        this.data = data;
        cursor = data.iterator();
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (count == data.size()) {
            cursor = data.iterator();
            count = 0;
        }
        count++;
        return cursor.next();
    }
}
