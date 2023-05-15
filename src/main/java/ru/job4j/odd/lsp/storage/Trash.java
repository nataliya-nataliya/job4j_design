package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Trash extends AbstractStore {
    private final List<Food> trashList;

    public Trash() {
        this.trashList = new ArrayList<>();
    }

    @Override
    public void add(Food product) {
        trashList.add(product);
    }

    @Override
    public List<Food> findAll() {
        return trashList;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return trashList.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public boolean addCheck(Food product) {
        if (remainingShelfLifeCoefficient(product) < 0) {
            trashList.add(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trash trash = (Trash) o;
        return Objects.equals(trashList, trash.trashList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trashList);
    }

    @Override
    public String toString() {
        return "Trash{" + "trashList=" + trashList + '}';
    }
}
