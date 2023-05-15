package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Storages {
    private final List<Store> storeList;

    public Storages() {
        storeList = new ArrayList<>();
        collectStores();

    }

    private void collectStores() {
        storeList.add(new Shop());
        storeList.add(new Trash());
        storeList.add(new Warehouse());
    }

    public List<Store> getStoreList() {
        return storeList;
    }
}
