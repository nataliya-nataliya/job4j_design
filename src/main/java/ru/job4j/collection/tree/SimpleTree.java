package ru.job4j.collection.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> nodeParent = findBy(parent);
        if (nodeParent.isPresent()) {
            Node<E> element = nodeParent.get();
            if (findBy(child).isEmpty()) {
                element.getChildren().add(new Node<E>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }
    @Override
    public boolean isBinary() {
        return findByPredicate(el -> el.getChildren().size() > 2).isEmpty();    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate((el -> el.getValue().equals(value)));
    }
}