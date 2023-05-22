package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Warehouse extends AbstractStore {
    private final List<Food> warehouseList;

    public Warehouse() {
        this.warehouseList = new ArrayList<>();
    }

    @Override
    public void add(Food product) {
        warehouseList.add(product);
    }

    @Override
    public List<Food> findAll() {
        return warehouseList;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return warehouseList.stream().filter(filter).collect(Collectors.toList());

    }

    @Override
    public boolean addCheck(Food product) {
        return remainingShelfLifeCoefficient(product) > 0.75 ? warehouseList.add(product) : false;
    }

    @Override
    public void clearStore() {
        warehouseList.clear();
    }

    @Override
    public int sizeOfStore() {
        return warehouseList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(warehouseList, warehouse.warehouseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseList);
    }

    @Override
    public String toString() {
        return "Warehouse{" + "warehouseList=" + warehouseList + '}';
    }
}
