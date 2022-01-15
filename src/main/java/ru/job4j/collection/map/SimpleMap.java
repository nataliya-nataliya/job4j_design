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
        int i = indexFor(hash(key.hashCode()));
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[i] == null || (table[i].key).equals(key)) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count++;
        } else {
             rsl = false;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode() ^ (hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity *= 2;
    }

    @Override
    public V get(K key) {
       int i = indexFor(hash(key.hashCode()));
        return table[i] != null ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(key.hashCode()));
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

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
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
