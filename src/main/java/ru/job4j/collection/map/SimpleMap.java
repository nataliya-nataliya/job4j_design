package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int i = indexFor(hash(key));
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count = count + 1;
        }
        return rsl;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h ^ (h >>> 16));
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tmp = table;
        table = new MapEntry[capacity * 2];
        for (MapEntry<K, V> obj : tmp) {
            if (obj != null) {
                put(obj.key, obj.value);
            }
        }
    }

    @Override
    public V get(K key) {
       int i = indexFor(hash(key));
        return (table[i] != null && (table[i].key).equals(key)) ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(key));
        boolean rsl = false;
        if (table[i] != null && (table[i].key).equals(key)) {
            table[i] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int pointer = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (pointer < table.length && table[pointer] == null) {
                    pointer++;
                }
                return pointer < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[pointer++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
