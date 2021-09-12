package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() { // Метод pop() - должен возвращать значение и удалять его из коллекции
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
