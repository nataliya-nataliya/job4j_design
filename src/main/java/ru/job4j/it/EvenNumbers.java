package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbers implements Iterator<Integer> {
    private final int[] data;
    private int index;

    public EvenNumbers(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index < data.length && data[index] % 2 != 0) {
            index++;
        }
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
              return data[index++];
    }

}
