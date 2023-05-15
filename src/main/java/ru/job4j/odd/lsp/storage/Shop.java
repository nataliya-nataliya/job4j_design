package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shop extends AbstractStore {
    private final List<Food> shopList;

    public Shop() {
        shopList = new ArrayList<>();
    }

    @Override
    public void add(Food product) {
        shopList.add(product);
    }

    @Override
    public List<Food> findAll() {
        return shopList;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return shopList.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public boolean addCheck(Food product) {
        boolean isAdd;
        if (remainingShelfLifeCoefficient(product) >= 0 && remainingShelfLifeCoefficient(product) < 0.75) {
            if (remainingShelfLifeCoefficient(product) < 0.25) {
                product.setPrice(product.getPrice() * product.getDiscount());
            }
            shopList.add(product);
            isAdd = true;
        } else {
            isAdd = false;
        }
        return isAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shop shop = (Shop) o;
        return Objects.equals(shopList, shop.shopList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopList);
    }

    @Override
    public String toString() {
        return "Shop{"
                + "shopList=" + shopList + '}';
    }
}
