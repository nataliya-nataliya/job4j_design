package ru.job4j.odd.lsp.storage;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Food product);

    List<Food> findAll();

    List<Food> findBy(Predicate<Food> filter);

    boolean addCheck(Food product);

    double remainingShelfLifeCoefficient(Food product);

    void clearStore();

    int sizeOfStore();
}
